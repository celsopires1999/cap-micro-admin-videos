package com.cap.admin.catalogo.infrastructure.genre.presenters;

import com.cap.admin.catalogo.application.genre.retrieve.get.GenreOutput;
import com.cap.admin.catalogo.application.genre.retrieve.list.GenreListOutput;
import com.cap.admin.catalogo.infrastructure.genre.models.GenreListResponse;
import com.cap.admin.catalogo.infrastructure.genre.models.GenreResponse;

public interface GenreApiPresenter {

    static GenreResponse present(final GenreOutput output) {
        return new GenreResponse(
                output.id(),
                output.name(),
                output.categories(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt());
    }

    static GenreListResponse present(final GenreListOutput output) {
        return new GenreListResponse(
                output.id(),
                output.name(),
                output.isActive(),
                output.createdAt(),
                output.deletedAt());
    }
}
