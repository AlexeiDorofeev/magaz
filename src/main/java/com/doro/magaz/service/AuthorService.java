package com.doro.magaz.service;

import com.doro.magaz.entity.Author;
import com.doro.magaz.repository.AuthorRepository;
import com.doro.magaz.util.CsvReader;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Data
public class AuthorService {

    private final AuthorRepository authorRepository;

    public void readAuthorsFromCSV(String filePath) {
        CsvReader csvReader = new CsvReader(filePath, StandardCharsets.UTF_8);
        try {
            List<String[]> rows = csvReader.read();
            rows.stream().map(row -> {
                    Author author = new Author();
                    author.setEmail(row[0]);
                    author.setFirstName(row[1]);
                    author.setLastName(row[2]);
                    return author;
                })
                .forEach(authorRepository::save);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
