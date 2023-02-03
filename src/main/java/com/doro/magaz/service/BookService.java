package com.doro.magaz.service;

import com.doro.magaz.entity.Author;
import com.doro.magaz.entity.Book;
import com.doro.magaz.entity.Magazine;
import com.doro.magaz.repository.AuthorRepository;
import com.doro.magaz.repository.BookRepository;
import com.doro.magaz.util.CsvReader;
import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

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

    public void readBooksFromCSV(String filePath) {
        CsvReader csvReader = new CsvReader(filePath, StandardCharsets.UTF_8);
        try {
            List<String[]> rows = csvReader.read();
            rows.stream().map(row -> {
                    Book book = new Book();
                    book.setTitle(row[0]);
                    book.setDescription(row[1]);
                    book.setIsbn(row[2]);
                    return book;
                })
                .forEach(bookRepository::save);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
