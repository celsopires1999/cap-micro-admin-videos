package com.cap.admin.catalogo.application.category.create;

import java.util.Objects;

import com.cap.admin.catalogo.domain.category.Category;
import com.cap.admin.catalogo.domain.category.CategoryGateway;
import com.cap.admin.catalogo.domain.validation.handler.Notification;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryCommand aCommand) {
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var notification = Notification.create();
                
        final var aCategory = Category.newCategory(aName, aDescription, isActive);
        aCategory.validate(notification);

        if (notification.hasError()) {
            //
        }

        return CreateCategoryOutput.from(
                this.categoryGateway.create(aCategory));
    }
}
