package com.cap.admin.catalogo.infrastructure.category;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cap.admin.catalogo.domain.category.Category;
import com.cap.admin.catalogo.domain.category.CategoryGateway;
import com.cap.admin.catalogo.domain.category.CategoryID;
import com.cap.admin.catalogo.domain.category.CategorySearchQuery;
import com.cap.admin.catalogo.domain.pagination.Pagination;
import com.cap.admin.catalogo.infrastructure.category.persistence.CategoryRepository;

@Service
public class CategoryMySQLGateway implements CategoryGateway {

    private final CategoryRepository repository;

    public CategoryMySQLGateway(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category create(Category aCategory) {
        return null;
    }

    @Override
    public void deleteById(CategoryID anId) {

    }

    @Override
    public Optional<Category> findById(CategoryID anId) {
        return Optional.empty();
    }

    @Override
    public Category update(Category aCategory) {
        return null;
    }

    @Override
    public Pagination<Category> findAll(CategorySearchQuery aQuery) {
        return null;
    }
}