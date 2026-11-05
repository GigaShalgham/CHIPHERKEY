package org.cipherkey.app.assistant

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.cipherkey.app.MainActivity
import org.cipherkey.app.R
import org.cipherkey.app.companion.EXTRA_FROM_CLIPBOARD
import org.cipherkey.app.companion.DecryptActivity
import org.cipherkey.app.companion.EncryptActivity
import org.cipherkey.data.preferences.SettingsRepository

/**
 * Floating assistant (v0.3.0): a user-enabled overlay bubble with quick
 * Encrypt/Decrypt-clipboard actions.
 *
 * SECURITY CONTRACT:
 *  - the service starts ONLY on an explicit user action (the Settings toggle
 *    or the auto-start after the user had enabled it with the permission
 *    granted — see [AssistantReadiness]);
 *  - it holds no key material and performs no crypto: the buttons merely
 *    launch the existing companion activities with the clipboard extra, so
 *    every encryption decision still runs through the audited companion
 *    flow (spec 003/004) with its focus-gated clipboard reads;
 *  - tapping "Close" on the panel persists assistantEnabled = false, so the
 *    bubble does not come back until the user re-enables it.
 *
 * The overlay needs SYSTEM_ALERT_WINDOW ("Display over other apps"), which
 * cannot be requested at runtime — Settings deep-links the user to it.
 */
@AndroidEntryPoint
class FloatingAssistantService : Service() {

    @Inject
    lateinit var settings: SettingsRepository

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private var windowManager: WindowManager? = null
    private var root: FrameLayout? = null
    private var bubble: ImageView? = null
    private var panel: LinearLayout? = null
    private var expanded = false

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WindowManager::class.java)
        createChannel()
        startForegroundWithType(buildNotification())
        if (Settings.canDrawOverlays(this)) {
            addOverlay()
        } else {
            // Permission revoked while enabled: stop instead of crashing.
            stopSelf()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_STOP) {
            stopSelf()
            return START_NOT_STICKY
        }
        return START_STICKY
    }

    override fun onDestroy() {
        root?.let { overlay ->
            runCatching { windowManager?.removeView(overlay) }
        }
        root = null
        bubble = null
        panel = null
        scope.cancel()
        super.onDestroy()
    }

    // ------------------------------------------------------------ overlay

    private fun addOverlay() {
        val manager = windowManager ?: return
        val density = resources.displayMetrics.density
        val pad = (12 * density).toInt()

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            android.graphics.PixelFormat.TRANSLUCENT,
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = pad
            y = (220 * density).toInt()
        }

        // The collapsed bubble.
        val bubbleView = ImageView(this).apply {
            setImageResource(R.drawable.ic_assistant_lock)
            background = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(Color.argb(242, 46, 58, 120))
            }
            layoutParams = FrameLayout.LayoutParams(
                (48 * density).toInt(),
                (48 * density).toInt(),
            ).apply { gravity = Gravity.CENTER }
            setPadding(pad, pad, pad, pad)
        }

        // The expanded quick-action panel.
        val panelView = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            background = GradientDrawable().apply {
                cornerRadius = 28 * density
                setColor(Color.argb(240, 23, 26, 36))
            }
            setPadding(pad, pad, pad, pad)
            visibility = View.GONE
            addView(
                panelButton(R.drawable.ic_assistant_lock, R.string.assistant_encrypt) {
                    launchClipboardFlow(encrypt = true)
                },
            )
            addView(
                panelButton(R.drawable.ic_assistant_lock_open, R.string.assistant_decrypt) {
                    launchClipboardFlow(encrypt = false)
                },
            )
            addView(panelButton(R.drawable.ic_assistant_close, R.string.close) {
                closeAssistant()
            })
        }

        val overlay = FrameLayout(this).apply {
            addView(bubbleView)
            addView(panelView)
        }
        attachDragAndTap(bubbleView, params)

        runCatching { manager.addView(overlay, params) }
            .onFailure { stopSelf(); return }

        root = overlay
        bubble = bubbleView
        panel = panelView
    }

    private fun panelButton(iconRes: Int, labelRes: Int, onClick: () -> Unit): View =
        ImageView(this).apply {
            setImageResource(iconRes)
            contentDescription = getString(labelRes)
            val pad = (11 * resources.displayMetrics.density).toInt()
            setPadding(pad, pad, pad, pad)
            background = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(Color.TRANSPARENT)
            }
            isClickable = true
            setOnClickListener { onClick() }
            layoutParams = LinearLayout.LayoutParams(
                (44 * resources.displayMetrics.density).toInt(),
                (44 * resources.displayMetrics.density).toInt(),
            )
        }

    /** Drag to move; a clean tap (no 10 px movement) toggles the panel. */
    private fun attachDragAndTap(bubbleView: View, params: WindowManager.LayoutParams) {
        bubbleView.setOnTouchListener(object : View.OnTouchListener {
            private var downX = 0f
            private var downY = 0f
            private var startX = 0
            private var startY = 0
            private var moved = false

            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when (event.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        downX = event.rawX
                        downY = event.rawY
                        startX = params.x
                        startY = params.y
                        moved = false
                        return true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val dx = event.rawX - downX
                        val dy = event.rawY - downY
                        if (kotlin.math.abs(dx) > DRAG_SLOP ||
                            kotlin.math.abs(dy) > DRAG_SLOP
                        ) {
                            moved = true
                        }
                        params.x = startX + dx.toInt()
                        params.y = startY + dy.toInt()
                        runCatching {
                            windowManager?.updateViewLayout(root, params)
                        }
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        if (!moved) togglePanel()
                        return true
                    }
                    MotionEvent.ACTION_CANCEL -> return true
                }
                return false
            }
        })
    }

    private fun togglePanel() {
        val bubbleView = bubble ?: return
        val panelView = panel ?: return
        val wasExpanded = expanded
        expanded = !wasExpanded
        bubbleView.visibility = if (expanded) View.GONE else View.VISIBLE
        panelView.visibility = if (expanded) View.VISIBLE else View.GONE
    }

    private fun collapsePanel() {
        expanded = false
        bubble?.visibility = View.VISIBLE
        panel?.visibility = View.GONE
    }

    // ------------------------------------------------------------ actions

    private fun launchClipboardFlow(encrypt: Boolean) {
        collapsePanel()
        val intent = Intent(
            this,
            if (encrypt) EncryptActivity::class.java else DecryptActivity::class.java,
        )
            .putExtra(EXTRA_FROM_CLIPBOARD, true)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        runCatching { startActivity(intent) }
    }

    /** "Close" on the panel: persist disabled, then stop the service. */
    private fun closeAssistant() {
        collapsePanel()
        scope.launch {
            runCatching { settings.setAssistantEnabled(false) }
            stopSelf()
        }
    }

    // ------------------------------------------------------------ notification

    private fun createChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            getString(R.string.assistant_channel_name),
            NotificationManager.IMPORTANCE_LOW,
        ).apply { setShowBadge(false) }
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }

    private fun buildNotification(): Notification {
        val contentIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_assistant_lock)
            .setContentTitle(getString(R.string.assistant_notification_title))
            .setContentText(getString(R.string.assistant_notification_text))
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .setContentIntent(contentIntent)
            .build()
    }

    private fun startForegroundWithType(notification: Notification) {
        if (Build.VERSION.SDK_INT >= 34) {
            startForeground(
                NOTIFICATION_ID,
                notification,
                android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE,
            )
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
    }

    companion object {
        private const val CHANNEL_ID = "assistant"
        private const val NOTIFICATION_ID = 1042
        private const val DRAG_SLOP = 10f

        /** Sent by Settings' "stop" path so the toggle stays responsive. */
        const val ACTION_STOP = "org.cipherkey.app.assistant.STOP"

        fun start(context: Context) {
            context.startForegroundService(Intent(context, FloatingAssistantService::class.java))
        }

        fun stop(context: Context) {
            context.stopService(Intent(context, FloatingAssistantService::class.java))
        }
    }
}
