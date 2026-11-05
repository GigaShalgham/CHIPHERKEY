package org.cipherkey.data.database;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.EntityUpsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.coroutines.flow.Flow;

/* compiled from: SessionKeyDao_Impl.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u0000 $2\u00020\u0001:\u0001$B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0096@¢\u0006\u0002\u0010\u0017J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0019\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0013J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0013J(\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u00112\b\u0010\u001d\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001e\u001a\u00020\u001fH\u0096@¢\u0006\u0002\u0010 J\u001e\u0010!\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001fH\u0096@¢\u0006\u0002\u0010\"J\u0016\u0010#\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lorg/cipherkey/data/database/SessionKeyDao_Impl;", "Lorg/cipherkey/data/database/SessionKeyDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__upsertAdapterOfSessionKeyEntity", "Landroidx/room/EntityUpsertAdapter;", "Lorg/cipherkey/data/database/SessionKeyEntity;", "upsert", "", "session", "(Lorg/cipherkey/data/database/SessionKeyEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeByContact", "Lkotlinx/coroutines/flow/Flow;", "", "contactId", "", "latestByContact", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "byKeyId", "keyId", "", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bySessionId", "sessionId", "countByContact", "", "updateWrappedKey", "wrapped", "now", "", "(Ljava/lang/String;[BJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "touch", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteByContact", "Companion", "database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SessionKeyDao_Impl implements SessionKeyDao {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final RoomDatabase __db;
    private final EntityUpsertAdapter<SessionKeyEntity> __upsertAdapterOfSessionKeyEntity;

    public SessionKeyDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__upsertAdapterOfSessionKeyEntity = new EntityUpsertAdapter<>(new EntityInsertAdapter<SessionKeyEntity>() { // from class: org.cipherkey.data.database.SessionKeyDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT INTO `session_keys` (`sessionId`,`contactId`,`keyId`,`salt`,`memoryExponent`,`iterations`,`parallelism`,`wrappedKey`,`createdAt`,`lastUsedAt`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, SessionKeyEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8595bindText(1, entity.getSessionId());
                statement.mo8595bindText(2, entity.getContactId());
                statement.mo8591bindBlob(3, entity.getKeyId());
                statement.mo8591bindBlob(4, entity.getSalt());
                statement.mo8593bindLong(5, entity.getMemoryExponent());
                statement.mo8593bindLong(6, entity.getIterations());
                statement.mo8593bindLong(7, entity.getParallelism());
                byte[] wrappedKey = entity.getWrappedKey();
                if (wrappedKey == null) {
                    statement.mo8594bindNull(8);
                } else {
                    statement.mo8591bindBlob(8, wrappedKey);
                }
                statement.mo8593bindLong(9, entity.getCreatedAt());
                Long lastUsedAt = entity.getLastUsedAt();
                if (lastUsedAt == null) {
                    statement.mo8594bindNull(10);
                } else {
                    statement.mo8593bindLong(10, lastUsedAt.longValue());
                }
            }
        }, new EntityDeleteOrUpdateAdapter<SessionKeyEntity>() { // from class: org.cipherkey.data.database.SessionKeyDao_Impl.2
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "UPDATE `session_keys` SET `sessionId` = ?,`contactId` = ?,`keyId` = ?,`salt` = ?,`memoryExponent` = ?,`iterations` = ?,`parallelism` = ?,`wrappedKey` = ?,`createdAt` = ?,`lastUsedAt` = ? WHERE `sessionId` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, SessionKeyEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8595bindText(1, entity.getSessionId());
                statement.mo8595bindText(2, entity.getContactId());
                statement.mo8591bindBlob(3, entity.getKeyId());
                statement.mo8591bindBlob(4, entity.getSalt());
                statement.mo8593bindLong(5, entity.getMemoryExponent());
                statement.mo8593bindLong(6, entity.getIterations());
                statement.mo8593bindLong(7, entity.getParallelism());
                byte[] wrappedKey = entity.getWrappedKey();
                if (wrappedKey == null) {
                    statement.mo8594bindNull(8);
                } else {
                    statement.mo8591bindBlob(8, wrappedKey);
                }
                statement.mo8593bindLong(9, entity.getCreatedAt());
                Long lastUsedAt = entity.getLastUsedAt();
                if (lastUsedAt == null) {
                    statement.mo8594bindNull(10);
                } else {
                    statement.mo8593bindLong(10, lastUsedAt.longValue());
                }
                statement.mo8595bindText(11, entity.getSessionId());
            }
        });
    }

    @Override // org.cipherkey.data.database.SessionKeyDao
    public Object upsert(final SessionKeyEntity sessionKeyEntity, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: org.cipherkey.data.database.SessionKeyDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SessionKeyDao_Impl.upsert$lambda$0(this.f$0, sessionKeyEntity, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit upsert$lambda$0(SessionKeyDao_Impl sessionKeyDao_Impl, SessionKeyEntity sessionKeyEntity, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        sessionKeyDao_Impl.__upsertAdapterOfSessionKeyEntity.upsert(_connection, (SQLiteConnection) sessionKeyEntity);
        return Unit.INSTANCE;
    }

    @Override // org.cipherkey.data.database.SessionKeyDao
    public Flow<List<SessionKeyEntity>> observeByContact(final String contactId) {
        Intrinsics.checkNotNullParameter(contactId, "contactId");
        final String str = "SELECT * FROM session_keys WHERE contactId = ? ORDER BY createdAt DESC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"session_keys"}, new Function1() { // from class: org.cipherkey.data.database.SessionKeyDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SessionKeyDao_Impl.observeByContact$lambda$0(str, contactId, (SQLiteConnection) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List observeByContact$lambda$0(String str, String str2, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo8595bindText(1, str2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sessionId");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "contactId");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "keyId");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "salt");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "memoryExponent");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "iterations");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "parallelism");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wrappedKey");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "createdAt");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "lastUsedAt");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                int i = columnIndexOrThrow2;
                int i2 = columnIndexOrThrow3;
                arrayList.add(new SessionKeyEntity(sQLiteStatementPrepare.getText(columnIndexOrThrow), sQLiteStatementPrepare.getText(columnIndexOrThrow2), sQLiteStatementPrepare.getBlob(columnIndexOrThrow3), sQLiteStatementPrepare.getBlob(columnIndexOrThrow4), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7), sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getBlob(columnIndexOrThrow8), sQLiteStatementPrepare.getLong(columnIndexOrThrow9), sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow10))));
                columnIndexOrThrow2 = i;
                columnIndexOrThrow3 = i2;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // org.cipherkey.data.database.SessionKeyDao
    public Object latestByContact(final String str, Continuation<? super SessionKeyEntity> continuation) {
        final String str2 = "SELECT * FROM session_keys WHERE contactId = ? ORDER BY createdAt DESC LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: org.cipherkey.data.database.SessionKeyDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SessionKeyDao_Impl.latestByContact$lambda$0(str2, str, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SessionKeyEntity latestByContact$lambda$0(String str, String str2, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo8595bindText(1, str2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sessionId");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "contactId");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "keyId");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "salt");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "memoryExponent");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "iterations");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "parallelism");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wrappedKey");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "createdAt");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "lastUsedAt");
            SessionKeyEntity sessionKeyEntity = null;
            if (sQLiteStatementPrepare.step()) {
                sessionKeyEntity = new SessionKeyEntity(sQLiteStatementPrepare.getText(columnIndexOrThrow), sQLiteStatementPrepare.getText(columnIndexOrThrow2), sQLiteStatementPrepare.getBlob(columnIndexOrThrow3), sQLiteStatementPrepare.getBlob(columnIndexOrThrow4), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7), sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getBlob(columnIndexOrThrow8), sQLiteStatementPrepare.getLong(columnIndexOrThrow9), sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow10)));
            }
            return sessionKeyEntity;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // org.cipherkey.data.database.SessionKeyDao
    public Object byKeyId(final byte[] bArr, Continuation<? super SessionKeyEntity> continuation) {
        final String str = "SELECT * FROM session_keys WHERE keyId = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: org.cipherkey.data.database.SessionKeyDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SessionKeyDao_Impl.byKeyId$lambda$0(str, bArr, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SessionKeyEntity byKeyId$lambda$0(String str, byte[] bArr, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo8591bindBlob(1, bArr);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sessionId");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "contactId");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "keyId");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "salt");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "memoryExponent");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "iterations");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "parallelism");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wrappedKey");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "createdAt");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "lastUsedAt");
            SessionKeyEntity sessionKeyEntity = null;
            if (sQLiteStatementPrepare.step()) {
                sessionKeyEntity = new SessionKeyEntity(sQLiteStatementPrepare.getText(columnIndexOrThrow), sQLiteStatementPrepare.getText(columnIndexOrThrow2), sQLiteStatementPrepare.getBlob(columnIndexOrThrow3), sQLiteStatementPrepare.getBlob(columnIndexOrThrow4), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7), sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getBlob(columnIndexOrThrow8), sQLiteStatementPrepare.getLong(columnIndexOrThrow9), sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow10)));
            }
            return sessionKeyEntity;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // org.cipherkey.data.database.SessionKeyDao
    public Object bySessionId(final String str, Continuation<? super SessionKeyEntity> continuation) {
        final String str2 = "SELECT * FROM session_keys WHERE sessionId = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: org.cipherkey.data.database.SessionKeyDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SessionKeyDao_Impl.bySessionId$lambda$0(str2, str, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SessionKeyEntity bySessionId$lambda$0(String str, String str2, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo8595bindText(1, str2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sessionId");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "contactId");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "keyId");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "salt");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "memoryExponent");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "iterations");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "parallelism");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wrappedKey");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "createdAt");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "lastUsedAt");
            SessionKeyEntity sessionKeyEntity = null;
            if (sQLiteStatementPrepare.step()) {
                sessionKeyEntity = new SessionKeyEntity(sQLiteStatementPrepare.getText(columnIndexOrThrow), sQLiteStatementPrepare.getText(columnIndexOrThrow2), sQLiteStatementPrepare.getBlob(columnIndexOrThrow3), sQLiteStatementPrepare.getBlob(columnIndexOrThrow4), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7), sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getBlob(columnIndexOrThrow8), sQLiteStatementPrepare.getLong(columnIndexOrThrow9), sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow10)));
            }
            return sessionKeyEntity;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // org.cipherkey.data.database.SessionKeyDao
    public Object countByContact(final String str, Continuation<? super Integer> continuation) {
        final String str2 = "SELECT COUNT(*) FROM session_keys WHERE contactId = ?";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: org.cipherkey.data.database.SessionKeyDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(SessionKeyDao_Impl.countByContact$lambda$0(str2, str, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int countByContact$lambda$0(String str, String str2, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo8595bindText(1, str2);
            return sQLiteStatementPrepare.step() ? (int) sQLiteStatementPrepare.getLong(0) : 0;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // org.cipherkey.data.database.SessionKeyDao
    public Object updateWrappedKey(final String str, final byte[] bArr, final long j, Continuation<? super Unit> continuation) {
        final String str2 = "UPDATE session_keys SET wrappedKey = ?, lastUsedAt = ? WHERE sessionId = ?";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: org.cipherkey.data.database.SessionKeyDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SessionKeyDao_Impl.updateWrappedKey$lambda$0(str2, bArr, j, str, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit updateWrappedKey$lambda$0(String str, byte[] bArr, long j, String str2, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            if (bArr == null) {
                sQLiteStatementPrepare.mo8594bindNull(1);
            } else {
                sQLiteStatementPrepare.mo8591bindBlob(1, bArr);
            }
            sQLiteStatementPrepare.mo8593bindLong(2, j);
            sQLiteStatementPrepare.mo8595bindText(3, str2);
            sQLiteStatementPrepare.step();
            sQLiteStatementPrepare.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            sQLiteStatementPrepare.close();
            throw th;
        }
    }

    @Override // org.cipherkey.data.database.SessionKeyDao
    public Object touch(final String str, final long j, Continuation<? super Unit> continuation) {
        final String str2 = "UPDATE session_keys SET lastUsedAt = ? WHERE sessionId = ?";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: org.cipherkey.data.database.SessionKeyDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SessionKeyDao_Impl.touch$lambda$0(str2, j, str, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit touch$lambda$0(String str, long j, String str2, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo8593bindLong(1, j);
            sQLiteStatementPrepare.mo8595bindText(2, str2);
            sQLiteStatementPrepare.step();
            sQLiteStatementPrepare.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            sQLiteStatementPrepare.close();
            throw th;
        }
    }

    @Override // org.cipherkey.data.database.SessionKeyDao
    public Object deleteByContact(final String str, Continuation<? super Unit> continuation) {
        final String str2 = "DELETE FROM session_keys WHERE contactId = ?";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: org.cipherkey.data.database.SessionKeyDao_Impl$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SessionKeyDao_Impl.deleteByContact$lambda$0(str2, str, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteByContact$lambda$0(String str, String str2, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo8595bindText(1, str2);
            sQLiteStatementPrepare.step();
            sQLiteStatementPrepare.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            sQLiteStatementPrepare.close();
            throw th;
        }
    }

    /* compiled from: SessionKeyDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/cipherkey/data/database/SessionKeyDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<KClass<?>> getRequiredConverters() {
            return CollectionsKt.emptyList();
        }
    }
}
