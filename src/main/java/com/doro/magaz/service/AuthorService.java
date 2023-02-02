package com.doro.magaz.service;

import com.doro.magaz.entity.Author;
import com.doro.magaz.repository.AuthorRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
@Data
public class AuthorService {

    private final AuthorRepository authorRepository;

    public void readAuthorsFromCSV(String filePath) {
        try (Stream<String> rows = Files.lines(Paths.get(filePath))) {
            List<String[]> strings = rows.map(line -> line.split(",")).toList();
            for (String[] row : strings) {
                Author author = new Author();
                author.setEmail(row[0]);
                author.setFirstName(row[1]);
                author.setLastName(row[2]);
                authorRepository.save(author);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
