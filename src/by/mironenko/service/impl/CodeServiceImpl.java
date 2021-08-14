package by.mironenko.service.impl;

import by.mironenko.domen.PersonDto;
import by.mironenko.domen.Roles;
import by.mironenko.service.CodeService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CodeServiceImpl implements CodeService {
    private static String SEPARATOR = "\t";

    @Override
    public String getStringFromPerson(PersonDto personDto) {
        if (personDto == null) {
            System.out.println("Введены неверные параметры.");
        }
        final StringBuilder s = new StringBuilder();
        s.append(personDto.getName()).append("\n");
        s.append(personDto.getSurname()).append("\n");
        s.append(personDto.getEmail()).append("\n");
        s.append(convertToString(personDto.getRoles())).append("\n");
        s.append(convertToString(personDto.getNumbers())).append("\n");
        return s.toString();
    }

    @Override
    public PersonDto getPersonFromString(String s) {
        if (s == null || s.isEmpty()) {
            throw new RuntimeException("Указаны неверные значения.");
        }
        final String[] lines = s.split("\n");
        if (lines.length != 5) {
            throw new RuntimeException("Указано неверное количество значений.");
        }
        final String name = lines[0];
        final String surname = lines[1];
        final String email = lines[2];
        final List<Roles> roles = Arrays.stream(lines[3].split("\t"))
                .filter(Objects::nonNull)
                .filter(r -> !r.isEmpty())
                .map(r -> Roles.valueOf(r))
                .collect(Collectors.toList());
        final List<String> numbers = Arrays.stream(lines[4].split("\t"))
                .filter(Objects::nonNull)
                .filter(r -> !r.isEmpty())
                .collect(Collectors.toList());
        return new PersonDto(name, surname, email, roles, numbers);
    }

    private static <T> String convertToString(final List<T> list) {
        return list
                .stream()
                .map(s -> s + SEPARATOR)
                .reduce((a, b) -> a + b)
                .orElse("");
    }
}
