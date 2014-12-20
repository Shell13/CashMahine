package com.company;

import com.company.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Roman on 16.07.2014.
 */
public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en", Locale.ENGLISH);
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String s = null;
        try {
            s = reader.readLine();
            if (s.equalsIgnoreCase("exit")) {
                System.out.println(res.getString("the.end"));
                throw new InterruptOperationException();
            }
        } catch (IOException ignore) {
        }
        return s;
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            try {
                writeMessage(res.getString("choose.operation"));
                writeMessage(res.getString("operation.INFO"));
                writeMessage(res.getString("operation.DEPOSIT"));
                writeMessage(res.getString("operation.WITHDRAW"));
                writeMessage(res.getString("operation.EXIT"));
                String s = readString();
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(s));

            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException {

        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            String s = readString();
            if (s.matches("[a-zA-Z]{3}")) return s.toUpperCase();
            writeMessage(res.getString("invalid.data"));
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            System.out.printf(res.getString("choose.denomination.and.count.format") + "\n", currencyCode);
            String str = readString();
            String[] arr = str.split(" ");
            try {
                if (arr.length > 2) throw new Exception();
                int n = Integer.parseInt(arr[0]);
                int c = Integer.parseInt(arr[1]);
                if (n > 0 && c > 0) {
                    return arr;
                }
            } catch (Exception e) {
                System.out.println("Please specify valid data.");
            }
        }
    }
    public static void printExitMessage(){
        ConsoleHelper.writeMessage("Good Luck!");
    }
}
