package com.hyf.algorithm;

/**
 * 顺序查找
 *
 * @author baB_hyf
 * @date 2021/10/16
 */
public class LineSearch {

    public static void main(String[] args) {
        int[] ins = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(lineSearch(ins, 5));
        System.out.println(sentinelOptimize(ins, 5));
    }

    public static int lineSearch(int[] ins, int search) {
        for (int i = 0; i < ins.length; i++) {
            if (ins[i] == search) {
                return i;
            }
        }

        return Integer.MAX_VALUE;
    }

    // 哨兵技巧，性能优化
    public static int sentinelOptimize(int[] ins, int search) {
        int temp = ins[0];
        ins[0] = search; // 设置sentinel，这操作不大合适

        int i = ins.length - 1;
        while (ins[i] != search) { // 减少了for循环的比较操作
            i--;
        }

        if (i == 0 && ins[0] != temp) { // 最后再比较下被sentinel排掉的值
            return Integer.MAX_VALUE;
        }
        return i;
    }
}
