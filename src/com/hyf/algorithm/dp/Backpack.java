package com.hyf.algorithm.dp;

/**
 * 动态规划-背包
 *
 * @author baB_hyf
 * @date 2021/10/10
 */
public class Backpack {

    //  0,  0, 10, 10, 10, 10,
    //  3,  3, 10, 13, 13, 13,
    //  3,  9, 12, 13, 19, 22,
    //  3,  9, 12, 14, 19, 22,
    //  6,  9, 15, 18, 20, 25,

    public static void main(String[] args) {
        print(backpack());
    }

    public static int[][] backpack() {
        Good[] goods = getGoods();
        int[] weights = getWeights();

        int[][] ins = new int[goods.length][weights.length];

        for (int i = 0; i < goods.length; i++) {
            Good good = goods[i];
            for (int j = 0; j < weights.length; j++) {
                Integer nowWeight = weights[j];

                Integer goodWeight = good.weight;
                Integer goodCost = good.cost;

                // 第一行特殊处理
                if (i == 0) {
                    if (goodWeight > nowWeight) { // 物品放不下
                        goodCost = 0;
                    }

                    ins[i][j] = goodCost; // 第一行只有一个物品，所以直接设置
                }
                // 下面的每行都参照按照上一行数据
                else {
                    int i1 = ins[i - 1][j]; // 参照前一个物品当前weight的价值
                    int i2; // 减去当前物品的weight剩余的weight对应的cost

                    if (nowWeight < goodWeight) {
                        i2 = 0;
                    }
                    else if (nowWeight - goodWeight == 0) {
                        i2 = goodCost;
                    }
                    else {
                        i2 = goodCost + ins[i - 1][(nowWeight - goodWeight) - 1 /* 索引j作为权重 */];
                    }

                    ins[i][j] = i1 > i2 ? i1 : i2;
                }
            }
        }

        return ins;
    }

    public static void print(int[][] ins) {
        for (int[] in : ins) { // good
            for (int i : in) { // weight
                if (String.valueOf(i).length() == 1) {
                    System.out.print(" ");
                }
                System.out.print(i);
                System.out.print(", ");
            }
            System.out.println();
        }
        System.out.println("max cost: " + ins[ins.length - 1][ins[0].length - 1]);
    }

    public static int[] getWeights() {
        return new int[]{1, 2, 3, 4, 5, 6};
    }

    public static Good[] getGoods() {
        return new Good[]{
                new Good("水", 3, 10),
                new Good("水", 3, 10),
                new Good("书", 1, 3),
                new Good("食物", 2, 9),
                new Good("夹克", 2, 5),
                new Good("相机", 1, 6),
        };
    }

    public static class Good {
        public String  name;
        public Integer weight;
        public Integer cost;

        public Good(String name, Integer weight, Integer cost) {
            this.name = name;
            this.weight = weight;
            this.cost = cost;
        }
    }
}
