package com.cap.admin.catalogo.application.genre.retrieve.list;

import com.cap.admin.catalogo.application.UseCase;
import com.cap.admin.catalogo.domain.pagination.Pagination;
import com.cap.admin.catalogo.domain.pagination.SearchQuery;

public abstract class ListGenreUseCase
        extends UseCase<SearchQuery, Pagination<GenreListOutput>> {
}
