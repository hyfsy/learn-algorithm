package com.hyf.algorithm.dp;

import java.util.Arrays;

/**
 * @author baB_hyf
 * @date 2021/10/30
 */
public class DPUtils {

    public static void print(boolean[] ins) {
        System.out.println(Arrays.toString(ins));
    }

    public static void print(int[] ins) {
        System.out.println(Arrays.toString(ins));
    }

    public static void print(int[][] ins) {
        print(ins, 1);
    }

    public static void print(int[][] ins, int l) {
        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < ins[0].length; j++) {
            for (int i = 0; i < ins.length; i++) {
                String child = String.valueOf(ins[i][j]);
                if (child.length() < l) {
                    fillSpace(sb, l - child.length());
                }
                sb.append(child).append(", ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void fillSpace(StringBuilder sb, int l) {
        for (int i = 0; i < l; i++) {
            sb.append(" ");
        }
    }
}
