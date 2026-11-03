package org.cipherkey.data.repository

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import org.cipherkey.core.common.DispatcherProvider
import org.cipherkey.core.crypto.AeadCipher
import org.cipherkey.core.crypto.AesGcmAeadCipher
import org.cipherkey.core.crypto.Argon2KeyDerivator
import org.cipherkey.core.crypto.CryptoProvider
import org.cipherkey.core.crypto.PassphraseCryptoProvider
import org.cipherkey.core.crypto.ProtocolRandom
import org.cipherkey.core.crypto.RandomSource
import org.cipherkey.core.sessions.KeySessionManager
import org.cipherkey.core.sessions.TimeSource
import org.cipherkey.data.database.CipherKeyDatabase
import org.cipherkey.data.database.ContactDao
import org.cipherkey.data.database.SessionKeyDao
import org.cipherkey.data.preferences.SettingsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CipherKeyDatabase =
        Room.databaseBuilder(context, CipherKeyDatabase::class.java, CipherKeyDatabase.NAME)
            .build()

    @Provides
    fun provideContactDao(database: CipherKeyDatabase): ContactDao = database.contactDao()

    @Provides
    fun provideSessionKeyDao(database: CipherKeyDatabase): SessionKeyDao = database.sessionKeyDao()
}

@Module
@InstallIn(SingletonComponent::class)
object CryptoModule {

    @Provides
    @Singleton
    fun provideAeadCipher(): AeadCipher = AesGcmAeadCipher()

    @Provides
    @Singleton
    fun provideProtocolRandom(): ProtocolRandom = ProtocolRandom(RandomSource.SECURE)

    @Provides
    @Singleton
    fun provideCryptoProvider(
        aead: AeadCipher,
        random: ProtocolRandom,
    ): CryptoProvider = PassphraseCryptoProvider(aead, random)

    @Provides
    @Singleton
    fun provideArgon2KeyDerivator(): Argon2KeyDerivator = Argon2KeyDerivator()

    @Provides
    @Singleton
    fun provideTimeSource(): TimeSource = TimeSource.SYSTEM

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider = DispatcherProvider()
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSettingsRepository(@ApplicationContext context: Context): SettingsRepository =
        SettingsRepository(SettingsRepository.defaultDataStore(context))

    @Provides
    @Singleton
    fun provideContactRepository(
        contactDao: ContactDao,
        timeSource: TimeSource,
        dispatchers: DispatcherProvider,
    ): ContactRepository = ContactRepository(contactDao, timeSource, dispatchers)

    @Provides
    @Singleton
    fun provideSessionKeyRepository(
        sessionKeyDao: SessionKeyDao,
        random: ProtocolRandom,
        timeSource: TimeSource,
        dispatchers: DispatcherProvider,
    ): SessionKeyRepository = SessionKeyRepository(sessionKeyDao, random, timeSource, dispatchers)

    @Provides
    @Singleton
    fun provideKeySessionManager(
        derivator: Argon2KeyDerivator,
        timeSource: TimeSource,
    ): KeySessionManager = KeySessionManager(derivator, timeSource)

    @Provides
    @Singleton
    fun provideMessageCipher(
        contacts: ContactRepository,
        sessionKeys: SessionKeyRepository,
        sessions: KeySessionManager,
        protector: org.cipherkey.core.keys.KeyProtector,
        provider: CryptoProvider,
        settings: SettingsRepository,
        dispatchers: DispatcherProvider,
    ): MessageCipher = MessageCipher(
        contacts = contacts,
        sessionKeys = sessionKeys,
        sessions = sessions,
        protector = protector,
        provider = provider,
        sessionTimeout = {
            settings.settings.first().sessionTimeoutMillis
        },
        dispatchers = dispatchers,
    )
}
