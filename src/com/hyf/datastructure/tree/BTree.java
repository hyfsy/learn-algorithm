package com.hyf.datastructure.tree;

import java.util.*;

/**
 * B树 - 多路查找树 - 自平衡树
 * <p>
 * 对于一个t度的B树，t>2
 * 除根节点以外的每个节点必须至少有（t -1）个键。
 * 包含根节点的每个节点必须至多具有（2 t - 1）个键。
 * 每个内部节点（根节点除外）必须至少有t个子节点。每个内部节点（包括根节点）必须具有至多2t的子结点。
 * 节点中的键必须按升序存储
 * 键左侧的子节点的所有键必须小于该键，键右侧的子节点的所有键必须大于该键。
 * <p>
 * see https://blog.csdn.net/herry816/article/details/89525077
 *
 * @author baB_hyf
 * @date 2021/10/16
 */
public class BTree {

    /**
     * 树最小度
     */
    private static final int MIN_DEGREE = 2;

    /**
     * 每个节点的键下限
     */
    private final int minKeyNum;

    /**
     * 每个节点的键上限
     */
    private final int maxKeyNum;

    private Node root;

    public BTree() {
        this(MIN_DEGREE + 1);
    }

    public BTree(int degree) {
        if (degree < MIN_DEGREE) {
            throw new IllegalArgumentException("degree must greater than " + MIN_DEGREE);
        }

        this.minKeyNum = degree - 1;
        this.maxKeyNum = degree * 2 - 1;
    }

    public static void main(String[] args) {
        BTree tree = new BTree();

        int[] ins = new int[]{0, 1, 2, 4, 3, 5, 6, 7, 8, 9, 10, 11};

        for (int v : ins) {
            tree.add(v);
        }
        System.out.println(tree.remove(2));

        System.out.println(tree.remove(10));
        System.out.println(tree.remove(11));
        System.out.println(tree.remove(9));
        System.out.println(tree.remove(8));

        System.out.println(tree.find(3));

        System.out.println(tree.list());
    }

    public Node find(int v) {
        return find(root, v);
    }

    private Node find(Node cur, int v) {

        while (cur != null) {

            // 二分查找给定值
            int i = binarySearch(cur, v);
            if (i < cur.keyNum && cur.keys[i] == v) {
                return cur;
            }
            // 子节点循环查找
            cur = cur.nodes[i];
        }

        return null;
    }

    public List<Node> list() {
        List<Node> nodes = new ArrayList<>();
        list(nodes, root);
        return nodes;
    }

    private void list(List<Node> nodes, Node root) {
        if (root == null) {
            return;
        }

        nodes.add(root);

        for (int i = 0; i < root.nodes.length; i++) {
            list(nodes, root.nodes[i]);
        }
    }

    public boolean add(int v) {

        if (find(root, v) != null) {
            return false;
        }

        if (root == null) {
            root = new Node();
        }

        // 分裂后，根节点可能会改变
        root = add(root, v);
        return true;
    }

    private Node add(Node cur, int v) {

        // 校验溢出情况
        if (cur.keyNum == maxKeyNum) {
            cur = split(cur);
        }

        // 插入的位置 or 子节点的位置
        int i = binarySearch(cur, v);

        // 该判断没必要，因为上层add方法已校验数据必定不存在
        if (i < cur.keyNum && cur.keys[i] == v) {
            return cur;
        }

        // 叶子节点直接插入值
        if (cur.leaf) {
            for (int k = cur.keyNum - 1; k >= i; k--) {
                cur.keys[k + 1] = cur.keys[k];
                // 不需要处理子节点
            }
            cur.keys[i] = v;
            cur.keyNum++;
        }
        // 不是叶子节点，则递归让叶子节点添加值
        else {
            Node n = add(cur.nodes[i], v);

            // 分裂过需要和父节点合并
            if (n.keyNum == 1) {
                cur.nodes[cur.keyNum + 1] = cur.nodes[cur.keyNum];
                for (int k = cur.keyNum - 1; k >= i; k--) {
                    cur.keys[k + 1] = cur.keys[k];
                    cur.nodes[k + 1] = cur.nodes[k];
                }
                cur.keys[i] = n.keys[0];
                cur.nodes[i] = n.nodes[0];
                cur.nodes[i + 1] = n.nodes[1];
                cur.keyNum++;
            }
        }

        return cur;
    }

    public boolean remove(int v) {
        if (find(v) == null) {
            return false;
        }

        // 删掉根节点的情况，需要改变
        root = remove(root, v);
        return true;
    }

    private Node remove(Node cur, int v) {

        // 删除的位置 or 子节点的位置
        int i = binarySearch(cur, v);

        // 当前节点存在要删除的数据
        boolean hit = i < cur.keyNum && cur.keys[i] == v;

        // 叶子节点直接删除，当前递归方法外部自动判断平衡
        if (cur.leaf) {
            if (hit) { // 该判断没必要，因为上层remove方法已校验数据必定存在，所以到了叶子节点肯定是有该数据的
                // 删除给定值
                for (int k = i + 1; k < cur.keyNum; k++) {
                    cur.keys[k - 1] = cur.keys[k];
                }
                cur.keys[cur.keyNum - 1] = null;
                cur.keyNum--;
            }
        }
        // 非叶子节点
        else {
            // 数据在当前节点，替换删除
            if (hit) {
                // 优化点：校验前后两个子树哪个多，删除多的那颗
                Node left = cur.nodes[i];
                Node right = getRightSibling(cur, i);
                if (right != null && right.keyNum >= left.keyNum) {
                    int minimum = minimum(right);
                    cur.keys[i] = minimum;
                    v = minimum;
                    i++;
                }
                else {
                    int maximum = maximum(left);
                    cur.keys[i] = maximum;
                    v = maximum;
                }
            }

            // 数据不在当前节点，递归子节点
            Node n = remove(cur.nodes[i], v);

            // 删除后的键数量太少，需要旋转或合并
            if (n.keyNum < minKeyNum) {
                Node left = getLeftSibling(cur, i);
                Node right = getRightSibling(cur, i);

                if (left != null) {
                    if (left.keyNum > minKeyNum) {
                        rightRotation(cur, i - 1);
                    }
                    else {
                        leftMerge(cur, i);
                    }
                }
                else if (right != null) {
                    if (right.keyNum > minKeyNum) {
                        leftRotation(cur, i + 1);
                    }
                    else {
                        rightMerge(cur, i);
                    }
                }
            }
        }

        // 根节点特殊处理
        // 子节点合并操作或根节点为叶子节点的情况，会导致根节点没有数据
        if (cur == root && cur.keyNum == 0) {
            cur = cur.nodes[0];
        }

        return cur;
    }

    private Node getLeftSibling(Node n, int i) {
        if (i == 0) {
            return null;
        }
        return n.nodes[i - 1];
    }

    private Node getRightSibling(Node n, int i) {
        if (i == n.keyNum) {
            return null;
        }
        return n.nodes[i + 1];
    }

    /**
     * 二分查找给定节点对应的值
     * <p>
     * 在此处是一个非常经典的应用！！！
     *
     * @param n 查找的节点
     * @param v 查找值
     * @return 如果值在该节点内存在，则返回节点内值的索引，否则返回应插入值的节点索引
     */
    private int binarySearch(Node n, int v) {
        int l = 0;
        int h = n.keyNum - 1;

        while (l <= h) {
            int m = l + (h - l) / 2;
            int guess = n.keys[m];

            if (v < guess) {
                h = m - 1;
            }
            else if (v > guess) {
                l = m + 1;
            }
            else {
                return m;
            }
        }

        return l;
    }

    /**
     * 节点分裂
     *
     * @param cur 当前要分裂的节点
     * @return 分裂后的根节点
     */
    private Node split(Node cur) {

        int m = cur.keyNum / 2;

        // 左侧节点分裂
        Node left = new Node();
        for (int i = 0; i < m; i++) {
            left.keys[i] = cur.keys[i];
            left.nodes[i] = cur.nodes[i];
        }
        left.nodes[m] = cur.nodes[m];
        left.keyNum = m;
        left.leaf = cur.leaf;


        // 右侧节点分裂
        Node right = new Node();
        for (int i = m + 1; i < cur.keyNum; i++) {
            right.keys[i - m - 1] = cur.keys[i];
            right.nodes[i - m - 1] = cur.nodes[i];
        }
        right.nodes[cur.keyNum - m - 1] = cur.nodes[cur.keyNum];
        right.keyNum = cur.keyNum - m - 1;
        right.leaf = cur.leaf;


        // 根节点生成
        Node n = new Node();
        n.keyNum = 1;
        n.keys[0] = cur.keys[m];
        n.nodes[0] = left;
        n.nodes[1] = right;
        n.leaf = false;
        return n;
    }

    /**
     * 节点旋转
     *
     * @param cur 当前根节点
     * @param i   旋转的子节点索引
     */
    private void leftRotation(Node cur, int i) {

        Node left = cur.nodes[i - 1];
        Node right = cur.nodes[i];

        // 左侧添加根上节点
        left.keys[left.keyNum] = cur.keys[i - 1];
        left.nodes[left.keyNum + 1] = right.nodes[0];
        left.keyNum++;

        // 根添加右侧节点
        cur.keys[i - 1] = right.keys[0];

        // 右侧节点删除
        for (int k = 1; k < right.keyNum; k++) {
            right.keys[k - 1] = right.keys[k];
            right.nodes[k - 1] = right.nodes[k];
        }
        right.nodes[right.keyNum - 1] = right.nodes[right.keyNum];
        right.keys[right.keyNum - 1] = null;
        right.nodes[right.keyNum] = null;
        right.keyNum--;
    }

    private void rightRotation(Node cur, int i) {

        Node left = cur.nodes[i];
        Node right = cur.nodes[i + 1];

        // 右侧添加根上节点
        right.nodes[right.keyNum + 1] = right.nodes[right.keyNum];
        for (int k = right.keyNum - 1; k >= 0; k--) {
            right.keys[k + 1] = right.keys[k];
            right.nodes[k + 1] = right.nodes[k];
        }
        right.keys[0] = cur.keys[i];
        right.nodes[0] = left.nodes[cur.keyNum];
        right.keyNum++;

        // 根添加左侧节点
        cur.keys[i] = left.keys[left.keyNum - 1];

        // 左节点删除
        left.keys[left.keyNum - 1] = null;
        left.nodes[left.keyNum] = null;
        left.keyNum--;
    }

    /**
     * 节点合并
     * <p>
     * 另一种代码级别的偷懒方法是控制索引
     *
     * @param cur 当前根节点
     * @param i   要合并的子节点索引
     */
    private void leftMerge(Node cur, int i) {

        Node left = cur.nodes[i - 1];
        Node right = cur.nodes[i];

        // 左侧节点填充根节点和右侧节点
        left.keys[left.keyNum++] = cur.keys[i - 1];
        for (int k = 0; k < right.keyNum; k++) {
            left.nodes[left.keyNum] = right.nodes[k];
            left.keys[left.keyNum++] = right.keys[k];
            right.keys[k] = null;
            right.nodes[k] = null;
        }
        left.nodes[left.keyNum + 1] = right.nodes[right.keyNum + 1];
        right.nodes[right.keyNum + 1] = null;

        // 根节点移动，顺便删了右侧节点的引用
        for (int k = i; k < cur.keyNum; k++) {
            cur.keys[k - 1] = cur.keys[k];
            cur.nodes[k] = cur.nodes[k + 1];
        }
        cur.keys[cur.keyNum - 1] = null;
        cur.nodes[cur.keyNum] = null;
        cur.keyNum--;
    }

    private void rightMerge(Node cur, int i) {

        Node left = cur.nodes[i];
        Node right = cur.nodes[i + 1];

        // 右侧填充根节点和左侧节点
        int fillLen = 1 + left.keyNum;
        for (int k = right.keyNum - 1; k >= 0; k--) {
            right.nodes[k + 1 + fillLen] = right.nodes[k + 1];
            right.keys[k + fillLen] = right.keys[k];
        }
        right.nodes[fillLen] = right.nodes[0];
        right.keys[fillLen - 1] = cur.keys[i];
        for (int k = 0; k < left.keyNum; k++) {
            right.keys[k] = left.keys[k];
            right.nodes[k] = left.nodes[k];
            left.keys[k] = null;
            left.nodes[k] = null;
        }
        right.nodes[left.keyNum] = left.nodes[left.keyNum];
        left.nodes[left.keyNum] = null;
        right.keyNum += fillLen;

        // 根节点移动，顺便删了左侧节点引用
        for (int k = i + 1; k < cur.keyNum; k++) {
            cur.nodes[k - 1] = cur.nodes[k];
            cur.keys[k - 1] = cur.keys[k];
        }
        cur.nodes[cur.keyNum - 1] = cur.nodes[cur.keyNum];
        cur.nodes[cur.keyNum] = null;
        cur.keys[cur.keyNum - 1] = null;
        cur.keyNum--;
    }

    /**
     * 获取极限值
     *
     * @param n 查找的节点
     * @return 获取给定节点内的最大、最小值
     */
    private int maximum(Node n) {
        int max = 0;
        while (n != null) {
            max = n.keys[n.keyNum - 1];
            n = n.nodes[n.keyNum];
        }
        return max;
    }

    private int minimum(Node n) {
        int min = 0;
        while (n != null) {
            min = n.keys[0];
            n = n.nodes[0];
        }
        return min;
    }

    private class Node {

        private final Integer[] keys  = new Integer[maxKeyNum];
        private final Node[]    nodes = new Node[maxKeyNum + 1];
        private       boolean   leaf  = true;
        private       int       keyNum;

        @Override
        public String toString() {
            // return "Node: {num[" + keyNum + "], keys" + Arrays.toString(keys) + ", leaf[" + leaf + "]}";
            return arrayToString();
        }

        private String arrayToString() {
            StringJoiner sj = new StringJoiner(", ", "[", "]");
            Arrays.stream(keys).filter(Objects::nonNull).map(String::valueOf).forEach(sj::add);
            return sj.toString();
        }
    }
}
