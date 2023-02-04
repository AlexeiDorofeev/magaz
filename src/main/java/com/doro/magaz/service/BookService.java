package com.doro.magaz.service;

import com.doro.magaz.entity.Author;
import com.doro.magaz.entity.Book;
import com.doro.magaz.entity.BookAuthor;
import com.doro.magaz.repository.AuthorRepository;
import com.doro.magaz.repository.BookAuthorRepository;
import com.doro.magaz.repository.BookRepository;
import com.doro.magaz.util.CsvReader;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Data
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookAuthorRepository bookAuthorRepository;

    public void readBooksFromCSV(String filePath) throws IOException {
        CsvReader csvReader = new CsvReader(filePath, Charset.defaultCharset());
        List<String[]> rows = csvReader.read();
        rows.stream().map(row -> {
            Book book = new Book();
            book.setTitle(row[0]);
            book.setIsbn(row[1]);
            book.setDescription(row[3]);
            book = bookRepository.save(book);

            String[] authorEmails = row[2].split(",");
            for (String authorEmail : authorEmails) {
                Optional<Author> maybeAuthor = authorRepository.findByEmail(authorEmail.trim());
                if (maybeAuthor.isPresent()) {
                    Author author = maybeAuthor.get();
                    BookAuthor bookAuthor = new BookAuthor();
                    bookAuthor.setBook(book);
                    bookAuthor.setAuthor(author);
                    bookAuthorRepository.save(bookAuthor);
                }
            }
            return book;
        }).collect(Collectors.toList());
    }

    public List<Book> findBooksByAuthorEmail(String authorEmail) {
        Optional<Author> maybeAuthor = authorRepository.findByEmail(authorEmail.trim());
        if (maybeAuthor.isPresent()) {
            Author author = maybeAuthor.get();
            List<BookAuthor> bookAuthors = bookAuthorRepository.findByAuthor(author);
            return bookAuthors.stream().map(BookAuthor::getBook).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public List<Book> getAllBooksWithDetails() {
        List<Book> books = bookRepository.findAll();
        books.forEach(book -> book.getBookAuthors().size());
        return books;
    }
}
