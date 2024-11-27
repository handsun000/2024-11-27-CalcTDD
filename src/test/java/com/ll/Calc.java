package com.ll;

import java.util.ArrayList;
import java.util.List;

public class Calc {
    public static int run1(String expr) {
//        if ("10 + 5".equals(expr)) {
//            return 15;
//        }

        String[] exprBits = expr.split(" \\+ ");

        int num1 = Integer.parseInt(exprBits[0]);
        int num2 = Integer.parseInt(exprBits[1]);

        return num1 + num2;
    }

    public static int run2(String expr) {
//        if ("(10 + 5) * 5".equals(expr)) return 75;
//        if ("5 * (3 + 5)".equals(expr)) return 40;
//        String[] exprBits1 = expr.split(" \\* ");
//        String[] exprBits2 = exprBits1[0].replaceAll("[()]","").split(" \\+ ");
//
//        int num1 = Integer.parseInt(exprBits2[0]);
//        int num2 = Integer.parseInt(exprBits2[1]);
//        int num3 = Integer.parseInt(exprBits1[1]);
//        if ("(3 + 5) * 5 + -10".equals(expr)) return 30;
//        if("-10 + 5 * (3 + 5)".equals(expr)) return 30;

        List<Character> ch = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        List<Character> cals = new ArrayList<>();

        StringBuilder number = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            char e = expr.charAt(i);
            if (e == ' ') continue;

            if (e == '(') ch.add(e);
            else if (e == '+' || e == '*') {
                if (!number.isEmpty()) {
                    numbers.add(Integer.parseInt(number.toString()));
                    number = new StringBuilder();
                }
                cals.add(e);
            } else if (e == ')') {
                if (ch.getLast() == '(') {
                    if (!number.isEmpty()) {
                        numbers.add(Integer.parseInt(number.toString()));
                        number = new StringBuilder();
                    }
                    ch.removeLast();
                    calculator(numbers, cals.removeLast(), -1);
                }
            } else {
                number.append(e);
            }

            if (i == expr.length() - 1 && (!number.isEmpty())) {
                numbers.add(Integer.parseInt(number.toString()));
            }
        }

        while (!cals.isEmpty()) {
            if (cals.contains('*')) {
                int index = cals.indexOf('*');
                calculator(numbers, cals.remove(index), index);
            }
            else {
                calculator(numbers, cals.removeLast(), -1);
            }
        }

        return numbers.removeLast();
    }

    private static void calculator(List<Integer> numbers, char cal, int index) {
        int num1 = (index != -1) ? numbers.remove(index) : numbers.removeLast();
        int num2 = (index != -1) ? numbers.remove(index) : numbers.removeLast();

        if (cal == '+') numbers.add(num1 + num2);
        else if (cal == '*') numbers.add(num1 * num2);
    }
}
