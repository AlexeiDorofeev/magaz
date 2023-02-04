package com.doro.magaz;

import com.doro.magaz.entity.Book;
import com.doro.magaz.entity.Magazine;
import com.doro.magaz.service.AuthorService;
import com.doro.magaz.service.BookService;
import com.doro.magaz.service.MagazineService;
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
        MagazineService magazineService = context.getBean(MagazineService.class);

        authorService.readAuthorsFromCSV("src/main/resources/data/autoren.csv");
        bookService.readBooksFromCSV("src/main/resources/data/buecher.csv");
        magazineService.readMagazinesFromCSV("src/main/resources/data/zeitschriften.csv");
        List<Book> allBooks = bookService.getAllBooksWithDetails();
        List<Magazine> magazines = magazineService.getAllMagazinesWithDetails();
        //    List<Book> byAuthorEmail = bookService.findBooksByAuthorEmail("pr-lieblich@optivo.de");
    }
}
