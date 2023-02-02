package com.doro.magaz;

import com.doro.magaz.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MagazApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MagazApplication.class, args);
        AuthorService authorService = context.getBean(AuthorService.class);
        authorService.readAuthorsFromCSV("C:\\devel\\work\\spring\\magaz\\src\\main\\resources\\data");
    }

}
