package com.hyf.algorithm.sort;

/**
 * 堆排序，简单选择排序的改进
 * <p>
 * 构建堆：O(n)
 * 调整堆：O(nlogn)
 * 总共：O(nlogn)
 *
 * @author baB_hyf
 * @date 2021/10/17
 */
public class HeapSort {

    public static void main(String[] args) {
        SortUtil.sort(HeapSort::heapSort);
    }

    public static void heapSort(int[] ins) {

        // 调整数组生成大顶堆，只对非叶子节点调整
        for (int i = ins.length / 2 - 1; i >= 0; i--) {
            heapAdjust(ins, i, ins.length);
        }

        // 排序
        for (int i = ins.length - 1; i > 0; i--) {
            SortUtil.swap(ins, 0, i); // 交换根节点和最后的节点
            heapAdjust(ins, 0, i); // 调整堆
        }
    }

    /**
     * 调整堆
     *
     * @param ins 数组
     * @param i   调整的节点
     * @param m   调整的长度
     */
    public static void heapAdjust(int[] ins, int i, int m) {
        int tmp = ins[i];
        int j;

        // 2i + 1 表示当前节点的左子节点的坐标
        for (j = i * 2 + 1; j < m; j = j * 2 + 1) {
            if (j + 1 < m && ins[j] < ins[j + 1]) { // 右子节点存在且大于左子节点，将指针指向右子节点
                j++;
            }

            if (tmp >= ins[j]) {
                break;
            }

            ins[i] = ins[j];
            i = j;
        }

        ins[i] = tmp;
    }
}
