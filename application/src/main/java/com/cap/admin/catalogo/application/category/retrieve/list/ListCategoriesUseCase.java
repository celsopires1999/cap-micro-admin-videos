package com.cap.admin.catalogo.application.category.retrieve.list;

import com.cap.admin.catalogo.application.UseCase;
import com.cap.admin.catalogo.domain.category.CategorySearchQuery;
import com.cap.admin.catalogo.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase
        extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}