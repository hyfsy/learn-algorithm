package com.hyf.algorithm;

/**
 * 斐波那契数列
 *
 * @author baB_hyf
 * @date 2021/10/13
 */
public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(fibonacci(12));
    }

    public static int fibonacci(int i) {
        if (i <= 1) {
            return i;
        }

        return fibonacci(i -1) + fibonacci(i - 2);
    }
}
