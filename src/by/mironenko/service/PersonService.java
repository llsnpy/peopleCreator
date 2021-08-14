package by.mironenko.service;

import by.mironenko.domen.PersonDto;

import java.io.File;
import java.util.List;

public interface PersonService {

    void createPerson(final PersonDto personDto);

    void deletePersonByEmail(final String email);

    void updatePerson(final PersonDto personDto);

    PersonDto findPersonByEmail(final String email);

    List<File> getAllPersons();
}
