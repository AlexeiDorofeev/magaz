package com.doro.magaz.repository;

import com.doro.magaz.entity.Author;
import com.doro.magaz.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);

    List<Book> findByAuthorsContains(Author author);

}
