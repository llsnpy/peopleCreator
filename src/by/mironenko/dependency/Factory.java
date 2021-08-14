package by.mironenko.dependency;

import by.mironenko.service.CodeService;
import by.mironenko.service.PersonService;
import by.mironenko.service.impl.CodeServiceImpl;
import by.mironenko.service.impl.PersonServiceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Factory {
    private static String root;

    private static PersonService personService;
    private static CodeService codeService;

    static  {
        initProperty();
    }

    public static<T> T getService(final Class type) {
        if (type == PersonService.class) {
            return (T) getPersonServiceImpl();
        } else if (type == CodeService.class) {
            return (T) getCodeServiceImpl();
        }
        throw new RuntimeException("Class: " + type + " not found.");
    }

    private static PersonService getPersonServiceImpl() {
        if (personService == null) {
            personService = new PersonServiceImpl(getCodeServiceImpl(), root);
        }
        return personService;
    }

    private static CodeService getCodeServiceImpl() {
        if (codeService == null) {
            codeService = new CodeServiceImpl();
        }
        return codeService;
    }

    private static void initProperty(){
        try (final FileInputStream fileInputStream = new FileInputStream("src/properties/directory.properties")) {
            final Properties property = new Properties();
            property.load(fileInputStream);
            root = property.getProperty("directory");
            System.out.println("DIRECTORY: " + root);
        } catch (IOException e) {
            System.err.println("Файл свойств не найден.");
        }
    }
}
