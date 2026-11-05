package org.cipherkey.data.database;

import androidx.room.InvalidationTracker;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* compiled from: CipherKeyDatabase_Impl.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0016J\"\u0010\u000f\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0011\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u00120\u0010H\u0014J\u0016\u0010\u0013\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u00110\u0014H\u0016J*\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00122\u001a\u0010\u0018\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0011\u0012\u0004\u0012\u00020\u00150\u0010H\u0016J\b\u0010\u0019\u001a\u00020\u0006H\u0016J\b\u0010\u001a\u001a\u00020\bH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/cipherkey/data/database/CipherKeyDatabase_Impl;", "Lorg/cipherkey/data/database/CipherKeyDatabase;", "<init>", "()V", "_contactDao", "Lkotlin/Lazy;", "Lorg/cipherkey/data/database/ContactDao;", "_sessionKeyDao", "Lorg/cipherkey/data/database/SessionKeyDao;", "createOpenDelegate", "Landroidx/room/RoomOpenDelegate;", "createInvalidationTracker", "Landroidx/room/InvalidationTracker;", "clearAllTables", "", "getRequiredTypeConverterClasses", "", "Lkotlin/reflect/KClass;", "", "getRequiredAutoMigrationSpecClasses", "", "Landroidx/room/migration/AutoMigrationSpec;", "createAutoMigrations", "Landroidx/room/migration/Migration;", "autoMigrationSpecs", "contactDao", "sessionKeyDao", "database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CipherKeyDatabase_Impl extends CipherKeyDatabase {
    private final Lazy<ContactDao> _contactDao = LazyKt.lazy(new Function0() { // from class: org.cipherkey.data.database.CipherKeyDatabase_Impl$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CipherKeyDatabase_Impl._contactDao$lambda$0(this.f$0);
        }
    });
    private final Lazy<SessionKeyDao> _sessionKeyDao = LazyKt.lazy(new Function0() { // from class: org.cipherkey.data.database.CipherKeyDatabase_Impl$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CipherKeyDatabase_Impl._sessionKeyDao$lambda$0(this.f$0);
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public static final ContactDao_Impl _contactDao$lambda$0(CipherKeyDatabase_Impl cipherKeyDatabase_Impl) {
        return new ContactDao_Impl(cipherKeyDatabase_Impl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SessionKeyDao_Impl _sessionKeyDao$lambda$0(CipherKeyDatabase_Impl cipherKeyDatabase_Impl) {
        return new SessionKeyDao_Impl(cipherKeyDatabase_Impl);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.room.RoomDatabase
    public RoomOpenDelegate createOpenDelegate() {
        return new RoomOpenDelegate() { // from class: org.cipherkey.data.database.CipherKeyDatabase_Impl$createOpenDelegate$_openDelegate$1
            @Override // androidx.room.RoomOpenDelegate
            public void onCreate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onPostMigrate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
            }

            {
                super(1, "c526ed85d86407fe4a7385c7c2595a82", "16ca7e1135f89b0a1343fc9de996bda1");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void createAllTables(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `contacts` (`id` TEXT NOT NULL, `displayName` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_contacts_displayName` ON `contacts` (`displayName`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `session_keys` (`sessionId` TEXT NOT NULL, `contactId` TEXT NOT NULL, `keyId` BLOB NOT NULL, `salt` BLOB NOT NULL, `memoryExponent` INTEGER NOT NULL, `iterations` INTEGER NOT NULL, `parallelism` INTEGER NOT NULL, `wrappedKey` BLOB, `createdAt` INTEGER NOT NULL, `lastUsedAt` INTEGER, PRIMARY KEY(`sessionId`), FOREIGN KEY(`contactId`) REFERENCES `contacts`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_session_keys_contactId` ON `session_keys` (`contactId`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_session_keys_keyId` ON `session_keys` (`keyId`)");
                SQLite.execSQL(connection, RoomMasterTable.CREATE_QUERY);
                SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c526ed85d86407fe4a7385c7c2595a82')");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void dropAllTables(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `contacts`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `session_keys`");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onOpen(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                SQLite.execSQL(connection, "PRAGMA foreign_keys = ON");
                this.this$0.internalInitInvalidationTracker(connection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onPreMigrate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                DBUtil.dropFtsSyncTriggers(connection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public RoomOpenDelegate.ValidationResult onValidateSchema(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
                linkedHashMap.put("displayName", new TableInfo.Column("displayName", "TEXT", true, 0, null, 1));
                linkedHashMap.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, 1));
                linkedHashMap.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, 1));
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                linkedHashSet2.add(new TableInfo.Index("index_contacts_displayName", false, CollectionsKt.listOf("displayName"), CollectionsKt.listOf("ASC")));
                TableInfo tableInfo = new TableInfo("contacts", linkedHashMap, linkedHashSet, linkedHashSet2);
                TableInfo tableInfo2 = TableInfo.INSTANCE.read(connection, "contacts");
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenDelegate.ValidationResult(false, "contacts(org.cipherkey.data.database.ContactEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                linkedHashMap2.put("sessionId", new TableInfo.Column("sessionId", "TEXT", true, 1, null, 1));
                linkedHashMap2.put("contactId", new TableInfo.Column("contactId", "TEXT", true, 0, null, 1));
                linkedHashMap2.put("keyId", new TableInfo.Column("keyId", "BLOB", true, 0, null, 1));
                linkedHashMap2.put("salt", new TableInfo.Column("salt", "BLOB", true, 0, null, 1));
                linkedHashMap2.put("memoryExponent", new TableInfo.Column("memoryExponent", "INTEGER", true, 0, null, 1));
                linkedHashMap2.put("iterations", new TableInfo.Column("iterations", "INTEGER", true, 0, null, 1));
                linkedHashMap2.put("parallelism", new TableInfo.Column("parallelism", "INTEGER", true, 0, null, 1));
                linkedHashMap2.put("wrappedKey", new TableInfo.Column("wrappedKey", "BLOB", false, 0, null, 1));
                linkedHashMap2.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, 1));
                linkedHashMap2.put("lastUsedAt", new TableInfo.Column("lastUsedAt", "INTEGER", false, 0, null, 1));
                LinkedHashSet linkedHashSet3 = new LinkedHashSet();
                linkedHashSet3.add(new TableInfo.ForeignKey("contacts", "CASCADE", "NO ACTION", CollectionsKt.listOf("contactId"), CollectionsKt.listOf("id")));
                LinkedHashSet linkedHashSet4 = new LinkedHashSet();
                linkedHashSet4.add(new TableInfo.Index("index_session_keys_contactId", false, CollectionsKt.listOf("contactId"), CollectionsKt.listOf("ASC")));
                linkedHashSet4.add(new TableInfo.Index("index_session_keys_keyId", true, CollectionsKt.listOf("keyId"), CollectionsKt.listOf("ASC")));
                TableInfo tableInfo3 = new TableInfo("session_keys", linkedHashMap2, linkedHashSet3, linkedHashSet4);
                TableInfo tableInfo4 = TableInfo.INSTANCE.read(connection, "session_keys");
                if (!tableInfo3.equals(tableInfo4)) {
                    return new RoomOpenDelegate.ValidationResult(false, "session_keys(org.cipherkey.data.database.SessionKeyEntity).\n Expected:\n" + tableInfo3 + "\n Found:\n" + tableInfo4);
                }
                return new RoomOpenDelegate.ValidationResult(true, null);
            }
        };
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new LinkedHashMap(), new LinkedHashMap(), "contacts", "session_keys");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.performClear(true, "contacts", "session_keys");
    }

    @Override // androidx.room.RoomDatabase
    protected Map<KClass<?>, List<KClass<?>>> getRequiredTypeConverterClasses() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(Reflection.getOrCreateKotlinClass(ContactDao.class), ContactDao_Impl.INSTANCE.getRequiredConverters());
        linkedHashMap.put(Reflection.getOrCreateKotlinClass(SessionKeyDao.class), SessionKeyDao_Impl.INSTANCE.getRequiredConverters());
        return linkedHashMap;
    }

    @Override // androidx.room.RoomDatabase
    public Set<KClass<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecClasses() {
        return new LinkedHashSet();
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> createAutoMigrations(Map<KClass<? extends AutoMigrationSpec>, ? extends AutoMigrationSpec> autoMigrationSpecs) {
        Intrinsics.checkNotNullParameter(autoMigrationSpecs, "autoMigrationSpecs");
        return new ArrayList();
    }

    @Override // org.cipherkey.data.database.CipherKeyDatabase
    public ContactDao contactDao() {
        return this._contactDao.getValue();
    }

    @Override // org.cipherkey.data.database.CipherKeyDatabase
    public SessionKeyDao sessionKeyDao() {
        return this._sessionKeyDao.getValue();
    }
}
