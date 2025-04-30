package com.cap.admin.catalogo.infrastructure.api.controllers;

import com.cap.admin.catalogo.application.category.create.CreateCategoryCommand;
import com.cap.admin.catalogo.application.category.create.CreateCategoryOutput;
import com.cap.admin.catalogo.application.category.create.CreateCategoryUseCase;
import com.cap.admin.catalogo.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.cap.admin.catalogo.domain.pagination.Pagination;
import com.cap.admin.catalogo.domain.validation.handler.Notification;
import com.cap.admin.catalogo.infrastructure.api.CategoryAPI;
import com.cap.admin.catalogo.infrastructure.category.models.CategoryApiOutput;
import com.cap.admin.catalogo.infrastructure.category.models.CreateCategoryApiInput;
import com.cap.admin.catalogo.infrastructure.category.presenters.CategoryApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;

    public CategoryController(
            final CreateCategoryUseCase createCategoryUseCase,
            final GetCategoryByIdUseCase getCategoryByIdUseCase) {
        this.createCategoryUseCase = Objects.requireNonNull(createCategoryUseCase);
        this.getCategoryByIdUseCase = Objects.requireNonNull(getCategoryByIdUseCase);
    }

    @Override
    public ResponseEntity<?> createCategory(final CreateCategoryApiInput input) {
        final var aCommand = CreateCategoryCommand.with(
                input.name(),
                input.description(),
                input.active() != null ? input.active() : true);

        final Function<Notification, ResponseEntity<?>> onError = notification -> ResponseEntity.unprocessableEntity()
                .body(notification);

        final Function<CreateCategoryOutput, ResponseEntity<?>> onSuccess = output -> ResponseEntity
                .created(URI.create("/categories/" + output.id())).body(output);

        return this.createCategoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public Pagination<?> listCategories(String search, int page, int perPage, String sort, String direction) {
        return null;
    }

    @Override
    public CategoryApiOutput getById(final String id) {
        return CategoryApiPresenter.present(this.getCategoryByIdUseCase.execute(id));
    }
}