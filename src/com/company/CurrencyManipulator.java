package com.company;

import com.company.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Roman on 18.07.2014.
 */
public class CurrencyManipulator {

    private String currencyCode;
    Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.get(denomination) == null) denominations.put(denomination, count);
        else
            denominations.put(denomination, denominations.get(denomination) + count);
    }

    public int getTotalAmount() {
        int result = 0;
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet()) {
            result += pair.getKey() * pair.getValue();
        }
        return result;
    }

    public boolean hasMoney() {
        return denominations.size() > 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {

        int expected = expectedAmount;

        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };

        TreeMap<Integer, Integer> sortMap = new TreeMap<>(comp);
        TreeMap<Integer, Integer> cash = new TreeMap<>(comp);

        sortMap.putAll(denominations);

        for (Map.Entry<Integer, Integer> pair : sortMap.entrySet()) {
            while (pair.getKey() <= expected && pair.getValue() > 0) {
                int nominal = pair.getKey();
                int count = pair.getValue();
                if (cash.get(nominal) == null) cash.put(nominal, 1);
                else cash.put(nominal, cash.get(nominal) + 1);
                expected -= nominal;
            }
        }
        int sum = 0;
        for (Map.Entry<Integer, Integer> pair : cash.entrySet()) {
            sum += pair.getKey() * pair.getValue();
        }
        if (sum != expectedAmount) throw new NotEnoughMoneyException();
        for (Map.Entry<Integer, Integer> pair : sortMap.entrySet()) {
            int nominal = pair.getKey();
            int count = pair.getValue();
            if (cash.containsKey(nominal))denominations.put(nominal, count - cash.get(nominal));
        }
        return cash;
    }
}