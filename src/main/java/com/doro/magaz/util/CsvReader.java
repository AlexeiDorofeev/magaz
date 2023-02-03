package com.doro.magaz.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class CsvReader {

    private final String filePath;
    private final Charset charset;

    public CsvReader(String filePath, Charset charset) {
        this.filePath = filePath;
        this.charset = charset;
    }

    public List<String[]> read() throws IOException {
        try (Stream<String> rows = Files.lines(Paths.get(filePath), charset)) {
            return rows.map(line -> line.split("[,;]")).toList();
        }
    }
}
