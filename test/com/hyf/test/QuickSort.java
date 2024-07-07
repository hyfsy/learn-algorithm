package com.hyf.test;

import com.hyf.algorithm.sort.DirectInsertSort;
import com.hyf.algorithm.sort.SortUtil;

/**
 * @author baB_hyf
 * @date 2021/10/22
 */
public class QuickSort {

    public static final int MAX_SORT_NUM = 7;

    public static void main(String[] args) {
        // SortUtil.sort(QuickSort::quickSort);
        SortUtil.sort(QuickSort::quickSortOptimize);
    }

    public static void quickSortOptimize(int[] ins) {
        qSortOptimize(ins, 0, ins.length - 1);
    }

    public static void qSortOptimize(int[] ins, int s, int e) {

        if ((e - s) > MAX_SORT_NUM) {

            while (s < e) {

                int pivot = partitionOptimize(ins, s, e);

                qSortOptimize(ins, s, pivot - 1);
                // qSortOptimize(ins, pivot + 1, e);
                s = pivot + 1;
            }
        }
        else {
            DirectInsertSort.insertSort(ins, s, e);
        }
    }

    private static int partitionOptimize(int[] ins, int s, int e) {
        int pivotV = getPivot(ins, s, e);

        while (s < e) {
            while (s < e && ins[e] >= pivotV) {
                e--;
            }
            if (s < e) {
                ins[s] = ins[e];
            }
            while (s < e && ins[s] <= pivotV) {
                s++;
            }
            if (s < e) {
                ins[e] = ins[s];
            }
        }

        ins[s] = pivotV;
        return s;
    }

    public static int getPivot(int[] ins, int s, int e) {
        int m = (e + s) / 2;
        if (ins[m] > ins[e]) {
            SortUtil.swap(ins, m, e);
        }
        if (ins[s] > ins[e]) {
            SortUtil.swap(ins, s, e);
        }
        if (ins[s] < ins[m]) {
            SortUtil.swap(ins, s, m);
        }

        return ins[s];
    }

    public static void quickSort(int[] ins) {
        qSort(ins, 0, ins.length - 1);
    }

    public static void qSort(int[] ins, int s, int e) {
        if (s >= e) {
            return;
        }

        int m = partition(ins, s, e);

        qSort(ins, s, m - 1);
        qSort(ins, m + 1, e);
    }

    private static int partition(int[] ins, int s, int e) {
        int pivot = ins[s];

        while (s < e) {
            while (s < e && ins[e] >= pivot) {
                e--;
            }
            if (s < e) {
                SortUtil.swap(ins, s, e);
            }
            while (s < e && ins[s] <= pivot) {
                s++;
            }
            if (s < e) {
                SortUtil.swap(ins, s, e);
            }
        }

        return s;
    }
}
