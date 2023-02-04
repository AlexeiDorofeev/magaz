package com.doro.magaz;

import com.doro.magaz.service.AuthorService;
import com.doro.magaz.service.BookService;
import com.doro.magaz.service.MagazineService;
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
        MagazineService magazineService = context.getBean(MagazineService.class);

        try {
            authorService.readAuthorsFromCSV("src/main/resources/data/autoren.csv");
        } catch (Exception e) {
            System.out.println("exception");
        }
        bookService.readBooksFromCSV("src/main/resources/data/buecher.csv");
        //    List<Book> byAuthorEmail = bookService.findBooksByAuthorEmail("pr-lieblich@optivo.de");

        //   List<Book> allBooks = bookService.getAllBooksWithDetails();

        // magazineService.readMagazinesFromCSV("src/main/resources/data/zeitschriften.csv");

        //   List<Magazine> magazines = magazineService.getAllMagazinesWithDetails();


    }
}
