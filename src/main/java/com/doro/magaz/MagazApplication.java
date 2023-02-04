package com.doro.magaz;

import com.doro.magaz.service.AuthorService;
import com.doro.magaz.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class MagazApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(MagazApplication.class, args);
        AuthorService authorService = context.getBean(AuthorService.class);
        BookService bookService = context.getBean(BookService.class);

        authorService.readAuthorsFromCSV("src/main/resources/data/autoren.csv");
        bookService.importBooksFromCsv("src/main/resources/data/buecher.csv");
    }
}
