package com.hyf.algorithm.sort;

/**
 * 希尔排序，直接插入排序的改进
 * <p>
 * O(n^3/2)
 *
 * @author baB_hyf
 * @date 2021/10/17
 */
public class ShellSort {

    public static void main(String[] args) {
        SortUtil.sort(ShellSort::shellSort);
    }

    public static void shellSort(int[] ins) {
        int gap = ins.length;

        do {
            // 计算区间增量长度
            gap = gap / 3 + 1; // 迄今还未找到一个好的增量序列

            for (int i = gap; i < ins.length; i++) {
                int pre = i - gap;
                int tmp = ins[i];

                // 对i之前的所有当前间隔对应的节点做插入排序
                while (pre >= 0 && ins[pre] > tmp) {
                    ins[pre + gap] = ins[pre]; // 节点后移
                    pre = pre - gap;
                }
                ins[pre + gap] = tmp;
            }
        }
        // 基本有序后，最后相当于直接插入排序
        while (gap > 1);
    }
}
