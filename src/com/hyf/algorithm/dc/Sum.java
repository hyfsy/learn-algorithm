package com.hyf.algorithm.dc;

/**
 * 分治-求和
 * <p>
 * O(n)
 *
 * @author baB_hyf
 * @date 2021/10/10
 */
public class Sum {

    public static void main(String[] args) {
        int[] ins = {1, 3, 5, 2};
        System.out.println(sum(ins));
    }

    public static int sum(int[] ins) {
        return sum(0, ins);
    }

    public static int sum(int start, int[] ins) {
        if (start >= ins.length) {
            return 0;
        }
        return ins[start] + sum(++start, ins);
    }
}
