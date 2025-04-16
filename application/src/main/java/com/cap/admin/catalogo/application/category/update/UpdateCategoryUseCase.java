package com.cap.admin.catalogo.application.category.update;

import com.cap.admin.catalogo.application.UseCase;
import com.cap.admin.catalogo.domain.validation.handler.Notification;

import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase
        extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
