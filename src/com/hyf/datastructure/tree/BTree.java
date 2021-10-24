package com.hyf.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * B树 - 多路查找树
 *
 * @author baB_hyf
 * @date 2021/10/16
 */
public class BTree {

    public static final int ORDER_MIN = 2;
    public static final int ORDER_MAX = 3;


    private static class Node {

        private int num;

        private List<Integer> values  = new ArrayList<>(ORDER_MIN);
        private List<Node>    childes = new ArrayList<>(ORDER_MIN + 1);

    }
}
