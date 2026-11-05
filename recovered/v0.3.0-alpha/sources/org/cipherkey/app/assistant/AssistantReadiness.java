package org.cipherkey.app.assistant;

import kotlin.Metadata;

/* compiled from: AssistantReadiness.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\nB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u0016\u0010\t\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007¨\u0006\u000b"}, d2 = {"Lorg/cipherkey/app/assistant/AssistantReadiness;", "", "<init>", "()V", "onToggle", "Lorg/cipherkey/app/assistant/AssistantReadiness$Action;", "enabled", "", "overlayGranted", "shouldAutoStart", "Action", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AssistantReadiness {
    public static final int $stable = 0;
    public static final AssistantReadiness INSTANCE = new AssistantReadiness();

    public final boolean shouldAutoStart(boolean enabled, boolean overlayGranted) {
        return enabled && overlayGranted;
    }

    private AssistantReadiness() {
    }

    /* compiled from: AssistantReadiness.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0003\u0005\u0006\u0007¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lorg/cipherkey/app/assistant/AssistantReadiness$Action;", "", "StartService", "StopService", "RequestOverlayPermission", "Lorg/cipherkey/app/assistant/AssistantReadiness$Action$RequestOverlayPermission;", "Lorg/cipherkey/app/assistant/AssistantReadiness$Action$StartService;", "Lorg/cipherkey/app/assistant/AssistantReadiness$Action$StopService;", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Action {

        /* compiled from: AssistantReadiness.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/app/assistant/AssistantReadiness$Action$StartService;", "Lorg/cipherkey/app/assistant/AssistantReadiness$Action;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class StartService implements Action {
            public static final int $stable = 0;
            public static final StartService INSTANCE = new StartService();

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof StartService)) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                return -1143540790;
            }

            public String toString() {
                return "StartService";
            }

            private StartService() {
            }
        }

        /* compiled from: AssistantReadiness.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/app/assistant/AssistantReadiness$Action$StopService;", "Lorg/cipherkey/app/assistant/AssistantReadiness$Action;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class StopService implements Action {
            public static final int $stable = 0;
            public static final StopService INSTANCE = new StopService();

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof StopService)) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                return 1295560092;
            }

            public String toString() {
                return "StopService";
            }

            private StopService() {
            }
        }

        /* compiled from: AssistantReadiness.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/app/assistant/AssistantReadiness$Action$RequestOverlayPermission;", "Lorg/cipherkey/app/assistant/AssistantReadiness$Action;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class RequestOverlayPermission implements Action {
            public static final int $stable = 0;
            public static final RequestOverlayPermission INSTANCE = new RequestOverlayPermission();

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof RequestOverlayPermission)) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                return 323764423;
            }

            public String toString() {
                return "RequestOverlayPermission";
            }

            private RequestOverlayPermission() {
            }
        }
    }

    public final Action onToggle(boolean enabled, boolean overlayGranted) {
        if (!enabled) {
            return Action.StopService.INSTANCE;
        }
        if (overlayGranted) {
            return Action.StartService.INSTANCE;
        }
        return Action.RequestOverlayPermission.INSTANCE;
    }
}
