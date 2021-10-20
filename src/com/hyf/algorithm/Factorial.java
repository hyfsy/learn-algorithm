package com.hyf.algorithm;

/**
 * 阶乘
 *
 * @author baB_hyf
 * @date 2021/10/10
 */
public class Factorial {

    public static void main(String[] args) {
        System.out.println(factorial(3));
    }

    public static int factorial(int x) {
        if (x == 1) {
            return 1;
        }
        return x * factorial(x - 1);
    }
}
