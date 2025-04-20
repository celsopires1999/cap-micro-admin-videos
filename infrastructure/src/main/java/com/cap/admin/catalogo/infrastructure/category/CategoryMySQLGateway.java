package com.cap.admin.catalogo.infrastructure.category;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cap.admin.catalogo.domain.category.Category;
import com.cap.admin.catalogo.domain.category.CategoryGateway;
import com.cap.admin.catalogo.domain.category.CategoryID;
import com.cap.admin.catalogo.domain.category.CategorySearchQuery;
import com.cap.admin.catalogo.domain.pagination.Pagination;
import com.cap.admin.catalogo.infrastructure.category.persistence.CategoryJpaEntity;
import com.cap.admin.catalogo.infrastructure.category.persistence.CategoryRepository;

@Service
public class CategoryMySQLGateway implements CategoryGateway {

    private final CategoryRepository repository;

    public CategoryMySQLGateway(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category create(final Category aCategory) {
        return save(aCategory);
    }

    @Override
    public void deleteById(CategoryID anId) {
        final String anIdValue = anId.getValue();
        if (this.repository.existsById(anIdValue)) {
            this.repository.deleteById(anIdValue);
        }
    }

    @Override
    public Optional<Category> findById(CategoryID anId) {
        return Optional.empty();
    }

    @Override
    public Category update(final Category aCategory) {
        return save(aCategory);
    }

    @Override
    public Pagination<Category> findAll(CategorySearchQuery aQuery) {
        return null;
    }

    private Category save(final Category aCategory) {
        return this.repository.save(CategoryJpaEntity.from(aCategory)).toAggregate();
    }
}
