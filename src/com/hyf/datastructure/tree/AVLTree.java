package com.hyf.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

/**
 * 平衡二叉树
 * <p>
 * 前提是颗二叉排序树
 * 左子树的深度-右子树的深度=平衡因子
 * 平衡因子一般为-1/0/1，如果绝对值大于1就代表数不平衡了，需要进行旋转保持平衡
 * 距离插入结点最近的，且平衡因子的绝对值大于1的结点为根的子树，称为最小不平衡子树
 *
 * @author baB_hyf
 * @date 2021/10/16
 */
public class AVLTree {

    /**
     * 二叉树遍历类型
     */
    private static final int IN_ORDER   = 123;
    private static final int PRE_ORDER  = 213;
    private static final int POST_ORDER = 132;

    /**
     * 根节点
     */
    private Node root;

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        int[] ins = new int[]{0, 1, 2, 4, 3, 5, 6, 7, 8, 9, 10, 11};

        for (int v : ins) {
            tree.add(v);
        }

        System.out.println(tree.remove(10));
        System.out.println(tree.remove(11));
        System.out.println(tree.remove(9));
        System.out.println(tree.remove(8));

        System.out.println(tree.remove(999));

        System.out.println(tree.find(3));

        System.out.println(tree.list());
        System.out.println(tree.listPre());
        System.out.println(tree.listPost());
    }

    public Node find(int v) {
        return find(root, v);
    }

    private Node find(Node cur, int v) {
        if (cur == null) {
            return null;
        }

        if (v < cur.value) {
            return find(cur.left, v);
        }
        else if (v > cur.value) {
            return find(cur.right, v);
        }
        else {
            return cur;
        }
    }

    public List<Node> list() {
        return list(root, IN_ORDER);
    }

    public List<Node> listPre() {
        return list(root, PRE_ORDER);
    }

    public List<Node> listPost() {
        return list(root, POST_ORDER);
    }

    private List<Node> list(Node cur, int type) {
        if (cur == null) {
            return new ArrayList<>();
        }

        List<Node> nodeList = new ArrayList<>(); // 可优化

        if (type == IN_ORDER) {
            nodeList.addAll(list(cur.left, type));
            nodeList.add(cur);
            nodeList.addAll(list(cur.right, type));
        }
        else if (type == PRE_ORDER) {
            nodeList.add(cur);
            nodeList.addAll(list(cur.left, type));
            nodeList.addAll(list(cur.right, type));
        }
        else if (type == POST_ORDER) {
            nodeList.addAll(list(cur.left, type));
            nodeList.addAll(list(cur.right, type));
            nodeList.add(cur);
        }

        return nodeList;
    }

    /**
     * 树节点旋转
     *
     * @param childRoot 要旋转的子树根节点
     * @return 旋转后的新子树根节点
     */
    private Node leftRotation(Node childRoot) {

        // 设置要上升的子树根节点
        Node newChild = childRoot.right;
        // 自己的孩子节点给下降的节点
        childRoot.right = newChild.left;
        // 设置子节点为下降的节点
        newChild.left = childRoot;

        // 旋转后重新设置高度
        childRoot.bf = max(bf(childRoot.left), bf(childRoot.right)) + 1;
        newChild.bf = max(bf(newChild.right), childRoot.bf) + 1; // 原本的孩子+新孩子的高度

        return newChild;
    }

    private Node rightLeftRotation(Node childRoot) {
        // 节点旋转后，子树根节点会改变，此处重新设置
        childRoot.right = rightRotation(childRoot.right /* 固定的情况，其他情况不会出现，不用考虑 */);
        return leftRotation(childRoot);
    }

    private Node rightRotation(Node childRoot) {

        Node newChildRoot = childRoot.left;
        childRoot.left = newChildRoot.right;
        newChildRoot.right = childRoot;

        childRoot.bf = max(bf(childRoot.left), bf(childRoot.right)) + 1;
        newChildRoot.bf = max(bf(newChildRoot.left), childRoot.bf) + 1;

        return newChildRoot;
    }

    private Node leftRightRotation(Node childRoot) {
        childRoot.left = leftRotation(childRoot.left);
        return rightRotation(childRoot);
    }

    /**
     * 获取树的平衡因子
     *
     * @param n 节点
     * @return 节点对应的平衡因子，null则返回 0
     */
    private int bf(Node n) {
        if (n == null) {
            return 0;
        }
        return n.bf;
    }

    public boolean add(int v) {
        boolean has = find(v) == null;
        if (has) {
            root = add(root, v);
        }
        return has;
    }

    private Node add(Node subTree, int v) {

        // 插入的节点初始化
        if (subTree == null) {
            subTree = new Node(v);
        }
        // 查找操作
        else {
            // 小于，向左查
            if (v < subTree.value) {
                // 向左查询+添加，初始化或处理过后的节点重新赋值给左节点
                subTree.left = add(subTree.left, v);

                // 调整子树的平衡
                // 插入在左侧，所以left-right
                if (bf(subTree.left) - bf(subTree.right) == 2) {

                    // 值在最左侧，表示左侧的节点多，直接右旋
                    if (v < subTree.left.value) {
                        subTree = rightRotation(subTree);
                    }
                    // 中间的节点多，先将多数节点左旋到左侧，再右旋
                    else {
                        subTree = leftRightRotation(subTree);
                    }
                }
            }
            // 大于向右查
            else if (v > subTree.value) {
                subTree.right = add(subTree.right, v);

                if (bf(subTree.right) - bf(subTree.left) == 2) {

                    if (v > subTree.right.value) {
                        subTree = leftRotation(subTree);
                    }
                    else {
                        subTree = rightLeftRotation(subTree);
                    }
                }
            }
            // 值相同的情况已经在外面判断，此处就不需要了
            // else { return null; }
        }

        // 非旋转情况的平衡调整
        // 旋转后也会进行调整，是否可以优化（将旋转那边的相关代码删除）
        subTree.bf = max(bf(subTree.left), bf(subTree.right)) + 1;

        return subTree;
    }

    public boolean remove(int v) {
        boolean has = find(v) != null;
        if (has) {
            root = remove(root, v);
        }
        return has;
    }

    private Node remove(Node cur, int v) {

        if (cur == null) {
            return null;
        }

        // 值在左侧，继续查找
        if (v < cur.value) {
            // 左侧递归 查询+删除
            cur.left = remove(cur.left, v);

            // 删除后，左边少，右边多，需要左旋

            if (bf(cur.right) - bf(cur.left) == 2) { // 多数-少数

                // 校验删除后的另一半子树的平衡情况
                Node check = cur.right;
                // 右边节点多，则直接左旋
                if (bf(check.right) >= bf(check.left)) {
                    cur = leftRotation(cur);
                }
                // 否则表示中间多，需要先右旋再左旋
                else {
                    cur = rightLeftRotation(cur);
                }
            }
        }
        else if (v > cur.value) {
            cur.right = remove(cur.right, v);

            if (bf(cur.left) - bf(cur.right) == 2) {

                Node check = cur.left;
                if (bf(check.left) >= bf(check.right)) {
                    cur = rightRotation(cur);
                }
                else {
                    cur = leftRightRotation(cur);
                }
            }
        }
        // 实际删除操作
        else {

            // 不是两个子节点都有的情况，则直接赋值cur表示删除
            // cur表示要被删除的节点，所以此处只需要将它的子节点重新赋值为根节点即可
            if (cur.left == null || cur.right == null) {
                cur = cur.left != null ? cur.left : cur.right;
            }
            // 两个节点都有的情况
            else {

                // 将左树最大或右树最小的节点值覆盖当前要被删除的节点，然后继续向下遍历查找，删除最小或最大值的节点
                // 有种狸猫换太子的感觉

                // 选择大的平衡因子删除 保证删除后 树还是平衡的
                if (bf(cur.left) > bf(cur.right)) {
                    int maximum = maximum(cur.left); // 获取前驱/后继节点
                    cur.value = maximum; // 覆盖要被删除的值
                    cur.left = remove(cur.left, maximum); // 移除前驱/后继节点
                }
                else {
                    int minimum = minimum(cur.right);
                    cur.value = minimum;
                    cur.right = remove(cur.right, minimum);
                }
            }
        }

        return cur;
    }

    private int maximum(Node cur) {
        if (cur == null) {
            return Integer.MAX_VALUE;
        }

        Node c;
        int v = 0;
        while ((c = cur.right) != null) {
            v = c.value;
        }

        return v;
    }

    private int minimum(Node cur) {
        if (cur == null) {
            return Integer.MIN_VALUE;
        }

        Node c;
        int v = 0;
        while ((c = cur.left) != null) {
            v = c.value;
        }

        return v;
    }

    private static class Node {

        private Node left;
        private Node right;

        private int value;

        private int bf = 0;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            // return "Node{v[" + value + "], bf[" + bf + "]}";
            return String.valueOf(value);
        }
    }
}
