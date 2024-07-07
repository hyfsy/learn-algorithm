package com.hyf.test;

import com.hyf.algorithm.sort.SortUtil;

/**
 * @author baB_hyf
 * @date 2021/10/20
 */
public class HeapSort {

    public static void main(String[] args) {
        SortUtil.sort(HeapSort::heapSort);
    }

    public static void heapSort(int[] ins) {
        // 处理成堆结构

        int n = ins.length / 2;
        while (n >= 0) {
            adjustHeap(ins, n, ins.length);
            n--;
        }

        // 遍历排序
        n = ins.length - 1;
        while (n > 0) {
            SortUtil.swap(ins, 0, n);
            adjustHeap(ins, 0, n);
            n--;
        }
    }

    public static void adjustHeap(int[] ins, int i, int m) {
        int temp = ins[i];
        int j = i;
        while ((j = j * 2 + 1) < m) {
            if (j + 1 < m && ins[j] < ins[j + 1]) {
                j++;
            }

            if (temp >= ins[j]) {
                break;
            }

            ins[i] = ins[j];
            i = j;
        }
        ins[i] = temp;
    }
}
