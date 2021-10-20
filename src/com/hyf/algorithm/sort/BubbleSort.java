package com.hyf.algorithm.sort;

/**
 * 冒泡排序
 * <p>
 * O(n^2)
 *
 * @author baB_hyf
 * @date 2021/10/17
 */
public class BubbleSort {

    public static void main(String[] args) {
        SortUtil.sort(BubbleSort::swapSort);
        SortUtil.sort(BubbleSort::bubbleSort);
    }

    // 伪冒泡，交换排序
    public static void swapSort(int[] ins) {
        for (int i = 0; i < ins.length - 1; i++) {
            for (int j = i + 1; j < ins.length; j++) {
                if (ins[i] > ins[j]) {
                    SortUtil.swap(ins, i, j);
                }
            }
        }
    }

    public static void bubbleSort(int[] ins) {
        // for (int i = 0; i < ins.length - 1; i++) {
        //     for (int j = ins.length - 1; j > i; j--) {
        //         if (ins[j] < ins[j - 1]) {
        //             SortUtil.swap(ins, j - 1, j);
        //         }
        //     }
        // }

        boolean ordered = true; // 优化，防止已经有序的集合再重复比较

        for (int i = ins.length - 1; i > 0 && ordered; i--) {
            ordered = false;
            for (int j = 0; j < i; j++) {
                if (ins[j] > ins[j + 1]) {
                    SortUtil.swap(ins, j, j + 1);
                    ordered = true;
                }
            }
        }
    }
}
