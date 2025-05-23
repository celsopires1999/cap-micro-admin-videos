package com.cap.admin.catalogo.infrastructure.genre;

import com.cap.admin.catalogo.domain.genre.Genre;
import com.cap.admin.catalogo.domain.genre.GenreGateway;
import com.cap.admin.catalogo.domain.genre.GenreID;
import com.cap.admin.catalogo.domain.pagination.Pagination;
import com.cap.admin.catalogo.domain.pagination.SearchQuery;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GenreMySQLGateway implements GenreGateway {

    @Override
    public Genre create(Genre aGenre) {
        return null;
    }

    @Override
    public void deleteById(GenreID anId) {

    }

    @Override
    public Optional<Genre> findById(GenreID anId) {
        return Optional.empty();
    }

    @Override
    public Genre update(Genre aGenre) {
        return null;
    }

    @Override
    public Pagination<Genre> findAll(SearchQuery aQuery) {
        return null;
    }
}
