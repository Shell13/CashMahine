package com.company.command;

import com.company.CashMachine;
import com.company.ConsoleHelper;
import com.company.CurrencyManipulator;
import com.company.CurrencyManipulatorFactory;
import com.company.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Roman on 20.07.2014.
 */
class DepositCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en", Locale.ENGLISH);

    @Override
    public void execute() throws InterruptOperationException {
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());
        String[] s = ConsoleHelper.getValidTwoDigits(manipulator.getCurrencyCode());
        int denomination = Integer.parseInt(s[0]);
        int amount = Integer.parseInt(s[1]);
        manipulator.addAmount(denomination, amount);
        System.out.println(res.getString("before"));
        System.out.printf(res.getString("success.format") + "\n", denomination*amount, manipulator.getCurrencyCode());
    }
}
