package com.hyf.bli;

import com.hyf.algorithm.dp.DPUtils;

/**
 * @author baB_hyf
 * @date 2022/03/27
 */
public class Bk {

    public static void main(String[] args) {
        optimizeSpace();
    }

    public static void optimizeSpace() {
        int m = 6;
        int[] ws = new int[]{3, 3, 1, 2, 2, 1};
        int[] vs = new int[]{10, 10, 3, 9, 5, 6};

        int[] ins = new int[m + 1];
        for (int i = 0; i < vs.length; i++) {
            for (int j = m; j >= ws[i]; j--) {
                int newV = vs[i] + ins[j - ws[i]];
                if (newV > ins[j]) {
                    ins[j] = newV;
                }
            }
        }

        System.out.println(ins[m]);
        DPUtils.print(ins);
    }

    public static void scrollingArray() {
        int m = 6;
        int[] ws = new int[]{3, 3, 1, 2, 2, 1};
        int[] vs = new int[]{10, 10, 3, 9, 5, 6};

        int[][] ins = new int[2][m + 1];
        for (int i = 0; i < vs.length; i++) {
            for (int j = 0; j <= m; j++) {
                int ni = i & 1;
                if (i == 0) {
                    if (ws[i] <= j)
                        ins[ni][j] = vs[i];
                }
                else if (ws[i] > j)
                    ins[ni][j] = ins[ni ^ 1][j];
                else
                    ins[ni][j] = Math.max(ins[ni ^ 1][j], ins[ni ^ 1][j - ws[i]] + vs[i]);
            }
        }

        System.out.println(ins[vs.length & 1 ^ 1][m]);
        DPUtils.print(ins);
    }

    public static void common() {
        int m = 6;
        int[] ws = new int[]{3, 3, 1, 2, 2, 1};
        int[] vs = new int[]{10, 10, 3, 9, 5, 6};


        int[][] ins = new int[vs.length][m + 1];

        for (int i = 0; i < ins.length; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 0) {
                    if (ws[i] <= j) {
                        ins[i][j] = vs[i];
                    }
                }
                else if (ws[i] > j) {
                    ins[i][j] = ins[i - 1][j];
                }
                else {
                    ins[i][j] = Math.max(ins[i - 1][j], ins[i - 1][j - ws[i]] + vs[i]);
                }
            }
        }

        System.out.println(ins[vs.length - 1][m]);
    }
}
