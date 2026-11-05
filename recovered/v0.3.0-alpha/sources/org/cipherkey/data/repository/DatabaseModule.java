package org.cipherkey.data.repository;

import android.content.Context;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.data.database.CipherKeyDatabase;
import org.cipherkey.data.database.ContactDao;
import org.cipherkey.data.database.SessionKeyDao;

/* compiled from: DiModules.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u0005H\u0007¨\u0006\r"}, d2 = {"Lorg/cipherkey/data/repository/DatabaseModule;", "", "<init>", "()V", "provideDatabase", "Lorg/cipherkey/data/database/CipherKeyDatabase;", "context", "Landroid/content/Context;", "provideContactDao", "Lorg/cipherkey/data/database/ContactDao;", "database", "provideSessionKeyDao", "Lorg/cipherkey/data/database/SessionKeyDao;", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
/* loaded from: classes3.dex */
public final class DatabaseModule {
    public static final DatabaseModule INSTANCE = new DatabaseModule();

    private DatabaseModule() {
    }

    @Provides
    @Singleton
    public final CipherKeyDatabase provideDatabase(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (CipherKeyDatabase) Room.databaseBuilder(context, CipherKeyDatabase.class, CipherKeyDatabase.NAME).build();
    }

    @Provides
    public final ContactDao provideContactDao(CipherKeyDatabase database) {
        Intrinsics.checkNotNullParameter(database, "database");
        return database.contactDao();
    }

    @Provides
    public final SessionKeyDao provideSessionKeyDao(CipherKeyDatabase database) {
        Intrinsics.checkNotNullParameter(database, "database");
        return database.sessionKeyDao();
    }
}
