package org.cipherkey.data.repository;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: ContactRepository.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.data.repository.ContactRepository", f = "ContactRepository.kt", i = {0}, l = {32}, m = "contactName-ovFUVq0", n = {"$v$c$org-cipherkey-core-common-ContactId$-contactId$0"}, s = {"L$0"}, v = 1)
/* loaded from: classes3.dex */
final class ContactRepository$contactName$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ContactRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ContactRepository$contactName$1(ContactRepository contactRepository, Continuation<? super ContactRepository$contactName$1> continuation) {
        super(continuation);
        this.this$0 = contactRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.m10667contactNameovFUVq0(null, this);
    }
}
