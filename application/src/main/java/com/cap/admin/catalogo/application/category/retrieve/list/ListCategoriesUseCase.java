package com.cap.admin.catalogo.application.category.retrieve.list;

import com.cap.admin.catalogo.application.UseCase;
import com.cap.admin.catalogo.domain.pagination.SearchQuery;
import com.cap.admin.catalogo.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase
                extends UseCase<SearchQuery, Pagination<CategoryListOutput>> {
}
