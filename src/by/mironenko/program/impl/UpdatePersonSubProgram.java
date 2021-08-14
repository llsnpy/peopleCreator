package by.mironenko.program.impl;

import by.mironenko.domen.PersonDto;

public class UpdatePersonSubProgram extends CreateNewPersonSubProgram {

    public void execute(final PersonDto personDto) {
        service.updatePerson(personDto);
        System.out.println("Обновление пользователя " + personDto.getEmail() + " завершено.");
    }
}
