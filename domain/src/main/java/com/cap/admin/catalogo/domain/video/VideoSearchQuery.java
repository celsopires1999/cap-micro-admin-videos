package com.cap.admin.catalogo.domain.video;

import com.cap.admin.catalogo.domain.castmember.CastMemberID;
import com.cap.admin.catalogo.domain.category.CategoryID;
import com.cap.admin.catalogo.domain.genre.GenreID;

import java.util.Set;

public record VideoSearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction,
        Set<CastMemberID> castMembers,
        Set<CategoryID> categories,
        Set<GenreID> genres) {
}
