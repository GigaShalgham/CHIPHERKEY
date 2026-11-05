package org.cipherkey.data.repository;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.cipherkey.core.common.DispatcherProvider;
import org.cipherkey.core.crypto.Argon2KeyDerivator;
import org.cipherkey.core.crypto.CryptoProvider;
import org.cipherkey.core.crypto.ProtocolRandom;
import org.cipherkey.core.keys.KeyProtector;
import org.cipherkey.core.sessions.KeySessionManager;
import org.cipherkey.core.sessions.TimeSource;
import org.cipherkey.data.database.ContactDao;
import org.cipherkey.data.database.SessionKeyDao;
import org.cipherkey.data.preferences.AppSettings;
import org.cipherkey.data.preferences.SettingsRepository;

/* compiled from: DiModules.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J(\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\rH\u0007J@\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fH\u0007¨\u0006$"}, d2 = {"Lorg/cipherkey/data/repository/RepositoryModule;", "", "<init>", "()V", "provideSettingsRepository", "Lorg/cipherkey/data/preferences/SettingsRepository;", "context", "Landroid/content/Context;", "provideContactRepository", "Lorg/cipherkey/data/repository/ContactRepository;", "contactDao", "Lorg/cipherkey/data/database/ContactDao;", "timeSource", "Lorg/cipherkey/core/sessions/TimeSource;", "dispatchers", "Lorg/cipherkey/core/common/DispatcherProvider;", "provideSessionKeyRepository", "Lorg/cipherkey/data/repository/SessionKeyRepository;", "sessionKeyDao", "Lorg/cipherkey/data/database/SessionKeyDao;", "random", "Lorg/cipherkey/core/crypto/ProtocolRandom;", "provideKeySessionManager", "Lorg/cipherkey/core/sessions/KeySessionManager;", "derivator", "Lorg/cipherkey/core/crypto/Argon2KeyDerivator;", "provideMessageCipher", "Lorg/cipherkey/data/repository/MessageCipher;", "contacts", "sessionKeys", "sessions", "protector", "Lorg/cipherkey/core/keys/KeyProtector;", "provider", "Lorg/cipherkey/core/crypto/CryptoProvider;", "settings", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
/* loaded from: classes3.dex */
public final class RepositoryModule {
    public static final RepositoryModule INSTANCE = new RepositoryModule();

    private RepositoryModule() {
    }

    @Provides
    @Singleton
    public final SettingsRepository provideSettingsRepository(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new SettingsRepository(SettingsRepository.INSTANCE.defaultDataStore(context));
    }

    @Provides
    @Singleton
    public final ContactRepository provideContactRepository(ContactDao contactDao, TimeSource timeSource, DispatcherProvider dispatchers) {
        Intrinsics.checkNotNullParameter(contactDao, "contactDao");
        Intrinsics.checkNotNullParameter(timeSource, "timeSource");
        Intrinsics.checkNotNullParameter(dispatchers, "dispatchers");
        return new ContactRepository(contactDao, timeSource, dispatchers);
    }

    @Provides
    @Singleton
    public final SessionKeyRepository provideSessionKeyRepository(SessionKeyDao sessionKeyDao, ProtocolRandom random, TimeSource timeSource, DispatcherProvider dispatchers) {
        Intrinsics.checkNotNullParameter(sessionKeyDao, "sessionKeyDao");
        Intrinsics.checkNotNullParameter(random, "random");
        Intrinsics.checkNotNullParameter(timeSource, "timeSource");
        Intrinsics.checkNotNullParameter(dispatchers, "dispatchers");
        return new SessionKeyRepository(sessionKeyDao, random, timeSource, dispatchers);
    }

    @Provides
    @Singleton
    public final KeySessionManager provideKeySessionManager(Argon2KeyDerivator derivator, TimeSource timeSource) {
        Intrinsics.checkNotNullParameter(derivator, "derivator");
        Intrinsics.checkNotNullParameter(timeSource, "timeSource");
        return new KeySessionManager(derivator, timeSource, null, 4, null);
    }

    @Provides
    @Singleton
    public final MessageCipher provideMessageCipher(ContactRepository contacts, SessionKeyRepository sessionKeys, KeySessionManager sessions, KeyProtector protector, CryptoProvider provider, SettingsRepository settings, DispatcherProvider dispatchers) {
        Intrinsics.checkNotNullParameter(contacts, "contacts");
        Intrinsics.checkNotNullParameter(sessionKeys, "sessionKeys");
        Intrinsics.checkNotNullParameter(sessions, "sessions");
        Intrinsics.checkNotNullParameter(protector, "protector");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(dispatchers, "dispatchers");
        return new MessageCipher(contacts, sessionKeys, sessions, protector, provider, new AnonymousClass1(settings), dispatchers);
    }

    /* compiled from: DiModules.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* renamed from: org.cipherkey.data.repository.RepositoryModule$provideMessageCipher$1, reason: invalid class name */
    static final class AnonymousClass1 implements SessionTimeoutSource {
        final /* synthetic */ SettingsRepository $settings;

        AnonymousClass1(SettingsRepository settingsRepository) {
            this.$settings = settingsRepository;
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
        @Override // org.cipherkey.data.repository.SessionTimeoutSource
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object timeoutMillis(Continuation<? super Long> continuation) {
            RepositoryModule$provideMessageCipher$1$timeoutMillis$1 repositoryModule$provideMessageCipher$1$timeoutMillis$1;
            if (continuation instanceof RepositoryModule$provideMessageCipher$1$timeoutMillis$1) {
                repositoryModule$provideMessageCipher$1$timeoutMillis$1 = (RepositoryModule$provideMessageCipher$1$timeoutMillis$1) continuation;
                if ((repositoryModule$provideMessageCipher$1$timeoutMillis$1.label & Integer.MIN_VALUE) != 0) {
                    repositoryModule$provideMessageCipher$1$timeoutMillis$1.label -= Integer.MIN_VALUE;
                } else {
                    repositoryModule$provideMessageCipher$1$timeoutMillis$1 = new RepositoryModule$provideMessageCipher$1$timeoutMillis$1(this, continuation);
                }
            }
            Object objFirst = repositoryModule$provideMessageCipher$1$timeoutMillis$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = repositoryModule$provideMessageCipher$1$timeoutMillis$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(objFirst);
                Flow<AppSettings> settings = this.$settings.getSettings();
                repositoryModule$provideMessageCipher$1$timeoutMillis$1.label = 1;
                objFirst = FlowKt.first(settings, repositoryModule$provideMessageCipher$1$timeoutMillis$1);
                if (objFirst == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(objFirst);
            }
            return Boxing.boxLong(((AppSettings) objFirst).getSessionTimeoutMillis());
        }
    }
}
