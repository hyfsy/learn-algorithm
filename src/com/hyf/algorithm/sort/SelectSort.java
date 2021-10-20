package com.hyf.algorithm.sort;

/**
 * 简单选择排序
 * <p>
 * O(n^2)
 *
 * @author baB_hyf
 * @date 2021/10/10
 */
public class SelectSort {

    public static void main(String[] args) {
        SortUtil.sort(SelectSort::selectSort);
    }

    public static void selectSort(int[] ins) {
        for (int i = 0; i < ins.length - 1; i++) {

            // 选最小值
            int min = i;
            for (int j = min + 1; j < ins.length; j++) {
                if (ins[j] < ins[min]) {
                    min = j;
                }
            }

            SortUtil.swap(ins, i, min);
        }
    }
}
