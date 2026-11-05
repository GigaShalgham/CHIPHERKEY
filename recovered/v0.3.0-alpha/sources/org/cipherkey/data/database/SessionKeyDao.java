package org.cipherkey.data.database;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* compiled from: Daos.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\n\u001a\u00020\u000bH'J\u0018\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u000bH§@¢\u0006\u0002\u0010\rJ\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000f\u001a\u00020\u0010H§@¢\u0006\u0002\u0010\u0011J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u000bH§@¢\u0006\u0002\u0010\rJ(\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0016\u001a\u00020\u0017H§@¢\u0006\u0002\u0010\u0018J\u001e\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0017H§@¢\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH§@¢\u0006\u0002\u0010\rJ\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\n\u001a\u00020\u000bH§@¢\u0006\u0002\u0010\r¨\u0006\u001eÀ\u0006\u0003"}, d2 = {"Lorg/cipherkey/data/database/SessionKeyDao;", "", "upsert", "", "session", "Lorg/cipherkey/data/database/SessionKeyEntity;", "(Lorg/cipherkey/data/database/SessionKeyEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeByContact", "Lkotlinx/coroutines/flow/Flow;", "", "contactId", "", "latestByContact", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "byKeyId", "keyId", "", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bySessionId", "sessionId", "updateWrappedKey", "wrapped", "now", "", "(Ljava/lang/String;[BJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "touch", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteByContact", "countByContact", "", "database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface SessionKeyDao {
    Object byKeyId(byte[] bArr, Continuation<? super SessionKeyEntity> continuation);

    Object bySessionId(String str, Continuation<? super SessionKeyEntity> continuation);

    Object countByContact(String str, Continuation<? super Integer> continuation);

    Object deleteByContact(String str, Continuation<? super Unit> continuation);

    Object latestByContact(String str, Continuation<? super SessionKeyEntity> continuation);

    Flow<List<SessionKeyEntity>> observeByContact(String contactId);

    Object touch(String str, long j, Continuation<? super Unit> continuation);

    Object updateWrappedKey(String str, byte[] bArr, long j, Continuation<? super Unit> continuation);

    Object upsert(SessionKeyEntity sessionKeyEntity, Continuation<? super Unit> continuation);
}
