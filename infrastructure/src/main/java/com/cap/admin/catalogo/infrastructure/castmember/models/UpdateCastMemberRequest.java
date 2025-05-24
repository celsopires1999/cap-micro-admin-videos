package com.cap.admin.catalogo.infrastructure.castmember.models;

import com.cap.admin.catalogo.domain.castmember.CastMemberType;

public record UpdateCastMemberRequest(String name, CastMemberType type) {
}
