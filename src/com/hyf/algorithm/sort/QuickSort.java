package com.hyf.algorithm.sort;

/**
 * 快速排序，冒泡排序的改进
 * <p>
 * 最糟情况：O(n^2)
 * 平均情况：O(nlogn)
 *
 * @author baB_hyf
 * @date 2021/10/10
 */
public class QuickSort {

    public static final int MAX_LENGTH_INSERT_OPERATE = 7;

    public static void main(String[] args) {
        SortUtil.sort(QuickSort::quickSort);
        SortUtil.sort(QuickSort::quickSortOptimize);
    }

    public static void quickSort(int[] ins) {
        qSort(ins, 0, ins.length - 1);
    }

    public static void qSort(int[] ins, int l, int h) {
        if (l >= h) {
            return;
        }

        int pivot = partition(ins, l, h);
        qSort(ins, l, pivot - 1);
        qSort(ins, pivot + 1, h);
    }

    public static int partition(int[] ins, int l, int h) {
        int pivot = ins[l];

        // 将主轴放入中间，保证左侧的小，右侧的大
        while (l < h) {
            while (l < h && ins[h] >= pivot) { // pivot开始选的l，所以得先从h开始，从两端向中间扫描
                h--;
            }
            SortUtil.swap(ins, l, h);
            while (l < h && ins[l] <= pivot) {
                l++;
            }
            SortUtil.swap(ins, l, h);
        }

        return l;
    }

    public static void quickSortOptimize(int[] ins) {
        qSortOptimize(ins, 0, ins.length - 1);
    }

    public static void qSortOptimize(int[] ins, int l, int h) {
        // 3. 小数组的排序效率没有直接插入排序高
        if ((h - l) > MAX_LENGTH_INSERT_OPERATE) {
            int pivot;
            while (l < h) {
                pivot = partitionOptimize(ins, l, h);
                qSortOptimize(ins, l, pivot - 1); // 前半部分递归排序
                l = pivot + 1; // 4. 尾递归
            }
        }
        else {
            DirectInsertSort.directInsertSort(ins);
        }
    }

    public static int partitionOptimize(int[] ins, int l, int h) {
        int pivot = getPivot(ins, l, h); // 1. 优化枢轴

        while (l < h) {
            while (l < h && ins[h] >= pivot) {
                h--;
            }
            ins[l] = ins[h]; // 2. 减少pivot的交换操作
            while (l < h && ins[l] <= pivot) {
                l++;
            }
            ins[h] = ins[l];
        }
        ins[l] = pivot; // 设置中部位置的值

        return l;
    }

    // 优化枢轴 - 三数取中
    public static int getPivot(int[] ins, int l, int h) {
        // int m =  (h + l) / 2;
        int m = l + (h - l) / 2;

        // 把三数中最大的放到最后
        if (ins[l] > ins[h]) {
            SortUtil.swap(ins, l, h);
        }
        if (ins[m] > ins[h]) {
            SortUtil.swap(ins, m, h);
        }
        // 比较两数中大的为中间数
        if (ins[m] > ins[l]) {
            SortUtil.swap(ins, l, m);
        }

        return ins[l];
    }
}
