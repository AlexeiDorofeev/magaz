package com.doro.magaz;

import com.doro.magaz.entity.Book;
import com.doro.magaz.service.AuthorService;
import com.doro.magaz.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class MagazApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(MagazApplication.class, args);
        AuthorService authorService = context.getBean(AuthorService.class);
        BookService bookService = context.getBean(BookService.class);

    //    authorService.readAuthorsFromCSV("src/main/resources/data/autoren.csv");
       // bookService.importBooksFromCsv("src/main/resources/data/buecher.csv");
    //    List<Book> byAuthorEmail = bookService.findBooksByAuthorEmail("pr-lieblich@optivo.de");

        List<Book> allBooks = bookService.getAllBooksWithDetails();
        System.out.println(allBooks);

    }
}
