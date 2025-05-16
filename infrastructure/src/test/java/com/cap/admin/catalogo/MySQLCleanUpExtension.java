package com.cap.admin.catalogo;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collection;

public class MySQLCleanUpExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(final ExtensionContext context) {
        final var repositories = convertToGenericCollection(SpringExtension
                .getApplicationContext(context)
                .getBeansOfType(CrudRepository.class)
                .values());

        cleanUp(repositories);
    }

    private Collection<CrudRepository<?, ?>> convertToGenericCollection(Collection<?> rawCollection) {
        Collection<CrudRepository<?, ?>> result = new ArrayList<>();
        for (Object item : rawCollection) {
            if (item instanceof CrudRepository<?, ?> crudRepository) {
                result.add(crudRepository);
            }
        }
        return result;
    }

    private void cleanUp(final Collection<CrudRepository<?, ?>> repositories) {
        repositories.forEach(CrudRepository::deleteAll);
    }
}
