package com.cap.admin.catalogo.domain.validation.handler;

import java.util.List;

import com.cap.admin.catalogo.domain.exceptions.DomainException;
import com.cap.admin.catalogo.domain.validation.ValidationHandler;
import com.cap.admin.catalogo.domain.validation.Error;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final Error anError) {
        throw DomainException.with(anError);
    }

    @Override
    public ValidationHandler append(final ValidationHandler anHandler) {
        throw DomainException.with(anHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(final Validation aValidation) {
        try {
            aValidation.validate();
        } catch (final Exception ex) {
            throw DomainException.with(List.of(new Error(ex.getMessage())));
        }

        return this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
}
