package by.mironenko.service.impl;

import by.mironenko.domen.PersonDto;
import by.mironenko.service.CodeService;
import by.mironenko.service.PersonService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PersonServiceImpl implements PersonService {

    private final CodeService ci;
    private final String root;

    public PersonServiceImpl(final CodeService ci, final String root) {
        this.ci = ci;
        this.root = root;
    }

    @Override
    public void createPerson(final PersonDto personDto) {
        if (personDto == null || personDto.getEmail() == null) {
            throw new RuntimeException("Не указан email.");
        }
        try {
            findPersonByEmail(personDto.getEmail());
            System.out.println("Такой пользователь уже существует.");
            return;
        } catch (RuntimeException ignored) {
        }
        try (FileWriter writer = new FileWriter(new File(root + "/" + defineFileName(personDto.getEmail())))) {
            writer.write(ci.getStringFromPerson(personDto));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePersonByEmail(final String email) {
        if (email == null) {
            throw new RuntimeException("Вы не ввели email.");
        }
        final String correctedFileName = defineFileName(email);
        File file = new File(root + "/" + correctedFileName);
        if (!file.exists()) {
            throw new RuntimeException("Email not found.");
        }
        file.delete();
    }

    @Override
    public void updatePerson(final PersonDto personDto) {
        deletePersonByEmail(personDto.getEmail());
        createPerson(personDto);
    }

    @Override
    public PersonDto findPersonByEmail(final String email) {
        if (email == null) {
            throw new RuntimeException("Вы не ввели email.");
        }
        final String correctedFileName = defineFileName(email);
        return getAllPersons().stream()
                .filter(f -> f.getName().contains(correctedFileName))
                .map(this::readFileAsString)
                .map(ci::getPersonFromString)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Не могу найти пользователя с email: " + email));
    }

    @Override
    public List<File> getAllPersons() {
        final File root = new File(this.root);
        if (!root.exists() || !root.isDirectory()) {
            throw new RuntimeException("Root directory: " + this.root + " not exist");
        }
        final File[] files = root.listFiles();
        if (files == null) {
            throw new RuntimeException("Root directory: " + this.root + " not exist");
        }
        return Arrays.asList(files);
    }

    private String readFileAsString(final File f) {
        try (final Stream<String> stream = Files.lines(Paths.get(String.valueOf(f)))) {
            return stream
                    .map(l -> l + "\n")
                    .reduce((a, b) -> a + b)
                    .orElseThrow(() -> new RuntimeException("Файл пустой."));
        } catch (IOException e) {
            throw new RuntimeException("Не могу прочитать файл. Причина: " + e.getMessage());
        }
    }

    private String defineFileName(final String email) {
        return email.replaceAll("[.]", "_") + ".txt";
    }
}
