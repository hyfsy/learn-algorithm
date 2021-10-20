package com.hyf.algorithm.sort;

/**
 * 归并排序，比较占用内存但效率高且稳定
 * <p>
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(n + logn)，非递归：O(n)
 *
 * @author baB_hyf
 * @date 2021/10/17
 */
public class MergingSort {

    public static void main(String[] args) {
        SortUtil.sort(MergingSort::mergingSort);
        SortUtil.sort(MergingSort::levelupMergingSort);
    }

    public static void levelupMergingSort(int[] ins) {
        int[] tins = new int[ins.length];
        int e = ins.length - 1;
        int k = 1; // 两两合并间隔
        while (k <= ins.length) {
            // 两个数组交替填充合并结果
            levelupMerge(ins, tins, k, e);
            k *= 2;
            levelupMerge(tins, ins, k, e);
            k *= 2;
        }
    }

    public static void levelupMerge(int[] sins, int[] tins, int k, int e) {

        int i = 0;
        while (i + 2 * k <= e) {
            merge(sins, tins, i, i + k - 1, i + 2 * k - 1); // -1 间隔需要
            i = i + 2 * k;
        }

        if (i + k <= e) {
            merge(sins, tins, i, i + k - 1, e); // 剩下的大于k，但达不到2k的情况，说明也能合并下
        }
        else {
            for (int j = i; j <= e; j++) { // 达不到k，直接顺序填充
                tins[j] = sins[j];
            }
        }
    }

    public static void mergingSort(int[] ins) {
        sort(ins, ins, 0, ins.length - 1);
    }

    public static void sort(int[] sins, int[] tins, int s, int e) {
        if (s == e) {
            tins[s] = sins[s]; // 这边设置的是临时数组，不要搞混
            return;
        }

        int[] temp = new int[e + 1];
        int m = (s + e) / 2; // 分割数组，分割结果放入临时数组中
        sort(sins, temp, s, m);
        sort(sins, temp, m + 1, e);
        merge(temp, tins, s, m, e); // 临时数组的结果合并到目标排序数组中
    }

    public static void merge(int[] sins, int[] tins, int s, int m, int e) {

        // 临时数组的结果，放入tins中，tins也代表了上次的临时数组

        int i = s, j = m + 1;
        int k = i; // 填充空数组的指针
        for (; i <= m && j <= e; k++) {
            // 比较前后两个指针数据，小的先放入到空数组中
            if (sins[i] < sins[j]) {
                tins[k] = sins[i++];
            }
            else {
                tins[k] = sins[j++];
            }
        }

        // 前半部分的指针还有剩余的数据
        if (i <= m) {
            for (int l = i; l <= m; l++) {
                tins[k++] = sins[l];
            }
        }
        // 后半部分的指针还有剩余的数据
        if (j <= e) {
            for (int l = j; l <= e; l++) {
                tins[k++] = sins[l];
            }
        }
    }
}
