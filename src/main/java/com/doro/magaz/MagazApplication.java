package com.doro.magaz;

import com.doro.magaz.entity.Author;
import com.doro.magaz.repository.AuthorRepository;
import com.doro.magaz.service.AuthorService;
import com.doro.magaz.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class MagazApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MagazApplication.class, args);
        AuthorService authorService = context.getBean(AuthorService.class);
        BookService bookService = context.getBean(BookService.class);

        authorService.readAuthorsFromCSV("src/main/resources/data/autoren.csv");
        bookService.readBooksFromCSV("C:\\Users\\Alexei.Dorofeev\\Desktop\\project\\magaz\\src\\main\\resources\\data\\buecher.csv");
    }

}
