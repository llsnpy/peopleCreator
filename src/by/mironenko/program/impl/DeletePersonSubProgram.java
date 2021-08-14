package by.mironenko.program.impl;

import by.mironenko.dependency.Factory;
import by.mironenko.program.SubProgram;
import by.mironenko.service.PersonService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeletePersonSubProgram implements SubProgram {
    private final PersonService service = Factory.getService(PersonService.class);
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void execute() {
        System.out.println("Введите email пользователя для удаления.");
        final String email;
        try {
            email = bufferedReader.readLine();
            service.deletePersonByEmail(email);
        } catch (IOException e) {
            System.out.println("Не удалось удалить пользователя.");
            return;
        }
        System.out.println("Пользователь c email " + email + " удален.");
    }
}
