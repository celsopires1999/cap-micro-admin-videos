package com.cap.admin.catalogo.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;

import com.cap.admin.catalogo.application.category.create.CreateCategoryUseCase;
import com.cap.admin.catalogo.application.category.delete.DeleteCategoryUseCase;
import com.cap.admin.catalogo.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.cap.admin.catalogo.application.category.retrieve.list.ListCategoriesUseCase;
import com.cap.admin.catalogo.application.category.update.UpdateCategoryUseCase;
import com.cap.admin.catalogo.infrastructure.configuration.WebServerConfig;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME,
                "development");
        SpringApplication.run(WebServerConfig.class, args);
    }

    @Bean
    @DependsOnDatabaseInitialization
    ApplicationRunner runner(
            @Autowired CreateCategoryUseCase createCategoryUseCase,
            @Autowired UpdateCategoryUseCase updateCategoryUseCase,
            @Autowired DeleteCategoryUseCase deteteCategoryUseCase,
            @Autowired GetCategoryByIdUseCase getCategoryByIdUseCase,
            @Autowired ListCategoriesUseCase listCategoriesUseCase) {
        return args -> {
        };
    }
}