package com.hyf.bli;

import com.hyf.algorithm.dp.DPUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 移动0
 *
 * @author baB_hyf
 * @date 2022/03/28
 */
public class MoveZero {

    public static void main(String[] args) {
        int count = 10;
        int[] ins = generate(count);
        DPUtils.print(ins);

        int l = 0;
        int h = 0;

        while (h < ins.length) {

            // 0标记前进
            while (h < ins.length && ins[h] == 0) {
                h++;
            }

            // 不相等说明存在0
            if (l != h) {
                ins[l] = ins[h];
            }

            l++;
            h++;
        }

        while (l < h) {
            ins[l++] = 0;
        }

        DPUtils.print(ins);
    }

    private static int[] generate(int count) {
        Random random = new Random();
        int r = random.nextInt(count);
        List<Integer> ins = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            ins.add(i);
        }

        for (int i = 0; i < r; i++) {
            int addIdx = random.nextInt(count);
            ins.add(addIdx, 0);
        }

        return ins.stream().mapToInt(Integer::valueOf).toArray();
    }
}
