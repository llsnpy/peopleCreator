package by.mironenko.program.impl;

import by.mironenko.Utils;
import by.mironenko.dependency.Factory;
import by.mironenko.domen.PersonDto;
import by.mironenko.program.SubProgram;
import by.mironenko.service.CodeService;
import by.mironenko.service.PersonService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ViewDetailedInformationAboutPersonSubProgram implements SubProgram {
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    protected final PersonService service = Factory.getService(PersonService.class);
    private final CodeService codeService = Factory.getService(CodeService.class);

    @Override
    public void execute() {
        System.out.println("Введите email");
        try {
            final String email = bufferedReader.readLine();
            Utils.emailValidation(email);
            final PersonDto personDto = service.findPersonByEmail(email);
            System.out.println(codeService.getStringFromPerson(personDto));
        } catch (IOException e) {
            System.out.println("Введен неверный (несуществующий) email ");;
        }
    }
}
