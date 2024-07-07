package com.hyf.algorithm.dp;

/**
 * https://www.bilibili.com/video/BV1xb411e7ww
 * <p>
 * 你有三种硬币,分别面值2元,5元和7元,每种硬币都有足够多
 * 买一本书需要27元
 * 如何用最少的硬币组合正好付清,不需要对方找钱
 * <p>
 * 动态规划：三种题型：最值型（min/max）、计数型（+）、可行性型（and/or）
 * <p>
 * 动态规划组成部分:
 * 1.确定状态，状态在动态规划中的作用相当于定海神针
 * - 最后一步（最优策略中使用的最后一枚硬币ak）
 * - 化成子问题（最少的硬币拼出更小的面值27-ak），f[X] = f[27 - ak]
 * 2.转移方程：f[X] = min{f[X-2]+1, f[X-5]+1, f[X-7]+1}
 * 3.初始条件和边界情况：f[0] = 0，如果不能拼出Y，f[Y]=正无穷，细心，考虑周全
 * 4.计算顺序：f[O], f[1], f[2]，利用好之前的计算结果
 *
 * @author baB_hyf
 * @date 2022/03/27
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] a = new int[]{2, 5, 7};
        int m = 27;

        int[] res = new int[m + 1];

        for (int i = 1; i < res.length; i++) {
            // 初始设置，下面判断min使用
            res[i] = Integer.MAX_VALUE;
            // 内循环设置最小的 27 - ak，硬币的数量
            for (int j = 0; j < a.length; j++) {
                if (i >= a[j] && res[i - a[j]] != Integer.MAX_VALUE) {
                    // a[j] 当前硬币值
                    // i - a[j] 之前的硬币值
                    // res[i - a[j]] 之前的硬币数
                    res[i] = Math.min(res[i - a[j]] + 1 /* 当前硬币值和之前那个最小 */, res[i]);
                }
            }
        }

        System.out.println(res[m]);
        DPUtils.print(res);
    }
}
