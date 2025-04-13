package com.cap.admin.catalogo.application.category.create;

import com.cap.admin.catalogo.application.UseCase;
import com.cap.admin.catalogo.domain.validation.handler.Notification;

import io.vavr.control.Either;

public abstract class CreateCategoryUseCase
        extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {
}
