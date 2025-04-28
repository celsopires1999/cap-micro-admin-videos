package com.cap.admin.catalogo.infrastructure.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.cap.admin.catalogo.ControllerTest;
import com.cap.admin.catalogo.application.category.create.CreateCategoryUseCase;

@ControllerTest(controllers = CategoryAPI.class)
public class CategoryAPITest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private CreateCategoryUseCase createCategoryUseCase;

    @Test
    public void test() {

    }
}