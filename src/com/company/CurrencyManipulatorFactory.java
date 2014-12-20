package com.company;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Roman on 18.07.2014.
 */
public class CurrencyManipulatorFactory {

    private static Map<String, CurrencyManipulator> manipulatorMap = new HashMap<>();

    private static CurrencyManipulator manipulator;

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (manipulatorMap.containsKey(currencyCode)) {
            return manipulatorMap.get(currencyCode);
        } else {
            CurrencyManipulator man = new CurrencyManipulator(currencyCode);
            manipulatorMap.put(currencyCode, man);
            return man;
        }
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return manipulatorMap.values();
    }
}
