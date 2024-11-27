package com.ll;

public class Calc {
    public static int run1(String expr) {
//        if ("10 + 5".equals(expr)) {
//            return 15;
//        }
//
//        if ("20 + 5".equals(expr)) {
//            return 25;
//        }
//
//        return 8;
        String[] exprBits = expr.split(" \\+ ");

        int num1 = Integer.parseInt(exprBits[0]);
        int num2 = Integer.parseInt(exprBits[1]);

        return num1+num2;
    }
}
