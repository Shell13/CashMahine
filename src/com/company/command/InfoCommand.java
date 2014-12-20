package com.company.command;

import com.company.CashMachine;
import com.company.ConsoleHelper;
import com.company.CurrencyManipulator;
import com.company.CurrencyManipulatorFactory;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Roman on 20.07.2014.
 */
class InfoCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en", Locale.ENGLISH);

    @Override
    public void execute() {
        System.out.println(res.getString("before"));
        if (!(CurrencyManipulatorFactory.getAllCurrencyManipulators().size() > 0)) System.out.println(res.getString("no.money"));
        else
        for (CurrencyManipulator man : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (man.hasMoney())
            ConsoleHelper.writeMessage(man.getCurrencyCode() + " - " + man.getTotalAmount());
            else ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}
