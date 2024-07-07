package com.hyf.algorithm.dp;

/**
 * 哪个子序列乘积最大
 *
 * @author baB_hyf
 * @date 2022/03/27
 */
public class MaximumProductSubArray {

    public static void main(String[] args) {
        int[] ins = new int[]{1, 3, -1, 3, 5, 6, 3, -10, 3, 8, 9, 99};
        int[] res = new int[ins.length];


        int max = res[0] = ins[0];
        for (int i = 1; i < ins.length; i++) {
            // 确定状态
            // - 最后一步：a[m - 1] * a[n]
            // - 化成子问题：如何确定a[m - 1]
            // 状态转换方程：a[m - 1] = max(a[m - 2] * a[n], a[m - 2])
            // 初始条件及边界情况：a[n] <= 0，则重置a[m]为1
            // 执行顺序：ins[0]/ins[1]...
            if (ins[i] <= 0) {
                res[i] = 1;
            }
            else {
                res[i] = Math.max(res[i - 1] * ins[i], ins[i - 1]);
            }

            max = Math.max(max, res[i]);
        }

        System.out.println(max);
        DPUtils.print(res);
    }
}
