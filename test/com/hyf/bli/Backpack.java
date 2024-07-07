package com.hyf.bli;

import com.hyf.algorithm.dp.DPUtils;

/**
 * 有为N件物品,它们的重量w分别是w1,w2,...,wn,它们的价值v分别是v1,v2,..,vn,每件物品数量有且仅有一个,
 * 现在给你个承重为M的背包,求背包里装入的物品具有的价值最大总和?
 * <p>
 * 输入描述:物品数量 N = 5件 重量w分别是 2 2 6 5 4 价值v分别是 6 3 5 4 6 背包承重为 M = 10
 * 输出描述:背包内物品最大总和为15
 *
 * @author baB_hyf
 * @date 2022/03/27
 */
public class Backpack {

    //  0,  0,  0,  0,  0,  0,
    //  0,  0,  3,  3,  3,  6,
    //  0,  0,  3,  9,  9,  9,
    // 10, 10, 10, 12, 12, 15,
    // 10, 10, 13, 13, 14, 18,
    // 10, 10, 13, 19, 19, 20,
    // 10, 20, 20, 22, 22, 25,

    public static void main(String[] args) {

        int m = 6;
        Good[] goods = getGoods();

        int[][] res = new int[goods.length][m + 1];

        // 确定状态
        // - 最后一步：ins[m - 1][x].w + g.w <= weight && ins[m - 1][x].v + g.v >= ins[m - 1][n - 1]
        // - 化成子问题：找到 ins[m - 1][x]
        // 转移方程：res[m][n] = max(res[m - 1][n], res[m - 1][w - g.w] + g.v)
        // 初始条件和边界情况：m == 0 || n == 0
        // 执行顺序：g1w1 g1w2... g2w1 g2w2...

        for (int i = 0; i < goods.length; i++) {
            for (int j = 1; j <= m; j++) {
                // 第一个商品的背包容量初始化
                if (i == 0) {
                    if (goods[i].w <= j) { // 背包容量能放得下
                        res[i][j] = goods[i].v;
                    }
                }
                // 当前权重比物品剩下的权重小，则只需要按照前一个商品的来
                else if (j < goods[i].w) {
                    res[i][j] = res[i - 1][j];
                }
                // 比较前一个商品的容量 和 当前商品使用后剩下权重对应的背包最大容量+当前物品的容量比较
                else {
                    res[i][j] = Math.max(res[i - 1][j], res[i - 1][j - goods[i].w] + goods[i].v);
                }
            }
        }

        System.out.println(res[goods.length - 1][m]);
        DPUtils.print(res, 2);
    }

    public static Good[] getGoods() {
        return new Good[]{
                new Good(3, 10),
                new Good(3, 10),
                new Good(1, 3),
                new Good(2, 9),
                new Good(2, 5),
                new Good(1, 6),
        };
    }

    public static class Good {
        public int w;
        public int v;
        public Good(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }
}
