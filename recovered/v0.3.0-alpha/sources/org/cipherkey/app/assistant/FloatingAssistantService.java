package org.cipherkey.app.assistant;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.app.NotificationCompat;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import org.cipherkey.app.MainActivity;
import org.cipherkey.app.R;
import org.cipherkey.app.companion.BaseCompanionActivityKt;
import org.cipherkey.app.companion.DecryptActivity;
import org.cipherkey.app.companion.EncryptActivity;
import org.cipherkey.data.preferences.SettingsRepository;

/* compiled from: FloatingAssistantService.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 52\u00020\u0001:\u00015B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\"\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0016J\b\u0010 \u001a\u00020\u001bH\u0016J\b\u0010!\u001a\u00020\u001bH\u0002J*\u0010\"\u001a\u00020\u00112\b\b\u0001\u0010#\u001a\u00020\u001d2\b\b\u0001\u0010$\u001a\u00020\u001d2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001b0&H\u0002J\u0018\u0010'\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020\u00112\u0006\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020\u001bH\u0002J\b\u0010,\u001a\u00020\u001bH\u0002J\u0010\u0010-\u001a\u00020\u001b2\u0006\u0010.\u001a\u00020\u0015H\u0002J\b\u0010/\u001a\u00020\u001bH\u0002J\b\u00100\u001a\u00020\u001bH\u0002J\b\u00101\u001a\u000202H\u0002J\u0010\u00103\u001a\u00020\u001b2\u0006\u00104\u001a\u000202H\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lorg/cipherkey/app/assistant/FloatingAssistantService;", "Landroid/app/Service;", "<init>", "()V", "settings", "Lorg/cipherkey/data/preferences/SettingsRepository;", "getSettings", "()Lorg/cipherkey/data/preferences/SettingsRepository;", "setSettings", "(Lorg/cipherkey/data/preferences/SettingsRepository;)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "windowManager", "Landroid/view/WindowManager;", "root", "Landroid/widget/FrameLayout;", "bubble", "Landroid/view/View;", "panel", "Landroid/widget/LinearLayout;", "expanded", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onStartCommand", "", "flags", "startId", "onDestroy", "addOverlay", "panelButton", "iconRes", "labelRes", "onClick", "Lkotlin/Function0;", "attachDragAndTap", "bubbleView", "params", "Landroid/view/WindowManager$LayoutParams;", "togglePanel", "collapsePanel", "launchClipboardFlow", "encrypt", "closeAssistant", "createChannel", "buildNotification", "Landroid/app/Notification;", "startForegroundWithType", "notification", "Companion", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes3.dex */
public final class FloatingAssistantService extends Hilt_FloatingAssistantService {
    public static final String ACTION_STOP = "org.cipherkey.app.assistant.STOP";
    private static final String CHANNEL_ID = "assistant";
    private static final int NOTIFICATION_ID = 1042;
    private View bubble;
    private boolean expanded;
    private LinearLayout panel;
    private FrameLayout root;
    private final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain()));

    @Inject
    public SettingsRepository settings;
    private WindowManager windowManager;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    public final SettingsRepository getSettings() {
        SettingsRepository settingsRepository = this.settings;
        if (settingsRepository != null) {
            return settingsRepository;
        }
        Intrinsics.throwUninitializedPropertyAccessException("settings");
        return null;
    }

    public final void setSettings(SettingsRepository settingsRepository) {
        Intrinsics.checkNotNullParameter(settingsRepository, "<set-?>");
        this.settings = settingsRepository;
    }

    @Override // org.cipherkey.app.assistant.Hilt_FloatingAssistantService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.windowManager = (WindowManager) getSystemService(WindowManager.class);
        createChannel();
        startForegroundWithType(buildNotification());
        if (Settings.canDrawOverlays(this)) {
            addOverlay();
        } else {
            stopSelf();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!Intrinsics.areEqual(intent != null ? intent.getAction() : null, ACTION_STOP)) {
            return 1;
        }
        stopSelf();
        return 2;
    }

    @Override // android.app.Service
    public void onDestroy() {
        Object objM8783constructorimpl;
        Unit unit;
        FrameLayout frameLayout = this.root;
        if (frameLayout != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                FloatingAssistantService floatingAssistantService = this;
                WindowManager windowManager = this.windowManager;
                if (windowManager != null) {
                    windowManager.removeView(frameLayout);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                objM8783constructorimpl = Result.m8783constructorimpl(unit);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM8783constructorimpl = Result.m8783constructorimpl(ResultKt.createFailure(th));
            }
            Result.m8782boximpl(objM8783constructorimpl);
        }
        this.root = null;
        this.bubble = null;
        this.panel = null;
        CoroutineScopeKt.cancel$default(this.scope, null, 1, null);
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addOverlay$lambda$3(FloatingAssistantService floatingAssistantService) {
        floatingAssistantService.launchClipboardFlow(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addOverlay$lambda$4(FloatingAssistantService floatingAssistantService) {
        floatingAssistantService.launchClipboardFlow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addOverlay$lambda$5(FloatingAssistantService floatingAssistantService) {
        floatingAssistantService.closeAssistant();
        return Unit.INSTANCE;
    }

    private final void addOverlay() {
        Object objM8783constructorimpl;
        WindowManager windowManager = this.windowManager;
        if (windowManager == null) {
            return;
        }
        float f = getResources().getDisplayMetrics().density;
        int i = (int) (12 * f);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2038, 40, -3);
        layoutParams.gravity = 8388659;
        layoutParams.x = i;
        layoutParams.y = (int) (220 * f);
        FloatingAssistantService floatingAssistantService = this;
        ImageView imageView = new ImageView(floatingAssistantService);
        imageView.setImageResource(R.drawable.ic_assistant_lock);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(Color.argb(242, 46, 58, 120));
        imageView.setBackground(gradientDrawable);
        int i2 = (int) (48 * f);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(i2, i2);
        layoutParams2.gravity = 17;
        imageView.setLayoutParams(layoutParams2);
        imageView.setPadding(i, i, i, i);
        LinearLayout linearLayout = new LinearLayout(floatingAssistantService);
        linearLayout.setOrientation(0);
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setCornerRadius(28 * f);
        gradientDrawable2.setColor(Color.argb(240, 23, 26, 36));
        linearLayout.setBackground(gradientDrawable2);
        linearLayout.setPadding(i, i, i, i);
        linearLayout.addView(panelButton(R.drawable.ic_assistant_lock, R.string.assistant_encrypt, new Function0() { // from class: org.cipherkey.app.assistant.FloatingAssistantService$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FloatingAssistantService.addOverlay$lambda$3(this.f$0);
            }
        }));
        linearLayout.addView(panelButton(R.drawable.ic_assistant_lock_open, R.string.assistant_decrypt, new Function0() { // from class: org.cipherkey.app.assistant.FloatingAssistantService$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FloatingAssistantService.addOverlay$lambda$4(this.f$0);
            }
        }));
        linearLayout.addView(panelButton(R.drawable.ic_assistant_close, R.string.close, new Function0() { // from class: org.cipherkey.app.assistant.FloatingAssistantService$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FloatingAssistantService.addOverlay$lambda$5(this.f$0);
            }
        }));
        FrameLayout frameLayout = new FrameLayout(floatingAssistantService);
        ImageView imageView2 = imageView;
        frameLayout.addView(imageView2);
        linearLayout.setVisibility(8);
        frameLayout.addView(linearLayout);
        attachDragAndTap(imageView2, layoutParams);
        try {
            Result.Companion companion = Result.INSTANCE;
            FloatingAssistantService floatingAssistantService2 = this;
            windowManager.addView(frameLayout, layoutParams);
            objM8783constructorimpl = Result.m8783constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8783constructorimpl = Result.m8783constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8786exceptionOrNullimpl(objM8783constructorimpl) != null) {
            stopSelf();
            return;
        }
        this.root = frameLayout;
        this.bubble = imageView2;
        this.panel = linearLayout;
    }

    private final View panelButton(int iconRes, int labelRes, final Function0<Unit> onClick) {
        float f = getResources().getDisplayMetrics().density;
        int i = (int) (44 * f);
        int i2 = (int) (11 * f);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(iconRes);
        imageView.setContentDescription(getString(labelRes));
        imageView.setPadding(i2, i2, i2, i2);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(0);
        imageView.setBackground(gradientDrawable);
        imageView.setClickable(true);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: org.cipherkey.app.assistant.FloatingAssistantService$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                onClick.invoke();
            }
        });
        imageView.setLayoutParams(new LinearLayout.LayoutParams(i, i));
        return imageView;
    }

    private final void attachDragAndTap(View bubbleView, final WindowManager.LayoutParams params) {
        bubbleView.setOnTouchListener(new View.OnTouchListener() { // from class: org.cipherkey.app.assistant.FloatingAssistantService.attachDragAndTap.1
            private float downX;
            private float downY;
            private boolean moved;
            private int startX;
            private int startY;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                Unit unit;
                Intrinsics.checkNotNullParameter(v, "v");
                Intrinsics.checkNotNullParameter(event, "event");
                int actionMasked = event.getActionMasked();
                if (actionMasked == 0) {
                    this.downX = event.getRawX();
                    this.downY = event.getRawY();
                    this.startX = params.x;
                    this.startY = params.y;
                    this.moved = false;
                    return true;
                }
                if (actionMasked != 1) {
                    if (actionMasked == 2) {
                        float rawX = event.getRawX() - this.downX;
                        float rawY = event.getRawY() - this.downY;
                        if (Math.abs(rawX) > 10.0f || Math.abs(rawY) > 10.0f) {
                            this.moved = true;
                        }
                        params.x = this.startX + ((int) rawX);
                        params.y = this.startY + ((int) rawY);
                        FloatingAssistantService floatingAssistantService = this;
                        WindowManager.LayoutParams layoutParams = params;
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            AnonymousClass1 anonymousClass1 = this;
                            WindowManager windowManager = floatingAssistantService.windowManager;
                            if (windowManager != null) {
                                windowManager.updateViewLayout(floatingAssistantService.root, layoutParams);
                                unit = Unit.INSTANCE;
                            } else {
                                unit = null;
                            }
                            Result.m8783constructorimpl(unit);
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            Result.m8783constructorimpl(ResultKt.createFailure(th));
                        }
                        return true;
                    }
                    if (actionMasked != 3) {
                        return false;
                    }
                }
                if (!this.moved && event.getActionMasked() == 1) {
                    this.togglePanel();
                }
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void togglePanel() {
        LinearLayout linearLayout;
        View view = this.bubble;
        if (view == null || (linearLayout = this.panel) == null) {
            return;
        }
        boolean z = this.expanded;
        this.expanded = !z;
        view.setVisibility(!z ? 8 : 0);
        linearLayout.setVisibility(this.expanded ? 0 : 8);
    }

    private final void collapsePanel() {
        this.expanded = false;
        View view = this.bubble;
        if (view != null) {
            view.setVisibility(0);
        }
        LinearLayout linearLayout = this.panel;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
    }

    private final void launchClipboardFlow(boolean encrypt) {
        collapsePanel();
        Intent intentAddFlags = new Intent(this, (Class<?>) (encrypt ? EncryptActivity.class : DecryptActivity.class)).putExtra(BaseCompanionActivityKt.EXTRA_FROM_CLIPBOARD, true).addFlags(268435456);
        Intrinsics.checkNotNullExpressionValue(intentAddFlags, "addFlags(...)");
        try {
            Result.Companion companion = Result.INSTANCE;
            FloatingAssistantService floatingAssistantService = this;
            startActivity(intentAddFlags);
            Result.m8783constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m8783constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* compiled from: FloatingAssistantService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.assistant.FloatingAssistantService$closeAssistant$1", f = "FloatingAssistantService.kt", i = {0, 0, 0}, l = {269}, m = "invokeSuspend", n = {"$this$launch", "$this$invokeSuspend_u24lambda_u240", "$i$a$-runCatching-FloatingAssistantService$closeAssistant$1$1"}, s = {"L$0", "L$1", "I$0"}, v = 1)
    /* renamed from: org.cipherkey.app.assistant.FloatingAssistantService$closeAssistant$1, reason: invalid class name and case insensitive filesystem */
    static final class C04981 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        C04981(Continuation<? super C04981> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04981 c04981 = FloatingAssistantService.this.new C04981(continuation);
            c04981.L$0 = obj;
            return c04981;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04981) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FloatingAssistantService floatingAssistantService = FloatingAssistantService.this;
                    Result.Companion companion = Result.INSTANCE;
                    SettingsRepository settings = floatingAssistantService.getSettings();
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.I$0 = 0;
                    this.label = 1;
                    if (settings.setAssistantEnabled(false, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                Result.m8783constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m8783constructorimpl(ResultKt.createFailure(th));
            }
            FloatingAssistantService.this.stopSelf();
            return Unit.INSTANCE;
        }
    }

    private final void closeAssistant() {
        collapsePanel();
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C04981(null), 3, null);
    }

    private final void createChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, getString(R.string.assistant_channel_name), 1);
        notificationChannel.setShowBadge(false);
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
    }

    private final Notification buildNotification() {
        FloatingAssistantService floatingAssistantService = this;
        Notification notificationBuild = new NotificationCompat.Builder(floatingAssistantService, CHANNEL_ID).setSmallIcon(R.drawable.ic_assistant_lock).setContentTitle(getString(R.string.assistant_notification_title)).setContentText(getString(R.string.assistant_notification_text)).setOngoing(true).setPriority(-2).setCategory(NotificationCompat.CATEGORY_SERVICE).setContentIntent(PendingIntent.getActivity(floatingAssistantService, 0, new Intent(floatingAssistantService, (Class<?>) MainActivity.class), 201326592)).build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
        return notificationBuild;
    }

    private final void startForegroundWithType(Notification notification) {
        if (Build.VERSION.SDK_INT >= 34) {
            startForeground(NOTIFICATION_ID, notification, 1073741824);
        } else {
            startForeground(NOTIFICATION_ID, notification);
        }
    }

    /* compiled from: FloatingAssistantService.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/cipherkey/app/assistant/FloatingAssistantService$Companion;", "", "<init>", "()V", "CHANNEL_ID", "", "NOTIFICATION_ID", "", "ACTION_STOP", "start", "", "context", "Landroid/content/Context;", "stop", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startForegroundService(new Intent(context, (Class<?>) FloatingAssistantService.class));
        }

        public final void stop(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.stopService(new Intent(context, (Class<?>) FloatingAssistantService.class));
        }
    }
}
