package org.cipherkey.security.policies;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.CharsKt;

/* compiled from: KeyPolicy.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0019\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bJ\u000e\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\bJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lorg/cipherkey/security/policies/KeyPolicy;", "", "<init>", "()V", "MIN_PASSPHRASE_CHARS", "", "MAX_PASSPHRASE_CHARS", "DEFAULT_SESSION_TIMEOUT_MILLIS", "", "MIN_SESSION_TIMEOUT_MILLIS", "MAX_SESSION_TIMEOUT_MILLIS", "TIMEOUT_CHOICES_MILLIS", "", "getTIMEOUT_CHOICES_MILLIS", "()Ljava/util/List;", "nearestTimeoutChoice", "millis", "clampSessionTimeout", "requestedMillis", "passphraseProblem", "", "passphrase", "", "policies"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class KeyPolicy {
    public static final int MAX_PASSPHRASE_CHARS = 1024;
    public static final long MAX_SESSION_TIMEOUT_MILLIS = 86400000;
    public static final int MIN_PASSPHRASE_CHARS = 8;
    public static final KeyPolicy INSTANCE = new KeyPolicy();
    public static final long MIN_SESSION_TIMEOUT_MILLIS = 60000;
    public static final long DEFAULT_SESSION_TIMEOUT_MILLIS = 900000;
    private static final List<Long> TIMEOUT_CHOICES_MILLIS = CollectionsKt.listOf((Object[]) new Long[]{Long.valueOf(MIN_SESSION_TIMEOUT_MILLIS), 300000L, Long.valueOf(DEFAULT_SESSION_TIMEOUT_MILLIS), 3600000L, 14400000L, 43200000L, 86400000L});

    private KeyPolicy() {
    }

    public final List<Long> getTIMEOUT_CHOICES_MILLIS() {
        return TIMEOUT_CHOICES_MILLIS;
    }

    public final long nearestTimeoutChoice(long millis) {
        Iterator<T> it = TIMEOUT_CHOICES_MILLIS.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        Object next = it.next();
        if (it.hasNext()) {
            long jAbs = Math.abs(((Number) next).longValue() - INSTANCE.clampSessionTimeout(millis));
            do {
                Object next2 = it.next();
                long jAbs2 = Math.abs(((Number) next2).longValue() - INSTANCE.clampSessionTimeout(millis));
                if (jAbs > jAbs2) {
                    next = next2;
                    jAbs = jAbs2;
                }
            } while (it.hasNext());
        }
        return ((Number) next).longValue();
    }

    public final long clampSessionTimeout(long requestedMillis) {
        return RangesKt.coerceIn(requestedMillis, MIN_SESSION_TIMEOUT_MILLIS, 86400000L);
    }

    public final String passphraseProblem(char[] passphrase) {
        Intrinsics.checkNotNullParameter(passphrase, "passphrase");
        if (passphrase.length < 8) {
            return "Passphrase must be at least 8 characters";
        }
        if (passphrase.length > 1024) {
            return "Passphrase must be at most 1024 characters";
        }
        for (char c : passphrase) {
            if (!CharsKt.isWhitespace(c)) {
                return null;
            }
        }
        return "Passphrase must not consist only of whitespace";
    }
}
