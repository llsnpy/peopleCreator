package by.mironenko.program.impl;

import by.mironenko.dependency.Factory;
import by.mironenko.program.SubProgram;
import by.mironenko.service.PersonService;

public class ViewAllPersonSubProgram implements SubProgram {
    protected final PersonService service = Factory.getService(PersonService.class);

    @Override
    public void execute() {
        System.out.println("Вывожу данные о пользователях.");

        long count = service.getAllPersons().stream()
                .peek(p -> System.out.println(p.getName() + "\n"))
                .count();
        System.out.println("Обнаружено " + count + " файлов.");
    }
}
