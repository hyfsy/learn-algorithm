package com.hyf.test;

import com.hyf.algorithm.sort.SortUtil;

/**
 * @author baB_hyf
 * @date 2021/10/20
 */
public class ShellSort {

    public static void main(String[] args) {
        SortUtil.sort(ShellSort::shellSort);
    }

    public static void shellSort(int[] ins) {
        int gap = ins.length;

        do {
            gap = gap / 3 + 1;

            for (int i = gap; i < ins.length; i++) {
                int pre = i - gap;
                int temp = ins[i];
                while (pre >= 0 && ins[pre] < temp) {
                    ins[pre + gap] = ins[pre];
                    pre -= gap;
                }
                ins[pre + gap] = temp;
            }
        }
        while (gap > 1);
    }

    public static void rowSort(int[] ins, int gap) {

    }
}
