package org.cipherkey.security.clipboard;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.bouncycastle.i18n.TextBundle;
import org.cipherkey.security.clipboard.ClipboardRead;

/* compiled from: ClipboardAccess.kt */
@Singleton
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0013\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\n\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0016J\n\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/cipherkey/security/clipboard/AndroidClipboardAccess;", "Lorg/cipherkey/security/clipboard/ClipboardAccess;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "read", "Lorg/cipherkey/security/clipboard/ClipboardRead;", "currentText", "", "setText", "", TextBundle.TEXT_ENTRY, "manager", "Landroid/content/ClipboardManager;", "clipboard_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AndroidClipboardAccess implements ClipboardAccess {
    private final Context context;

    @Inject
    public AndroidClipboardAccess(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.cipherkey.security.clipboard.ClipboardAccess
    public ClipboardRead read() {
        Object objM8783constructorimpl;
        Object objM8783constructorimpl2;
        Object objM8783constructorimpl3;
        CharSequence charSequenceM8783constructorimpl;
        ArrayList arrayListEmptyList;
        ClipboardManager clipboardManagerManager = manager();
        if (clipboardManagerManager == null) {
            return new ClipboardRead.Blocked("clipboard service unavailable");
        }
        try {
            ClipData primaryClip = clipboardManagerManager.getPrimaryClip();
            if (primaryClip == null) {
                return ClipboardRead.Empty.INSTANCE;
            }
            try {
                Result.Companion companion = Result.INSTANCE;
                AndroidClipboardAccess androidClipboardAccess = this;
                ClipDescription description = primaryClip.getDescription();
                if (description == null) {
                    arrayListEmptyList = CollectionsKt.emptyList();
                } else {
                    IntRange intRangeUntil = RangesKt.until(0, description.getMimeTypeCount());
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
                    Iterator<Integer> it = intRangeUntil.iterator();
                    while (it.hasNext()) {
                        arrayList.add(description.getMimeType(((IntIterator) it).nextInt()));
                    }
                    arrayListEmptyList = arrayList;
                }
                objM8783constructorimpl = Result.m8783constructorimpl(arrayListEmptyList);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM8783constructorimpl = Result.m8783constructorimpl(ResultKt.createFailure(th));
            }
            List listEmptyList = CollectionsKt.emptyList();
            if (Result.m8789isFailureimpl(objM8783constructorimpl)) {
                objM8783constructorimpl = listEmptyList;
            }
            List list = (List) objM8783constructorimpl;
            IntRange intRangeUntil2 = RangesKt.until(0, primaryClip.getItemCount());
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil2, 10));
            Iterator<Integer> it2 = intRangeUntil2.iterator();
            while (it2.hasNext()) {
                int iNextInt = ((IntIterator) it2).nextInt();
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    AndroidClipboardAccess androidClipboardAccess2 = this;
                    objM8783constructorimpl2 = Result.m8783constructorimpl(primaryClip.getItemAt(iNextInt));
                } catch (Throwable th2) {
                    Result.Companion companion4 = Result.INSTANCE;
                    objM8783constructorimpl2 = Result.m8783constructorimpl(ResultKt.createFailure(th2));
                }
                CharSequence charSequence = null;
                if (Result.m8789isFailureimpl(objM8783constructorimpl2)) {
                    objM8783constructorimpl2 = null;
                }
                ClipData.Item item = (ClipData.Item) objM8783constructorimpl2;
                if (item != null) {
                    try {
                        Result.Companion companion5 = Result.INSTANCE;
                        AndroidClipboardAccess androidClipboardAccess3 = this;
                        objM8783constructorimpl3 = Result.m8783constructorimpl(item.getText());
                    } catch (Throwable th3) {
                        Result.Companion companion6 = Result.INSTANCE;
                        objM8783constructorimpl3 = Result.m8783constructorimpl(ResultKt.createFailure(th3));
                    }
                    if (Result.m8789isFailureimpl(objM8783constructorimpl3)) {
                        objM8783constructorimpl3 = null;
                    }
                    CharSequence charSequence2 = (CharSequence) objM8783constructorimpl3;
                    if (charSequence2 == null) {
                        try {
                            Result.Companion companion7 = Result.INSTANCE;
                            AndroidClipboardAccess androidClipboardAccess4 = this;
                            CharSequence charSequenceCoerceToText = item.coerceToText(this.context);
                            charSequenceM8783constructorimpl = Result.m8783constructorimpl(charSequenceCoerceToText != null ? charSequenceCoerceToText.toString() : null);
                        } catch (Throwable th4) {
                            Result.Companion companion8 = Result.INSTANCE;
                            charSequenceM8783constructorimpl = Result.m8783constructorimpl(ResultKt.createFailure(th4));
                        }
                        if (!Result.m8789isFailureimpl(charSequenceM8783constructorimpl)) {
                            charSequence = charSequenceM8783constructorimpl;
                        }
                        charSequence = charSequence;
                    } else {
                        charSequence = charSequence2;
                    }
                }
                arrayList2.add(charSequence);
            }
            return ClipboardParser.INSTANCE.parse(new ClipSnapshot(list, arrayList2));
        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getSimpleName();
            }
            return new ClipboardRead.Blocked(message);
        }
    }

    @Override // org.cipherkey.security.clipboard.ClipboardAccess
    public String currentText() {
        ClipboardRead clipboardRead = read();
        ClipboardRead.Text text = clipboardRead instanceof ClipboardRead.Text ? (ClipboardRead.Text) clipboardRead : null;
        if (text != null) {
            return text.getText();
        }
        return null;
    }

    @Override // org.cipherkey.security.clipboard.ClipboardAccess
    public boolean setText(String text) {
        Object objM8783constructorimpl;
        Intrinsics.checkNotNullParameter(text, "text");
        ClipboardManager clipboardManagerManager = manager();
        if (clipboardManagerManager == null) {
            return false;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            AndroidClipboardAccess androidClipboardAccess = this;
            clipboardManagerManager.setPrimaryClip(ClipData.newPlainText("CipherKey", text));
            objM8783constructorimpl = Result.m8783constructorimpl(true);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8783constructorimpl = Result.m8783constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8789isFailureimpl(objM8783constructorimpl)) {
            objM8783constructorimpl = false;
        }
        return ((Boolean) objM8783constructorimpl).booleanValue();
    }

    private final ClipboardManager manager() {
        return (ClipboardManager) this.context.getSystemService(ClipboardManager.class);
    }
}
