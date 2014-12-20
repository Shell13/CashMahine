package com.company.command;

import com.company.Operation;
import com.company.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Roman on 20.07.2014.
 */
public final class CommandExecutor {
    private CommandExecutor() {
    }

    private static Map<Operation, Command> map = new HashMap<>();
    static {
        map.put(Operation.LOGIN, new LoginCommand());
        map.put(Operation.INFO, new InfoCommand());
        map.put(Operation.DEPOSIT, new DepositCommand());
        map.put(Operation.WITHDRAW, new WithdrawCommand());
        map.put(Operation.EXIT, new ExitCommand());
    }

    public static final void execute(Operation operation) throws InterruptOperationException {
        Command command = map.get(operation);
        command.execute();
    }
}
