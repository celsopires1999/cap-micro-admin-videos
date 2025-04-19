package com.cap.admin.catalogo.infrastructure;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;

import com.cap.admin.catalogo.domain.category.Category;
import com.cap.admin.catalogo.infrastructure.category.persistence.CategoryJpaEntity;
import com.cap.admin.catalogo.infrastructure.category.persistence.CategoryRepository;
import com.cap.admin.catalogo.infrastructure.configuration.WebServerConfig;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME,
                "development");
        // System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME,
        // "test");
        SpringApplication.run(WebServerConfig.class, args);
    }

    @Bean
    public ApplicationRunner runner(CategoryRepository repository) {
        return args -> {
            List<CategoryJpaEntity> all = repository.findAll();

            System.out.println("All categories: " + all);

            Category filmes = Category.newCategory("Filmes", null, true);

            repository.saveAndFlush(CategoryJpaEntity.from(filmes));

            repository.deleteAll();
        };
    }
}