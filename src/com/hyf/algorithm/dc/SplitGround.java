package com.hyf.algorithm.dc;

/**
 * 分治-分割土地-求最大公约数
 *
 * @author baB_hyf
 * @date 2021/10/10
 */
public class SplitGround {

    public static void main(String[] args) {
        int width = 168;
        int height = 64;
        System.out.println(splitGround(width, height));
    }

    public static int splitGround(int big, int small) {
        if (big < small) {
            int temp = big;
            big = small;
            small = temp;
        }

        int temp = small;
        small = big % small;
        big = temp;

        if (big % small == 0) {
            return small;
        }

        return splitGround(big, small);
    }
}
