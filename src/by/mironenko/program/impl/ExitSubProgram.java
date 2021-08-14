package by.mironenko.program.impl;

import by.mironenko.program.SubProgram;

public class ExitSubProgram implements SubProgram {

    @Override
    public void execute() {
        System.out.println("Завершение работы программы.");
        System.exit(0);
    }
}
