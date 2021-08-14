package by.mironenko.runner;

import by.mironenko.program.SubProgram;
import by.mironenko.program.impl.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Runner {

    public static void run() {
        while (true) {
            outputMenu();
            final int action = defineAction();
            final SubProgram subProgram = selectSubProgram(action);
            try {
                subProgram.execute();
            } catch (final RuntimeException e) {
                System.out.println("Во время выполнения программы произошла ошибка");
            }
        }
    }

    private static SubProgram selectSubProgram(int action) {
        if (action == -1) {
            return new DoNothingSubProgram();
        } else if (action == 0) {
            return new ExitSubProgram();
        } else if (action == 1) {
            return new ViewAllPersonSubProgram();
        } else if (action == 2) {
            return new CreateNewPersonSubProgram();
        } else if (action == 3) {
            return new UpdatePersonSubProgram();
        } else if (action == 4) {
            return new DeletePersonSubProgram();
        } else if (action == 5) {
            return new ViewDetailedInformationAboutPersonSubProgram();
        } else {
            System.out.println("Выбранной программы не существует");
            return new DoNothingSubProgram();
        }
    }

    private static void outputMenu() {
        System.out.println("\n");
        System.out.println("Для завершения работы программы введите 0");
        System.out.println("Для вывода всех пользователей введите 1");
        System.out.println("Для создания нового пользователя введите 2");
        System.out.println("Для редактирования существующего пользователя введите 3");
        System.out.println("Для удаления пользователя нажмите 4");
        System.out.println("Для вывода детальной информации о пользователе введите 5");
    }

    private static int defineAction() {
        String programNumber = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            programNumber = reader.readLine();
        } catch (final RuntimeException | IOException e) {
            System.out.println("Не могу прочитать строку");
            System.out.println("Попробуйте еще раз");
        }
        assert programNumber != null;
        return Integer.parseInt(programNumber);
    }
}
