package com.hyf.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉排序树
 * <p>
 * 代码没进过优化
 *
 * @author baB_hyf
 * @date 2021/10/23
 */
public class BinaryTree {

    private Node root;

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        int[] ins = new int[]{0, 1, 2, 4, 3, 5, 6, 7, 8, 9};

        for (int i = 0; i < ins.length; i++) {
            tree.add(ins[i]);
        }

        System.out.println(tree.remove(4));

        System.out.println(tree.get(3));

        List<Node> list = tree.list();
        list.forEach(System.out::println);
    }

    public boolean add(int i) {

        Node n = new Node(i);

        if (root == null) {
            root = n;
        }
        else {
            Node parent = search(root, i);
            if (i > parent.value) {
                parent.right = n;
            }
            else if (i < parent.value) {
                parent.left = n;
            }
            else {
                return false;
            }
        }

        return true;
    }

    private Node search(Node n, int i) {
        Node parent;
        Node child = n;
        while (true) {
            if (i > child.value) {
                parent = child;
                child = child.right;
            }
            else if (i < child.value) {
                parent = child;
                child = child.left;
            }
            else {
                return child;
            }

            if (child == null) {
                return parent;
            }
        }
    }

    public boolean remove(int i) {

        Node parent = null;
        Node child = root;
        while (child != null) {
            if (i > child.value) {
                parent = child;
                child = child.right;
            }
            else if (i < child.value) {
                parent = child;
                child = child.left;
            }
            else {
                if (parent == null) {
                    root = null;
                }
                else {
                    if (i > parent.value) {
                        removeInner(parent, parent.right);
                    }
                    else if (i < parent.value) {
                        removeInner(parent, parent.left);
                    }
                }
                return true;
            }

        }

        return false;
    }

    private void removeInner(Node parent, Node cur) {
        boolean isLeft = parent.left == cur;
        if (cur == null) {
            return;
        }

        if (cur.left == null && cur.right == null) {
            if (isLeft) {
                parent.left = null;
            }
            else {
                parent.right = null;
            }
        }
        else if (cur.left != null && cur.right == null) {
            if (isLeft) {
                parent.left = cur.left;
            }
            else {
                parent.right = cur.left;
            }
        }
        else if (cur.left == null) {
            if (isLeft) {
                parent.left = cur.right;
            }
            else {
                parent.right = cur.right;
            }
        }
        else {
            Node p = null;
            Node left = null;
            Node temp = cur;
            while (temp.left != null) {
                p = temp;
                left = temp.left;
                temp = temp.left;
            }

            if (left.value != cur.left.value) {
                left.left = cur.left;
            }
            if (left.value != cur.right.value) {
                left.right = cur.right;
            }

            p.left = null;
            if (isLeft) {
                parent.left = left;
            }
            else {
                parent.right = left;
            }
        }
    }

    public Node get(int i) {
        Node child = root;

        while (child != null) {
            if (i > child.value) {
                child = child.right;
            }
            else if (i < child.value) {
                child = child.left;
            }
            else {
                return child;
            }
        }

        return null;
    }

    public List<Node> list() {
        return list(root);
    }

    private List<Node> list(Node cur) {
        if (cur == null) {
            return new ArrayList<>();
        }

        List<Node> nodeList = new ArrayList<>(list(cur.left));
        nodeList.add(cur);
        nodeList.addAll(list(cur.right));

        return nodeList;
    }

    private static class Node {

        private final int  value;
        private       Node left;
        private       Node right;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node: value[" + value + "]";
        }
    }
}