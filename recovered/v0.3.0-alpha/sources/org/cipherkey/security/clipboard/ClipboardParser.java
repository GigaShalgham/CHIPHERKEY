package org.cipherkey.security.clipboard;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.cipherkey.security.clipboard.ClipboardRead;

/* compiled from: ClipboardAccess.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\t"}, d2 = {"Lorg/cipherkey/security/clipboard/ClipboardParser;", "", "<init>", "()V", "parse", "Lorg/cipherkey/security/clipboard/ClipboardRead;", "clip", "Lorg/cipherkey/security/clipboard/ClipSnapshot;", "classifyBlank", "clipboard_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ClipboardParser {
    public static final ClipboardParser INSTANCE = new ClipboardParser();

    private ClipboardParser() {
    }

    public final ClipboardRead parse(ClipSnapshot clip) {
        Object next;
        String string;
        if (clip == null) {
            return ClipboardRead.Empty.INSTANCE;
        }
        if (clip.getItems().isEmpty()) {
            return ClipboardRead.Empty.INSTANCE;
        }
        Iterator<T> it = clip.getItems().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            CharSequence charSequence = (CharSequence) next;
            if (!(charSequence == null || StringsKt.isBlank(charSequence))) {
                break;
            }
        }
        CharSequence charSequence2 = (CharSequence) next;
        if (charSequence2 == null || (string = charSequence2.toString()) == null) {
            return classifyBlank(clip);
        }
        return new ClipboardRead.Text(string);
    }

    private final ClipboardRead classifyBlank(ClipSnapshot clip) {
        Object next;
        Iterator<T> it = clip.getMimeTypes().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (!StringsKt.startsWith((String) next, "text/", true)) {
                break;
            }
        }
        String str = (String) next;
        if (str != null) {
            return new ClipboardRead.NonText(str);
        }
        return ClipboardRead.Empty.INSTANCE;
    }
}
