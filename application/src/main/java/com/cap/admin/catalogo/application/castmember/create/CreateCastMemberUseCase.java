package com.cap.admin.catalogo.application.castmember.create;

import com.cap.admin.catalogo.application.UseCase;

public sealed abstract class CreateCastMemberUseCase
                extends UseCase<CreateCastMemberCommand, CreateCastMemberOutput>
                permits DefaultCreateCastMemberUseCase {
}
