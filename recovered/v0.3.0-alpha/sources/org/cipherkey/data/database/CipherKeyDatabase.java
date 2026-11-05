package org.cipherkey.data.database;

import androidx.room.RoomDatabase;
import kotlin.Metadata;

/* compiled from: CipherKeyDatabase.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lorg/cipherkey/data/database/CipherKeyDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "contactDao", "Lorg/cipherkey/data/database/ContactDao;", "sessionKeyDao", "Lorg/cipherkey/data/database/SessionKeyDao;", "Companion", "database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class CipherKeyDatabase extends RoomDatabase {
    public static final String NAME = "cipherkey.db";

    public abstract ContactDao contactDao();

    public abstract SessionKeyDao sessionKeyDao();
}
