package com.cap.admin.catalogo.application.castmember.retrieve.get;

import com.cap.admin.catalogo.domain.castmember.CastMember;
import com.cap.admin.catalogo.domain.castmember.CastMemberGateway;
import com.cap.admin.catalogo.domain.castmember.CastMemberID;
import com.cap.admin.catalogo.domain.exceptions.NotFoundException;

import java.util.Objects;

public final class DefaultGetCastMemberByIdUseCase extends GetCastMemberByIdUseCase {

    private final CastMemberGateway castMemberGateway;

    public DefaultGetCastMemberByIdUseCase(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Override
    public CastMemberOutput execute(final String anIn) {
        final var aMemberId = CastMemberID.from(anIn);
        return this.castMemberGateway.findById(aMemberId)
                .map(CastMemberOutput::from)
                .orElseThrow(() -> NotFoundException.with(CastMember.class, aMemberId));
    }
}
