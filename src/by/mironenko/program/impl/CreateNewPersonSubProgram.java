package by.mironenko.program.impl;

import by.mironenko.Utils;
import by.mironenko.dependency.Factory;
import by.mironenko.domen.PersonDto;
import by.mironenko.domen.Roles;
import by.mironenko.program.SubProgram;
import by.mironenko.service.PersonService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateNewPersonSubProgram implements SubProgram {
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    protected final PersonService service = Factory.getService(PersonService.class);

    @Override
    public void execute() {
        try {
            System.out.println("Введите имя (name)");
            final String name = bufferedReader.readLine();

            System.out.println("Введите фамилию (surname)");
            final String surname = bufferedReader.readLine();

            System.out.println("Введите email");
            final String email = bufferedReader.readLine();
            Utils.emailValidation(email);

            System.out.println("Выберите роли: уровень 1: USER, CUSTOMER; уровень 2: ADMIN, PROVIDER; уровень 3: SUPER_ADMIN");
            System.out.println("Может быть не более 2х ролей. 1 роль каждого уровня.");
            System.out.println("SUPER_ADMIN не может иметь смежных ролей.");
            System.out.println("Название роли должно быть введено вручную.");
            final String role = bufferedReader.readLine();
            final List<Roles> roles = Arrays.stream(role.split("\t"))
                    .filter(Objects::nonNull)
                    .filter(r -> !r.isEmpty())
                    .flatMap(r -> Stream.of(r.replaceAll("[,;:]", "").split(" ")))
                    .map(String::toUpperCase)
                    .map(Roles::valueOf)
                    .collect(Collectors.toList());
            Utils.roleValidation(roles);

            System.out.println("Введите номера телефонов через пробел в формате +375....");
            final String phoneNumber = bufferedReader.readLine();
            final List<String> phoneNumbers = Arrays.stream(phoneNumber.split("\t"))
                    .filter(Objects::nonNull)
                    .filter(r -> !r.isEmpty())
                    .flatMap(r -> Stream.of(r.replaceAll("[-(),;:]", "").split(" ")))
                    .collect(Collectors.toList());
            Utils.phoneNumberValidation(phoneNumbers);

            makeActionWithPerson(new PersonDto(name, surname, email, roles, phoneNumbers));
        } catch (IOException e) {
            System.out.println("Пользователь не создан из-за ошибок ввода.");
        }
    }

    public void makeActionWithPerson(final PersonDto personDto) {
        service.createPerson(personDto);
        System.out.println("Создание пользователя " + personDto.getEmail() + " завершено.");
    }
}
