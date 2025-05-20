package com.dspatched.tasks;

import java.util.Set;

public class ConvertStringToInt {

    public static void main(String[] args) {
        System.out.println(myAtoi("   +0 123"));
    }

    public static int myAtoi(String s) {
        String result = "";
        Set<Character> digits = Set.of('1','2','3','4','5','6','7','8','9');
        boolean leadingDigit = false;
        boolean negative = false;
        boolean signAssigned = false;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == ' ' && result.isEmpty()) {
                continue;
            }
            if ((cur == '+' || cur == '-') && signAssigned) {
                break;
            }
            if (cur == '+' && !leadingDigit) {
                signAssigned = true;
            }
            if (cur == '-' && result.length() == 0) {
                negative = true;
                signAssigned = true;
                continue;
            }
            if (cur == '0') {
                result = leadingDigit ? result + cur : "0";
            }
            if (digits.contains(cur)) {
                result += cur;
                leadingDigit = true;
            } else if (cur != '0' && cur != '+') {
                break;
            }
        }
        if (result.isEmpty()) {
            return 0;
        }
        long num = Long.valueOf(result);
        num = negative ? (-1) * num : num;
        if (num > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (num < 0 && num < (long)Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) num;
    }

}
