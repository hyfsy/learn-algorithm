package com.hyf.algorithm.dp;

/**
 * @author baB_hyf
 * @date 2021/10/30
 */
public class DPUtils {

    public static void print(int[][] ins, int l) {
        StringBuilder sb = new StringBuilder();

        for (int[] in : ins) {
            for (int i : in) {
                String child = String.valueOf(i);
                if (child.length() < l) {
                    fillSpace(sb, l - child.length());
                }
                sb.append(i).append(", ");
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
