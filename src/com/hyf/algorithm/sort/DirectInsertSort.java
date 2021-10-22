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
        insertSort(ins, 0, ins.length - 1);
    }

    public static void insertSort(int[] ins, int s, int e) {

        for (int i = s + 1; i <= e; i++) {
            int k = i;
            int temp = ins[k];


            // for (int j = i - 1; j >= 0; j--) {
            //     if (tmp < ins[j]) {
            //         SortUtil.swap(ins, j, j + 1); // 移动位置
            //     }
            //     else {
            //         ins[j + 1] = tmp;
            //         break;
            //     }
            // }


            for (; k > s && temp < ins[k - 1] /* 省内部的break */; k--) {
                ins[k] = ins[k - 1];
            }
            ins[k] = temp;
        }

    }
}
