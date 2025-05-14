package com.cap.admin.catalogo.infrastructure.category.models;

import com.cap.admin.catalogo.JacksonTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

@JacksonTest
public class CategoryResponseTest {

    @Autowired
    private JacksonTester<CategoryResponse> json;

}