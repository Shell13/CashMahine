package com.company.command;

import com.company.CashMachine;
import com.company.ConsoleHelper;
import com.company.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Roman on 22.07.2014.
 */
class LoginCommand implements Command {

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards", Locale.ENGLISH);
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en", Locale.ENGLISH);

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String cN = ConsoleHelper.readString();
            String pin = ConsoleHelper.readString();
            if (validCreditCards.containsKey(cN) && validCreditCards.getString(cN).equals(pin)) {
                System.out.printf(res.getString("success.format") + "\n", cN);
                return;
            }
            System.out.printf(res.getString("not.verified.format") + "\n", cN);
            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
        }
    }
}
