package com.hyf.algorithm;

/**
 * 斐波那契查询，基于黄金分割率
 *
 * @author baB_hyf
 * @date 2021/10/16
 */
public class FibonacciSearch {

    public static void main(String[] args) {
        int[] ins = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(fibonacciSearch(ins, 5));
    }

    public static int fibonacciSearch(int[] ins, int key) {
        int l = ins.length;
        int[] fibonacciArray = getFibonacciArray(l);

        int k = 0;
        while (l > fibonacciArray[k]) {
            k++;
        }

        int low = ins[0];
        int high = ins[l - 1];
        int mid;
        while (low <= high) {
            mid = low + fibonacciArray[k - 1] - 1;
            int now = ins[mid];

            if (key < now) {
                high = mid - 1;
                k = k - 1;
            }
            else if (key > now) {
                low = mid + 1;
                k = k - 2;
            }
            else {
                return mid;
            }
        }

        return Integer.MAX_VALUE;
    }

    public static int[] getFibonacciArray(int n) {
        int[] ins = new int[n];
        for (int i = 0; i < ins.length; i++) {
            ins[i] = Fibonacci.fibonacci(i);
        }
        return ins;
    }
}
