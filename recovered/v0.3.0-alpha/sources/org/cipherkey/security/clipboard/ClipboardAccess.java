package org.cipherkey.security.clipboard;

import kotlin.Metadata;
import org.bouncycastle.i18n.TextBundle;

/* compiled from: ClipboardAccess.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lorg/cipherkey/security/clipboard/ClipboardAccess;", "", "read", "Lorg/cipherkey/security/clipboard/ClipboardRead;", "currentText", "", "setText", "", TextBundle.TEXT_ENTRY, "clipboard_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface ClipboardAccess {
    String currentText();

    ClipboardRead read();

    boolean setText(String text);
}
