package org.cipherkey.data.repository;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.cipherkey.data.database.ContactEntity;

/* compiled from: ContactRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lorg/cipherkey/data/repository/Contact;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.data.repository.ContactRepository$renameContact$2", f = "ContactRepository.kt", i = {0, 1, 1, 1}, l = {60, 63}, m = "invokeSuspend", n = {HintConstants.AUTOFILL_HINT_NAME, HintConstants.AUTOFILL_HINT_NAME, "existing", "updated"}, s = {"L$0", "L$0", "L$1", "L$2"}, v = 1)
/* loaded from: classes3.dex */
final class ContactRepository$renameContact$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Contact>, Object> {

    /* renamed from: $$v$c$org-cipherkey-core-common-ContactId$-contactId$0, reason: not valid java name */
    final /* synthetic */ String f172$$v$c$orgcipherkeycorecommonContactId$contactId$0;
    final /* synthetic */ String $newName;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ ContactRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ContactRepository$renameContact$2(String str, ContactRepository contactRepository, String str2, Continuation<? super ContactRepository$renameContact$2> continuation) {
        super(2, continuation);
        this.$newName = str;
        this.this$0 = contactRepository;
        this.f172$$v$c$orgcipherkeycorecommonContactId$contactId$0 = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ContactRepository$renameContact$2(this.$newName, this.this$0, this.f172$$v$c$orgcipherkeycorecommonContactId$contactId$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Contact> continuation) {
        return ((ContactRepository$renameContact$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String string;
        ContactEntity contactEntity;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            string = StringsKt.trim((CharSequence) this.$newName).toString();
            if (string.length() <= 0) {
                throw new IllegalArgumentException("contact name must not be empty".toString());
            }
            if (string.length() <= 64) {
                this.L$0 = string;
                this.label = 1;
                obj = this.this$0.contactDao.byId(this.f172$$v$c$orgcipherkeycorecommonContactId$contactId$0, this);
                if (obj != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            throw new IllegalArgumentException("contact name must be at most 64 chars".toString());
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            contactEntity = (ContactEntity) this.L$2;
            ResultKt.throwOnFailure(obj);
            return this.this$0.toDomain(contactEntity);
        }
        string = (String) this.L$0;
        ResultKt.throwOnFailure(obj);
        String str = string;
        ContactEntity contactEntity2 = (ContactEntity) obj;
        if (contactEntity2 == null) {
            return null;
        }
        ContactEntity contactEntityCopy$default = ContactEntity.copy$default(contactEntity2, null, str, 0L, this.this$0.timeSource.nowMillis(), 5, null);
        this.L$0 = SpillingKt.nullOutSpilledVariable(str);
        this.L$1 = SpillingKt.nullOutSpilledVariable(contactEntity2);
        this.L$2 = contactEntityCopy$default;
        this.label = 2;
        if (this.this$0.contactDao.upsert(contactEntityCopy$default, this) != coroutine_suspended) {
            contactEntity = contactEntityCopy$default;
            return this.this$0.toDomain(contactEntity);
        }
        return coroutine_suspended;
    }
}
