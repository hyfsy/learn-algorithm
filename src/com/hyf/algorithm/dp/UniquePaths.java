package com.hyf.algorithm.dp;

/**
 * 给定m行n列的网格,有一个机器人从左上角(0,0)出发,每一步可以向下或者向右走一步
 * 问有多少种不同的方式走到右下角
 *
 * @author baB_hyf
 * @date 2022/03/27
 */
public class UniquePaths {

    public static void main(String[] args) {
        int m = 10, n = 5;
        int[][] ins = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 确定状态
                // - 最后一步：[m - 2][n - 1] or [m - 1][m - 2]
                // - 化为子问题：走到[m - 1][n - 1] 有 [m - 2][n - 1] + [m - 1][n - 2]中走法
                // 转移方程：[m - 1][n - 1] = [m - 2][n - 1] + [m - 1][n - 2]
                // 初始条件和边界情况：m > 1 && n > 1
                // 计算顺序:[0][1]/[0][2]...

                if (i == 0 || j == 0) {
                    ins[i][j] = 1;
                }
                else {
                    ins[i][j] = ins[i - 1][j] + ins[i][j - 1];
                }
            }
        }

        System.out.println(ins[m - 1][n - 1]);
        DPUtils.print(ins, 3);
    }
}
