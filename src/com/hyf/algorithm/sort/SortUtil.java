package com.hyf.algorithm.sort;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author baB_hyf
 * @date 2021/10/17
 */
public class SortUtil {

    public static int[] get() {
        return new int[]{1, 3, 4, 2, 7, 6, 9, 5, 8, 0, 10};
    }

    public static void swap(int[] ins, int i, int j) {
        int tmp = ins[i];
        ins[i] = ins[j];
        ins[j] = tmp;
    }

    public static int[] copy(int[] is) {
        int[] ins = new int[is.length];
        System.arraycopy(is, 0, ins, 0, ins.length);
        return ins;
    }

    public static void sout(int[] ins) {
        System.out.println(Arrays.toString(ins));
    }

    public static void sort(Consumer<int[]> c) {
        int[] ins = get();
        c.accept(ins);
        sout(ins);
    }

    public static void sort(Function<int[], int[]> c) {
        sout(c.apply(get()));
    }
}
