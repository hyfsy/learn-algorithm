package com.hyf.bli;

import com.hyf.algorithm.dp.DPUtils;

/**
 * 最小操作次数给出两个单词word1和word2,计算出将word1转换为word2的最少操作次数。
 * 你总共三种操作方法:.插入一个字符.替换一个字符.删除一个字符
 * <p>
 * 输入描述:一共两行,分别代表word1和word2
 * 输出描述:直接打印次数即可
 *
 * @author baB_hyf
 * @date 2022/03/27
 */
public class SmallOperate {

    public static void main(String[] args) {
        String w1 = "karma";
        String w2 = "mart";

        int[][] ins = new int[w1.length() + 1][w2.length() + 1];
        // 长度为0的字符串变为长度为i的字符串
        for (int i = 0; i <= w1.length(); i++) {
            ins[i][0] = i;
        }
        // 长度为i的字符串变为长度为0的字符串
        for (int i = 0; i <= w2.length(); i++) {
            ins[0][i] = i;
        }


        // 确定状态
        // - 最后一步：wx + op == w2
        // - 得到子问题：确定为得到wx
        // 转移方程：ins[m][n] = min(ins[m - 1][n], ins[m][n - 1], ins[m - 1][n - 1]) + 1
        // 初始条件和边界情况：m == 0 || n == 0  ->  0|1
        // 执行顺序：w1...w2...

        for (int i = 1; i <= w1.length(); i++) {
            for (int j = 1; j <= w2.length(); j++) {

                if (w1.charAt(i - 1) == w2.charAt(j - 1)) {
                    ins[i][j] = ins[i - 1][j - 1];
                }
                else {
                    ins[i][j] = Math.min(ins[i - 1][j], Math.min(ins[i][j - 1], ins[i - 1][j - 1])) + 1;
                }
            }
        }

        System.out.println(ins[w1.length()][w2.length()]);
        DPUtils.print(ins);
    }
}
