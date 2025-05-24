package com.cap.admin.catalogo.application.castmember.retrieve.list;

import com.cap.admin.catalogo.application.UseCase;
import com.cap.admin.catalogo.domain.pagination.Pagination;
import com.cap.admin.catalogo.domain.pagination.SearchQuery;

public sealed abstract class ListCastMembersUseCase
                extends UseCase<SearchQuery, Pagination<CastMemberListOutput>>
                permits DefaultListCastMembersUseCase {
}
