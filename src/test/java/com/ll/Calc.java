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

        List<Character> operators = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        Stack<Integer> index = new Stack<>();

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
                operators.removeLast();
                continue;
            }

            if (e == '(') {
                index.push(numbers.size());
            } else if (e == '+' || e == '*' || e == '-' || e == '/') {
                if (!number.isEmpty()) {
                    numbers.add(Integer.parseInt(number.toString()));
                    number = new StringBuilder();
                }
                if (e == '-') minus = true;
                operators.add(e);
            } else if (e == ')') {
                if (!number.isEmpty()) {
                    numbers.add(Integer.parseInt(number.toString()));
                    number = new StringBuilder();
                }
                machine(operators, numbers, index.pop());

            } else {
                number.append(e);
            }

            if (i == expr.length() - 1 && (!number.isEmpty())) {
                numbers.add(Integer.parseInt(number.toString()));
            }
        }

        machine(operators, numbers, 0);

        return numbers.removeLast();
    }

    private static void machine(List<Character> operators, List<Integer> numbers, int start) {
        for (int i = start; i < operators.size(); i++) {
            if (operators.get(i) == '*' || operators.get(i) == '/') {
                int result = calculator(numbers.remove(i), numbers.remove(i), operators.remove(i));
                numbers.add(i, result);
                i--;
            }
        }

        for (int i = start; i < operators.size(); i++) {
            int result = calculator(numbers.remove(i), numbers.remove(i), operators.remove(i));
            numbers.add(i, result);
            i --;
        }
    }

    private static int calculator(int num1, int num2, char operator) {
        return switch (operator) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> -1;
        };
    }
}
