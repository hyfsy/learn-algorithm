package com.hyf.algorithm.sort;

/**
 * 直接插入排序
 * <p>
 * O(n^2)
 *
 * @author baB_hyf
 * @date 2021/10/17
 */
public class DirectInsertSort {

    public static void main(String[] args) {
        SortUtil.sort(DirectInsertSort::directInsertSort);
    }

    public static void directInsertSort(int[] ins) {

        for (int i = 1; i < ins.length; i++) {
            int tmp = ins[i];

            // for (int j = i - 1; j >= 0; j--) {
            //     if (tmp < ins[j]) {
            //         SortUtil.swap(ins, j, j + 1); // 移动位置
            //     }
            //     else {
            //         ins[j + 1] = tmp;
            //         break;
            //     }
            // }

            int j = i - 1;
            for (; j >= 0 && tmp < ins[j] /* 省内部的break */; j--) {
                SortUtil.swap(ins, j, j + 1);
            }
            ins[j + 1] = tmp;
        }
    }
}
