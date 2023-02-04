package com.doro.magaz.repository;

import com.doro.magaz.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    //List<Book> findAll();
    @Query("SELECT b, ba.author.email FROM Book b JOIN b.bookAuthors ba")
    List<Object[]> getAllBooksWithDetails();
}
