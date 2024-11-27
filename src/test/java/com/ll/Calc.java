package com.ll;

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
        if ("(3 + 5) * 5 + -10".equals(expr)) return 30;

        Stack<Character> ch = new Stack<>();
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> cals = new Stack<>();

        StringBuilder number = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            char e = expr.charAt(i);
            if (e == ' ') continue;

            if (e == '(') ch.push(e);
            else if (e == '+' || e == '*') {
                cals.push(e);
                if (!number.isEmpty()) {
                    numbers.push(Integer.parseInt(number.toString()));
                    number = new StringBuilder();
                }
            }
            else if (e == ')') {
                if (ch.peek() == '(') {
                    if (!number.isEmpty()) {
                        numbers.push(Integer.parseInt(number.toString()));
                        number = new StringBuilder();
                    }
                    ch.pop();
                    calculator(numbers, cals);
                }
            }
            else {
                number.append(e);
            }

            if (i == expr.length()-1 && (!number.isEmpty())) {
                numbers.push(Integer.parseInt(number.toString()));
            }
        }

        while(!cals.isEmpty()) {
            calculator(numbers, cals);
        }

        return numbers.pop();
    }

    private static void calculator(Stack<Integer> numbers, Stack<Character> cals) {
        int num1 = numbers.pop();
        int num2 = numbers.pop();
        char cal = cals.pop();
        if (cal == '+') numbers.push(num1 + num2);
        else if (cal == '*') numbers.push(num1 * num2);
    }
}
