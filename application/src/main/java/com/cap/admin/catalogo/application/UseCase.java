package com.cap.admin.catalogo.application;

import com.cap.admin.catalogo.domain.category.Category;;

public class UseCase {

    public Category execute() {
        return Category.newCategory("name", "description", true);
    }
}