package org.cipherkey.core.sessions;

import kotlin.Metadata;

/* compiled from: TimeSource.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0005À\u0006\u0003"}, d2 = {"Lorg/cipherkey/core/sessions/TimeSource;", "", "nowMillis", "", "Companion", "sessions"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface TimeSource {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    long nowMillis();

    /* compiled from: TimeSource.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/cipherkey/core/sessions/TimeSource$Companion;", "", "<init>", "()V", "SYSTEM", "Lorg/cipherkey/core/sessions/TimeSource;", "getSYSTEM", "()Lorg/cipherkey/core/sessions/TimeSource;", "sessions"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final TimeSource SYSTEM = new TimeSource() { // from class: org.cipherkey.core.sessions.TimeSource$Companion$$ExternalSyntheticLambda0
            @Override // org.cipherkey.core.sessions.TimeSource
            public final long nowMillis() {
                return System.currentTimeMillis();
            }
        };

        private Companion() {
        }

        public final TimeSource getSYSTEM() {
            return SYSTEM;
        }
    }
}
