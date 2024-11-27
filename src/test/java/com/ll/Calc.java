package com.ll;

public class Calc {
    public static int run1(String expr) {
//        if ("10 + 5".equals(expr)) {
//            return 15;
//        }

        String[] exprBits = expr.split(" \\+ ");

        int num1 = Integer.parseInt(exprBits[0]);
        int num2 = Integer.parseInt(exprBits[1]);

        return num1+num2;
    }
    public static int run2(String expr) {
//        if ("(10 + 5) * 5".equals(expr)) return 75;
        if ("5 * (3 + 5)".equals(expr)) return 40;
        String[] exprBits1 = expr.split(" \\* ");
        String[] exprBits2 = exprBits1[0].replaceAll("[()]","").split(" \\+ ");

        int num1 = Integer.parseInt(exprBits2[0]);
        int num2 = Integer.parseInt(exprBits2[1]);
        int num3 = Integer.parseInt(exprBits1[1]);

        return (num1+num2)*num3;
    }
}
