package org.cipherkey.app.companion;

import android.content.Intent;
import dagger.hilt.android.AndroidEntryPoint;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.app.R;
import org.cipherkey.app.companion.ProcessTextContract;

/* compiled from: DecryptActivity.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0014¨\u0006\b"}, d2 = {"Lorg/cipherkey/app/companion/DecryptActivity;", "Lorg/cipherkey/app/companion/BaseCompanionActivity;", "<init>", "()V", "dispatchIntent", "", "intent", "Landroid/content/Intent;", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes3.dex */
public final class DecryptActivity extends Hilt_DecryptActivity {
    public static final int $stable = 8;

    @Override // org.cipherkey.app.companion.BaseCompanionActivity
    protected void dispatchIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (isClipboardLaunch(intent)) {
            getViewModel().beginFromClipboard(CompanionMode.DECRYPT);
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("android.intent.extra.PROCESS_TEXT_READONLY", false);
        ProcessTextContract.SelectedText envelope = ProcessTextContract.INSTANCE.parseEnvelope(intent.getCharSequenceExtra("android.intent.extra.PROCESS_TEXT"));
        if (envelope instanceof ProcessTextContract.SelectedText.Valid) {
            getViewModel().beginDecrypt(((ProcessTextContract.SelectedText.Valid) envelope).getText(), TextSource.SELECTION, booleanExtra);
        } else if (Intrinsics.areEqual(envelope, ProcessTextContract.SelectedText.Empty.INSTANCE)) {
            BaseCompanionActivity.showNotice$default(this, new UiNotice(R.string.notice_no_selection_decrypt, null, 2, null), false, 2, null);
        } else {
            if (!(envelope instanceof ProcessTextContract.SelectedText.Oversized)) {
                throw new NoWhenBranchMatchedException();
            }
            BaseCompanionActivity.showNotice$default(this, new UiNotice(R.string.notice_oversized_decrypt, String.valueOf(((ProcessTextContract.SelectedText.Oversized) envelope).getLimitBytes() / 1024)), false, 2, null);
        }
    }
}
