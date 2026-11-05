package org.cipherkey.data.repository;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.core.common.DispatcherProvider;
import org.cipherkey.core.crypto.AeadCipher;
import org.cipherkey.core.crypto.AesGcmAeadCipher;
import org.cipherkey.core.crypto.Argon2KeyDerivator;
import org.cipherkey.core.crypto.CryptoProvider;
import org.cipherkey.core.crypto.PassphraseCryptoProvider;
import org.cipherkey.core.crypto.ProtocolRandom;
import org.cipherkey.core.crypto.RandomSource;
import org.cipherkey.core.sessions.TimeSource;

/* compiled from: DiModules.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007J\b\u0010\u0006\u001a\u00020\u0007H\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0007H\u0007J\b\u0010\f\u001a\u00020\rH\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0007J\b\u0010\u0010\u001a\u00020\u0011H\u0007¨\u0006\u0012"}, d2 = {"Lorg/cipherkey/data/repository/CryptoModule;", "", "<init>", "()V", "provideAeadCipher", "Lorg/cipherkey/core/crypto/AeadCipher;", "provideProtocolRandom", "Lorg/cipherkey/core/crypto/ProtocolRandom;", "provideCryptoProvider", "Lorg/cipherkey/core/crypto/CryptoProvider;", "aead", "random", "provideArgon2KeyDerivator", "Lorg/cipherkey/core/crypto/Argon2KeyDerivator;", "provideTimeSource", "Lorg/cipherkey/core/sessions/TimeSource;", "provideDispatcherProvider", "Lorg/cipherkey/core/common/DispatcherProvider;", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
/* loaded from: classes3.dex */
public final class CryptoModule {
    public static final CryptoModule INSTANCE = new CryptoModule();

    private CryptoModule() {
    }

    @Provides
    @Singleton
    public final AeadCipher provideAeadCipher() {
        return new AesGcmAeadCipher();
    }

    @Provides
    @Singleton
    public final ProtocolRandom provideProtocolRandom() {
        return new ProtocolRandom(RandomSource.INSTANCE.getSECURE());
    }

    @Provides
    @Singleton
    public final CryptoProvider provideCryptoProvider(AeadCipher aead, ProtocolRandom random) {
        Intrinsics.checkNotNullParameter(aead, "aead");
        Intrinsics.checkNotNullParameter(random, "random");
        return new PassphraseCryptoProvider(aead, random);
    }

    @Provides
    @Singleton
    public final Argon2KeyDerivator provideArgon2KeyDerivator() {
        return new Argon2KeyDerivator();
    }

    @Provides
    @Singleton
    public final TimeSource provideTimeSource() {
        return TimeSource.INSTANCE.getSYSTEM();
    }

    @Provides
    @Singleton
    public final DispatcherProvider provideDispatcherProvider() {
        return new DispatcherProvider(null, null, null, 7, null);
    }
}
