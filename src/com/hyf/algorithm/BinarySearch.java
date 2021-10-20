package com.hyf.algorithm;

/**
 * 二分查找
 * <p>
 * O(logn)
 *
 * @author baB_hyf
 * @date 2021/10/10
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] ins = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int search = 11;
        int index = binarySearch(ins, search);
        System.out.println(ins[index]);
    }

    public static int binarySearch(int[] ins, int search) {
        int low = 0;
        int high = ins.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int guess = ins[mid];
            if (guess < search) {
                low = mid + 1;
            }
            else if (guess > search) {
                high = mid - 1;
            }
            else {
                return mid;
            }
        }

        return Integer.MAX_VALUE;
    }
}
