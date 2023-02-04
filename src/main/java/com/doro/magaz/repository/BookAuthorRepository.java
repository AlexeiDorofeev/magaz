package com.doro.magaz.repository;

import com.doro.magaz.entity.Author;
import com.doro.magaz.entity.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {

    List<BookAuthor> findByAuthor(Author author);
}