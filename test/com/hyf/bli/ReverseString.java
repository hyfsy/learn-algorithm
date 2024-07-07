package com.hyf.bli;

/**
 * 原地翻转句子中单词的顺序,但单词内字符的顺序不变。
 * 要求:空间复杂度O(1),时间复杂度O(n)。
 * <p>
 * 输入描述:英文句子中单词以一个空格符隔开。为简单起见,标点符号和普通字母一样处理。
 * 输出描述:翻转之后的英文句子,单词内字符的顺序不变，以一个空格隔开。
 * 输入例子1:I am a student.
 * 输出例子1:student. a am I
 * <p>
 * 557
 * kv-offer 58
 *
 * @author baB_hyf
 * @date 2022/03/27
 */
public class ReverseString {

    public static void main(String[] args) {
        System.out.println(reverseWords("I am a student."));
    }


    public static String reverseWords(String s) {
        char[] chars = s.toCharArray();

        int l = 0, h = l, len = chars.length - 1;

        reverse(chars, l, len);

        while (h <= len) {

            while (h < len && chars[h] == ' ') h++;
            if (h >= len) break;
            l = h; // 第一个字符的idx

            while (h < len && chars[h] != ' ') h++;
            if (h <= len) reverse(chars, l, h - 1);
        }

        return new String(chars);
    }

    public static void reverse(char[] chars, int l, int h) {
        while (l < h) {
            char tmp = chars[l];
            chars[l] = chars[h];
            chars[h] = tmp;
            l++;
            h--;
        }
    }
}
