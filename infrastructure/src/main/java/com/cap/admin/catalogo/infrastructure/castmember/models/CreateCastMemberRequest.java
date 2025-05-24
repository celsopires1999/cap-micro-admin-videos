package com.cap.admin.catalogo.infrastructure.castmember.models;

import com.cap.admin.catalogo.domain.castmember.CastMemberType;

public record CreateCastMemberRequest(String name, CastMemberType type) {
}
