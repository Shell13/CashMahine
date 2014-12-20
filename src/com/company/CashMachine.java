package com.company;

import com.company.command.CommandExecutor;
import com.company.exception.InterruptOperationException;

import java.util.Locale;

/**
 * Created by Roman on 16.07.2014.
 */
public class CashMachine {
    public static final String RESOURCE_PATH = "com.company.resources.";
    public static void main(String[] args) {
        try {
            Locale.setDefault(Locale.ENGLISH);
            CommandExecutor.execute(Operation.LOGIN);
            Operation op;
            do {
                op = ConsoleHelper.askOperation();
                CommandExecutor.execute(op);
            } while (op != Operation.EXIT);
        }catch (InterruptOperationException e){
            ConsoleHelper.printExitMessage();
        }
    }
}
