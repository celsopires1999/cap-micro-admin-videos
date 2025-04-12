package com.cap.admin.catalogo.domain.validation;

public abstract class Validator {
    private final ValidationHandler handler;

    protected Validator(final ValidationHandler aHandler) {
        this.handler = aHandler;
    }

    protected abstract void validate();

    protected ValidationHandler validationHandler() {
        return this.handler;
    }
}
