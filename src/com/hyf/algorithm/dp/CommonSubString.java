package com.hyf.algorithm.dp;

/**
 * 动态规划-公共子串
 *
 * @author baB_hyf
 * @date 2021/10/10
 */
public class CommonSubString {

    public static void main(String[] args) {
        print(commonSubstring("fish", "fosh"));
    }

    public static int[][] commonSubstring(String s1, String s2) {
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        int[][] ins = new int[cs1.length][cs2.length];

        for (int i = 0; i < cs1.length; i++) {
            char c1 = cs1[i];
            for (int j = 0; j < cs2.length; j++) {
                char c2 = cs2[j];

                if (c1 == c2) {
                    ins[i][j] = (i == 0 || j == 0) ? 1 : ins[i - 1][j - 1] + 1;
                }
                else {
                    // 公共子串
                    // ins[i][j] = 0;

                    // 公共子序列
                    if (j == 0) {
                        if (i == 0) {
                            ins[i][j] = 0;
                        }
                        else {
                            ins[i][j] = ins[i - 1][j]; // 上边
                        }
                    }
                    else {
                        ins[i][j] = ins[i][j - 1]; // 左边
                    }
                }
            }
        }

        return ins;
    }

    public static void print(int[][] ins) {
        int max = 0;
        for (int[] in : ins) {
            for (int i : in) {
                max = i > max ? i : max;
                if (String.valueOf(i).length() == 1) {
                    System.out.print(" ");
                }
                System.out.print(i);
                System.out.print(", ");
            }
            System.out.println();
        }
        System.out.println("max: " + max);
    }
}
