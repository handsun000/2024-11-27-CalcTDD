package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
        List<Character> calsFirst = new ArrayList<>();
        List<List<Character>> listCal = new ArrayList<>();
        Stack<Integer> index = new Stack<>();
        boolean flag = false;
        boolean minus = false;

        StringBuilder number = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            char e = expr.charAt(i);
            if (e == ' ') {
                minus = false;
                continue;
            }
            if (minus && Character.isDigit(e)) {
                number.append("-").append(e);
                minus = false;
                if (flag) calsFirst.removeLast();
                else cals.removeLast();
                continue;
            }

            if (e == '(') {
                ch.add(e);
                calsFirst = new ArrayList<>();
                listCal.add(calsFirst);
                index.push(numbers.size());
                flag = true;
            }
            else if (e == '+' || e == '*' || e == '-') {
                if (!number.isEmpty()) {
                    numbers.add(Integer.parseInt(number.toString()));
                    number = new StringBuilder();
                }
                if (e == '-') minus = true;
                if (flag) calsFirst.add(e);
                else cals.add(e);
                
            } else if (e == ')') {
                if (ch.getLast() == '(') {
                    if (!number.isEmpty()) {
                        numbers.add(Integer.parseInt(number.toString()));
                        number = new StringBuilder();
                    }
                    ch.removeLast();
                    machine(listCal.removeLast(), numbers, index.pop());
                    if (!listCal.isEmpty()) calsFirst = listCal.getLast();

                    if (ch.isEmpty()) flag = false;
                }
            } else {
                number.append(e);
            }

            if (i == expr.length() - 1 && (!number.isEmpty())) {
                numbers.add(Integer.parseInt(number.toString()));
            }
        }

        machine(cals, numbers, 0);

        return numbers.removeLast();
    }

    private static void machine(List<Character> cals, List<Integer> numbers, int st) {
        while (!cals.isEmpty()) {
            if (cals.contains('*')) {
                int index = cals.indexOf('*');
                calculator(numbers, cals.remove(index), st+index);
            }
            else {
                calculator(numbers, cals.removeLast(), -1);
            }
        }
    }

    private static void calculator(List<Integer> numbers, char cal, int index) {
        int num1 = (index != -1) ? numbers.remove(index) : numbers.removeLast();
        int num2 = (index != -1) ? numbers.remove(index) : numbers.removeLast();

        if (cal == '+') numbers.add(num2 + num1);
        else if (cal == '*') numbers.add(num2 * num1);
        else if (cal == '-') numbers.add(num2 - num1);
    }
}
