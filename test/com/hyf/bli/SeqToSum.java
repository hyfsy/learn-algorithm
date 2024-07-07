package com.hyf.bli;

import com.hyf.algorithm.dp.DPUtils;

/**
 * 给定一个正整数N,试求有多少组连续正整数满足所有数字之和为N? (1<= N <= 10^9)
 * <p>
 * 输入例子:5
 * 输出例子:2
 *
 * @author baB_hyf
 * @date 2022/03/27
 */
public class SeqToSum {

    public static void main(String[] args){
        int k = 5;


        // 确定状态
        // - 最后一步：f[m] + x = 5
        // - 化成子问题：判断所有的f[m]
        // 状态转移方程：ins[m] = ins[m - 1] + (k - i == 0 || k - i == i - 1) ? 1 : 0
        // 初始条件及边界情况：前面的默认0
        // 执行顺序：ins[0]/ins[1]....

        int[] ins = new int[k + 1];
        for (int i = 1; i < ins.length; i++) {
            ins[i] = ins[i - 1] + ((k - i == 0 || k - i == i - 1) ? 1 : 0);
        }

        System.out.println(ins[k]);
        DPUtils.print(ins);
    }
}
