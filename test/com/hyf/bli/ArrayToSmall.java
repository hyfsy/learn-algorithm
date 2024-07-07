package com.hyf.bli;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 输入一个正整数数组,将它们连接起来排成一个数,输出能排出的所有数字中最小的一个。
 * <p>
 * 输入描述:一行输入,数组中的数字用逗号隔开。例如:32,231则表示数组[32, 231]
 * 输出描述:直接输出最小数字即可,如示例题目中,输出为:23132
 *
 * @author baB_hyf
 * @date 2022/03/27
 */
public class ArrayToSmall {

    public static void main(String[] args) {
        // int[] ins = new int[]{1, 22, 4, 225, 44, 25, 6, 7, 77, 8, 8, 8, 8};
        int[] ins = new int[]{32, 231};

        String[] ss = new String[ins.length];
        for (int i = 0; i < ins.length; i++) {
            ss[i] = String.valueOf(ins[i]);
        }

        Arrays.sort(ss, new MyComparator());

        StringBuilder sb = new StringBuilder();

        for (String s : ss) {
            // if (sb.length() != 0) sb.append(' ');
            sb.append(s);
        }

        // while (sb.charAt(0) == '0') sb.deleteCharAt(0);

        System.out.println(sb.toString());
    }


    public static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            String co1 = o1.concat(o2);
            String co2 = o2.concat(o1);
            return co1.compareTo(co2);
        }
    }
}
