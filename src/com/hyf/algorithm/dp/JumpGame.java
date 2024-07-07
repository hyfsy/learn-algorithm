package com.hyf.algorithm.dp;

/**
 * 有n块石头分别在x轴的0,1, ..., n-1位置
 * 一只青蛙在石头0,想跳到石头n-1
 * 如果青蛙在第i块石头上,它最多可以向右跳距离a
 * 问青蛙能否跳到石头n-1
 *
 * @author baB_hyf
 * @date 2022/03/27
 */
public class JumpGame {

    public static void main(String[] args) {

        // int[] stones = new int[] {2, 3, 1, 1, 4};
        int[] stones = new int[] {3, 2, 1, 0, 4};

        boolean[] res = new boolean[stones.length];

        // 确定状态
        // - 最后一步：i + stones[i] >= end
        // - 化成子问题：确定为能不能跳到 res[j]
        // 状态转移方程：res[j] = res[i] && i + stones[i] >= end
        // 初始条件和边界情况：第一个石头能跳过去，res[0] = true
        // 执行顺序：res[0]/res[1]...


        res[0] = true;
        for (int i = 1; i < res.length; i++) {
            for (int j = 0; j < i; j++) {
                res[i] = res[j] && (j + stones[j]) >= i;
                if (res[i]) break;
            }
        }

        System.out.println(res[stones.length - 1]);
        DPUtils.print(res);
    }
}
