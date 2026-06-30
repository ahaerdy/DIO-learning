package br.com.dio.persistence;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NIO2FilePersistence extends FilePersistence {

    public NIO2FilePersistence(final String fileName) throws IOException {
        super(fileName);
        var path = Paths.get(currentDir + storedDir);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        cleanFile();
    }

    public String write(final String data) {
        var path = Paths.get(currentDir + storedDir + fileName);
        try {
            Files.write(path, data.getBytes(), StandardOpenOption.APPEND);
            Files.write(path, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public String findBy(final String sentence) {
        var content = findAll();
        return Stream.of(content.split(System.lineSeparator()))
                .filter(c -> c.contains(sentence))
                .findFirst()
                .orElse("");
    }

    public String findAll() {
        var path = Paths.get(currentDir + storedDir + fileName);
        var content = "";
        try (var lines = Files.lines(path)) {
            content = lines.collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return content.toString();
    }
}
