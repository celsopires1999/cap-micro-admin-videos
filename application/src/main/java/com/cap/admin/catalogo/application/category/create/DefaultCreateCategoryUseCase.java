package com.cap.admin.catalogo.application.category.create;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

import java.util.Objects;

import com.cap.admin.catalogo.domain.category.Category;
import com.cap.admin.catalogo.domain.category.CategoryGateway;
import com.cap.admin.catalogo.domain.validation.handler.Notification;

import io.vavr.control.Either;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand aCommand) {
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var notification = Notification.create();

        final var aCategory = Category.newCategory(aName, aDescription, isActive);
        aCategory.validate(notification);

        return notification.hasError() ? Left(notification)
                : create(aCategory);
    }

    private Either<Notification, CreateCategoryOutput> create(final Category aCategory){
        return Try(() -> this.categoryGateway.create(aCategory))
                .toEither()
                .bimap(Notification::create, CreateCategoryOutput::from);
    }
}
