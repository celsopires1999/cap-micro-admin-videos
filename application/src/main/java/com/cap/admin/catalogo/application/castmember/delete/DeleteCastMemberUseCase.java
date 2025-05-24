package com.cap.admin.catalogo.application.castmember.delete;

import com.cap.admin.catalogo.application.UnitUseCase;

public sealed abstract class DeleteCastMemberUseCase
        extends UnitUseCase<String>
        permits DefaultDeleteCastMemberUseCase {
}
