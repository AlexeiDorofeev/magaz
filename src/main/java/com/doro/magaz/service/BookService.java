package com.doro.magaz.service;

import com.doro.magaz.entity.Author;
import com.doro.magaz.entity.Book;
import com.doro.magaz.repository.AuthorRepository;
import com.doro.magaz.repository.BookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> findBooksByAuthor(Author author) {
        return bookRepository.findByAuthorsContains(author);
    }

    public List<Book> sortBooksByTitle() {
        return bookRepository.findAll(Sort.by("title"));
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> findAuthorByEmail(String email) {
        return authorRepository.findByEmail(email);
    }

}
