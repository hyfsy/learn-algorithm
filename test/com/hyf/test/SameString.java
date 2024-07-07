package com.hyf.test;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author baB_hyf
 * @date 2022/03/28
 */
public class SameString {

    public static void main(String[] args) {
        System.out.println(solve("bcbabcabab"));
    }

    public static int solve(String a) {
            int n = a.length(), res = 0;
            for(int i = n / 2; i > 0; --i) {//枚举长度
                res = 0;
                for(int j = 0; j < n - i; ++j) {//枚举起点
                    if(a.charAt(j) == a.charAt(i + j)) {
                        ++res;
                    } else {
                        res = 0;//不满足条件，重置长度，从下一个字符为起点开始分析
                    }
                    if(res == i) return i * 2;
                }
            }
            return 0;
        }

    public static int solve2(String a) {
        // write code here

        Map<String, Integer> map = new TreeMap<>();


        for (int i = 0; i < a.length(); i++) {
            for (int j = i + 1; j <= a.length(); j++) {
                map.compute(a.substring(i, j), (k, v) -> {
                    if (v == null) {
                        return 1;
                    }
                    else {
                        return ++v;
                    }
                });
            }
        }

        int max = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                max = Math.max(max, entry.getKey().length());
            }
        }

        return max * 2;


    }
}
