package com.cap.admin.catalogo.application.category.create;

public record CreateCategoryCommand(
        String name,
        String description,
        boolean isActive
) {

    public static CreateCategoryCommand with(
            final String aName,
            final String aDescription,
            final boolean isActive
    ) {
        return new CreateCategoryCommand(aName, aDescription, isActive);
    }
    public static CreateCategoryCommand with(
            final String aName,
            final String aDescription
    ) {
        return new CreateCategoryCommand(aName, aDescription, true);
    }
    public static CreateCategoryCommand with(
            final String aName
    ) {
        return new CreateCategoryCommand(aName, null, true);
    }
}
