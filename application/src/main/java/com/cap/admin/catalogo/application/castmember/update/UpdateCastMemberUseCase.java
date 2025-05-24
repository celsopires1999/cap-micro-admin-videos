package com.cap.admin.catalogo.application.castmember.update;

import com.cap.admin.catalogo.application.UseCase;

public sealed abstract class UpdateCastMemberUseCase
                extends UseCase<UpdateCastMemberCommand, UpdateCastMemberOutput>
                permits DefaultUpdateCastMemberUseCase {
}
