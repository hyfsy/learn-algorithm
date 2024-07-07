package com.hyf.leetcode;

import java.util.*;

/**
 * @author baB_hyf
 * @date 2021/10/28
 */
public class A {

    public static void main(String[] args) {
        System.out.println(1 / 2);
        System.out.println(new A().guessNumber(10));
    }

    public int guessNumber(int n) {
        int l = 1, h = n;

        while (l <= h) {
            int mid = l + (h - l) / 2;
            int t = guess(mid);
            if (t == -1) {
                l = mid + 1;
            }
            else if (t == 1) {
                h = mid - 1;
            }
            else {
                return mid;
            }
        }

        return -1;
    }

    private int guess(int mid) {
        return Integer.compare(mid, 6);
    }
    public boolean canConstruct(String ransomNote, String magazine) {

        Map<Character, Integer> tables = new HashMap<>();

        int l = magazine.length();
        for (int i = 0; i < l; i++) {
            tables.compute(magazine.charAt(i), (k, v) -> v == null ? 1 : ++v);
        }

        l = ransomNote.length();
        for (int i = 0; i < l; i++) {
            tables.compute(ransomNote.charAt(i), (k, v) -> v == null ? -1 : --v);
        }

        Collection<Integer> results = tables.values();
        for (Integer count : results) {
            if (count < 0) {
                return false;
            }
        }

        return true;
    }

}