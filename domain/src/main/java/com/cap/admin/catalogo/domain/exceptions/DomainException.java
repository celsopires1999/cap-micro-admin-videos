package com.cap.admin.catalogo.domain.exceptions;

import com.cap.admin.catalogo.domain.validation.Error;

import java.util.List;

public class DomainException extends NoStacktraceException {

    private final List<Error> errors;

    private DomainException(final String aMessage, final List<Error> theErrors) {
        super(aMessage);
        this.errors = theErrors;
    }

    public static DomainException with(final Error theErrors) {
        return new DomainException(theErrors.message(), List.of(theErrors));
    }

    public static DomainException with(final List<Error> theErrors) {
        return new DomainException("", theErrors);
    }

    public List<Error> getErrors() {
        return errors;
    }
}

