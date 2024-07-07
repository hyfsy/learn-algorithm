package com.hyf.test;

import com.hyf.algorithm.sort.SortUtil;

/**
 * @author baB_hyf
 * @date 2021/10/20
 */
public class MergingSort {

    public static void main(String[] args) {
        // SortUtil.sort(MergingSort::mergingSort);
        SortUtil.sort(MergingSort::levelupMergingSort);
    }

    public static void levelupMergingSort(int[] ins) {
        int gap = 1;

        int[] temp = new int[ins.length];

        while (gap <= ins.length) {
            levelupMerge(ins, temp, gap);
            gap *= 2;
            levelupMerge(temp, ins, gap);
            gap *= 2;
        }
    }

    public static void levelupMerge(int[] sins, int[] tins, int gap) {
        int k = 0;
        while (k + gap * 2 - 1 < sins.length) {

            merge(sins, tins, k, k + gap - 1, k + gap * 2 - 1);

            k = k + gap * 2;
        }


        if (k + gap - 1 < sins.length) {
            merge(sins, tins, k, k + gap - 1, sins.length - 1);
        }
        else {
            for (int i = k; i < sins.length; i++) {
                tins[i] = sins[i];
            }
        }

        // ins = temp;

    }

    public static void mergingSort(int[] ins) {
        sort(ins, ins, 0, ins.length - 1);
    }

    public static void sort(int[] sins, int[] tins, int s, int e) {
        if (s == e) {
            tins[s] = sins[s];
            return;
        }

        int m = (s + e) / 2;
        int[] temp = new int[e + 1];
        sort(sins, temp, s, m);
        sort(sins, temp, m + 1, e);
        merge(temp, tins, s, m, e);
    }

    public static void merge(int[] sins, int[] tins, int s, int m, int e) {

        int k = s;
        int i = s, j = m + 1;
        for (; i <= m && j <= e; k++) {
            if (sins[i] < sins[j]) {
                tins[k] = sins[i++];
            }
            else {
                tins[k] = sins[j++];
            }
        }

        if (i <= m) {
            for (int l = i; l <= m; l++) {
                tins[k++] = sins[l];
            }
        }
        if (j <= e) {
            for (int l = j; l <= e; l++) {
                tins[k++] = sins[l];
            }
        }
    }
}
