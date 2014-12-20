package com.company.command;

import com.company.CashMachine;
import com.company.ConsoleHelper;
import com.company.CurrencyManipulator;
import com.company.CurrencyManipulatorFactory;
import com.company.exception.InterruptOperationException;
import com.company.exception.NotEnoughMoneyException;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Roman on 20.07.2014.
 */
class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en", Locale.ENGLISH);

    @Override
    public void execute() throws InterruptOperationException {
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        while (true) {
            try {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                int sum = Integer.parseInt(ConsoleHelper.readString());
                if (!manipulator.isAmountAvailable(sum) || sum <= 0) {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    continue;
                }
                Map<Integer, Integer> cash = manipulator.withdrawAmount(sum);
                int result = 0;
                for (Map.Entry<Integer, Integer> pair : cash.entrySet()) {
                    result += pair.getKey() * pair.getValue();
                    System.out.println("\t" + pair.getKey() + " - " + pair.getValue());
                }
                System.out.printf(res.getString("success.format") + "\n", result, manipulator.getCurrencyCode());
                return;
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }
    }
}
