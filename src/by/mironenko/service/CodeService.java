package by.mironenko.service;

import by.mironenko.domen.PersonDto;

public interface CodeService {

    String getStringFromPerson(PersonDto personDto);

    PersonDto getPersonFromString(String s);
}
