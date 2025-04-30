package com.cap.admin.catalogo.infrastructure.category.presenters;

import com.cap.admin.catalogo.application.category.retrieve.get.CategoryOutput;
import com.cap.admin.catalogo.infrastructure.category.models.CategoryApiOutput;

public interface CategoryApiPresenter {

    static CategoryApiOutput present(final CategoryOutput output) {
        return new CategoryApiOutput(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt());
    }
}