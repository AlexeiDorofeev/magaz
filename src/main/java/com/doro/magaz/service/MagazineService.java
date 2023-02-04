package com.doro.magaz.service;

import com.doro.magaz.entity.Author;
import com.doro.magaz.entity.Magazine;
import com.doro.magaz.entity.MagazineAuthor;
import com.doro.magaz.repository.AuthorRepository;
import com.doro.magaz.repository.MagazineAuthorRepository;
import com.doro.magaz.repository.MagazineRepository;
import com.doro.magaz.util.CsvReader;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class MagazineService {
    private final MagazineRepository magazineRepository;
    private final AuthorRepository authorRepository;
    private final MagazineAuthorRepository magazineAuthorRepository;

    @Transactional
    public List<Magazine> getAllMagazinesWithDetails() {
        List<Magazine> magazines = magazineRepository.findAll();
        magazines.forEach(magazine -> magazine.getMagazineAuthors().size());
        return magazines;
    }

    public void readMagazinesFromCSV(String filePath) throws IOException {
        CsvReader csvReader = new CsvReader(filePath, StandardCharsets.ISO_8859_1);
        List<String[]> rows = csvReader.read();
        rows.stream().map(row -> {
            Magazine magazine = new Magazine();
            magazine.setTitle(row[0]);
            magazine.setIsbn(row[1]);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            magazine.setPublicationDate(LocalDate.parse(row[3], formatter));
            magazine = magazineRepository.save(magazine);

            String[] authorEmails = row[2].split(",");
            for (String authorEmail : authorEmails) {
                Optional<Author> maybeAuthor = authorRepository.findByEmail(authorEmail.trim());
                if (maybeAuthor.isPresent()) {
                    Author author = maybeAuthor.get();
                    MagazineAuthor magazineAuthor = new MagazineAuthor();
                    magazineAuthor.setMagazine(magazine);
                    magazineAuthor.setAuthor(author);
                    magazineAuthorRepository.save(magazineAuthor);
                }
            }
            return magazine;
        }).collect(Collectors.toList());
    }
}
