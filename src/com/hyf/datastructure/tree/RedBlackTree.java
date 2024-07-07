package com.hyf.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 红黑树
 * <p>
 * 1. 任何一个节点都有颜色，黑色或者红色。
 * 2. 根节点是黑色的。
 * 3. 父子节点之间不能出现两个连续的红节点。
 * 4. 任何一个节点向下遍历到其子孙的叶子节点，所经过的黑节点个数必须相等。
 * 5. 空节点被认为是黑色的。
 * <p>
 * 编码时一般不看这些条件，因为有约定好的调整步骤
 *
 * see https://tech.meituan.com/2016/12/02/redblack-tree.html
 * see https://blog.csdn.net/qq_34173549/article/details/79636764
 *
 * @author baB_hyf
 * @date 2021/10/23
 */
public class RedBlackTree {

    /**
     * 颜色区分
     */
    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    /**
     * 根节点
     */
    private Node root;

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        int[] ins = new int[]{0, 1, 2, 0, 4, 3, 5, 6, 7, 8, 9, 10, 11};

        for (int v : ins) {
            tree.add(v);
        }

        System.out.println(tree.find(3));

        System.out.println(tree.remove(11));
        System.out.println(tree.remove(10));
        System.out.println(tree.remove(9));
        System.out.println(tree.remove(8));
        System.out.println(tree.remove(99));
        System.out.println(tree.remove(3));

        System.out.println(tree.list());
    }

    public Node find(int i) {
        Node cur = root;

        while (cur != null) {
            if (i < cur.value) {
                cur = cur.left;
            }
            else if (i > cur.value) {
                cur = cur.right;
            }
            else {
                return cur;
            }
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

        list(nodes, root.left);
        nodes.add(root);
        list(nodes, root.right);
    }

    public boolean add(int v) {

        Node n = new Node(v);
        Node parent = getParent(n);

        // 初始化根节点
        if (parent == null) {
            n.color(BLACK); // 根节点黑色
            root = n;
        }
        else {

            // 重复添加
            if (v == parent.value) {
                return false;
            }

            // 关联关系

            n.parent = parent;

            if (v < parent.value) {
                parent.left = n;
            }
            else {
                parent.right = n;
            }

            // 平衡修复
            fixAdd(n);
        }

        return true;
    }

    /**
     * 添加节点后，修复树的平衡
     *
     * @param cur 当前添加过的节点
     */
    private void fixAdd(Node cur) {

        Node parent = cur.parent;

        // 修复平衡的条件：父节点为红色，如果父节点为黑色，则可以直接插入，无需处理平衡
        while (parent != null && parent.red) {
            Node uncle = getUncle(cur);

            // 叔叔节点为红色，则只需要处理下颜色即可
            if (uncle != null && uncle.red) {

                // 当前层次：红
                // 父辈层次：黑
                // 爷辈层次：红

                parent.color(BLACK); // 父
                uncle.color(BLACK); // 叔
                if (parent.parent != null) {
                    parent.parent.color(RED); // 爷
                }
                // 继续遍历，处理平衡
                cur = parent;
                parent = parent.parent;
            }
            // 旋转的条件：父节点为红色，叔叔节点不为红色
            else {
                Node ancestor = parent.parent;
                boolean parentIsLeft = ancestor == null || ancestor.left == parent;
                boolean childIsLeft = parent.left == cur;

                boolean doubleRotation = parentIsLeft != childIsLeft;

                // 先旋转后处理颜色

                // 四种旋转情况:
                // 1. 父左子左 -> 右旋
                // 2. 父左子右 -> 左右旋
                // 3. 父右子右 -> 左旋
                // 4. 父右子左 -> 右左旋

                if (parentIsLeft) {
                    if (doubleRotation) {
                        rotateLeft(parent);
                    }

                    rotateRight(ancestor);
                }
                else {
                    if (doubleRotation) {
                        rotateRight(parent);
                    }

                    rotateLeft(ancestor);
                }

                // 双旋情况：子变根，需变黑
                // 单旋情况：父变根，需变黑
                // 根节点下节点必红

                if (doubleRotation) {
                    cur.color(BLACK);
                    parent = null; // end loop
                }
                else {
                    parent.color(BLACK);
                }

                // 因为到这里的条件满足父节点为红，子节点也为红，所以只需要明确设置爷爷的颜色
                if (ancestor != null) {
                    ancestor.color(RED);
                }
            }
        }

        // 根节点保持黑色
        root.color(BLACK);
    }

    /**
     * 获取父节点，如果值相等，则返回当前节点
     *
     * @param n 寻找父节点的节点
     * @return 当前节点的父节点，或当前节点（值相等），没有父节点则返回 null
     */
    private Node getParent(Node n) {
        Node parent = root;
        Node cur = parent;

        while (cur != null) {

            // 值已存在，则返回当前节点
            if (n.value == cur.value) {
                return cur;
            }

            // 向下查找
            parent = cur;

            if (n.value < cur.value) {
                cur = cur.left;
            }
            else {
                cur = cur.right;
            }
        }

        return parent;
    }

    /**
     * 获取叔叔节点
     *
     * @param n 查找的节点
     * @return 叔叔节点，没有则返回null
     */
    private Node getUncle(Node n) {
        Node parent = n.parent;
        if (parent == null) {
            return null;
        }

        // 获取爷爷节点
        Node ancestor = parent.parent;
        if (ancestor == null) {
            return null;
        }

        // 获取叔叔节点
        if (parent == ancestor.left) {
            return ancestor.right;
        }
        else {
            return ancestor.left;
        }
    }

    /**
     * 树节点旋转
     *
     * @param child 被旋转掉的节点
     */
    private void rotateLeft(Node child) {
        if (child == null) {
            child = root;
        }

        Node newChildRoot = child.right;
        child.right = newChildRoot.left;
        newChildRoot.left = child;

        newChildRoot.parent = child.parent;
        child.parent = newChildRoot;

        if (newChildRoot.parent == null) {
            root = newChildRoot;
        }
        else {
            Node ancestor = newChildRoot.parent;
            if (newChildRoot.value < ancestor.value) {
                ancestor.left = newChildRoot;
            }
            else {
                ancestor.right = newChildRoot;
            }
        }
    }

    private void rotateRight(Node child) {

        // 爷爷没有则默认根节点
        if (child == null) {
            child = root;
        }

        // 旋转
        Node newChildRoot = child.left;
        child.left = newChildRoot.right;
        newChildRoot.right = child;

        // 修复父子关系
        newChildRoot.parent = child.parent;
        child.parent = newChildRoot;

        // 修复爷父关系
        if (newChildRoot.parent == null) {
            root = newChildRoot;
        }
        else {
            Node ancestor = newChildRoot.parent;
            if (newChildRoot.value < ancestor.value) {
                ancestor.left = newChildRoot;
            }
            else {
                ancestor.right = newChildRoot;
            }
        }
    }

    public boolean remove(int i) {

        Node cur = find(i);
        if (cur == null) {
            return false;
        }

        // 变更移除的节点，变更节点的左节点肯定没有值
        if (cur.left != null && cur.right != null) {
            // 获取要删除元素的后继节点
            Node minimum = cur.right;
            while (minimum.left != null) {
                minimum = minimum.left;
            }
            cur.value = minimum.value; // 覆盖值
            cur = minimum; // 变更为删除后继节点
        }

        // 单子节点情况
        Node replace = cur.left != null ? cur.left : cur.right;

        // 父节点处理
        if (replace != null) {
            replace.parent = cur.parent;
        }

        // 根节点处理
        if (cur.parent == null) {
            root = replace;
        }
        // 子节点处理
        else {
            if (cur.parent.left == cur) {
                cur.parent.left = replace;
            }
            else {
                cur.parent.right = replace;
            }
        }

        // 平衡修复
        fixRemove(cur);

        // fix需要，在之后解除引用
        cur.parent = cur.left = cur.right = null;

        return true;
    }

    /**
     * 删除节点后，修复树的平衡
     *
     * @param cur 当前删除掉的节点
     */
    private void fixRemove(Node cur) {

        // 只有不为根节点的黑色节点需要修复平衡
        while (cur != root && !cur.red) {

            Node sibling = getSibling(cur);

            // 构建临时节点，节省下面的null判断
            if (sibling == null) {
                sibling = new Node(0);
                sibling.red = false;
            }

            // 四种调整情况:
            // 1. 兄弟节点为红色                -> 父节点往删除方旋转 + 父/兄颜色调整
            // 2. 兄弟节点为黑色，兄弟子节点都为黑 -> 兄弟变红（会调整成1的情况，但是是平衡的）
            // 3. 兄弟节点为黑色，兄弟左子节点为红 -> 兄弟节点往兄弟方旋转 + 兄/兄左子颜色调整（会调整成4的情况，但是是平衡的）
            // 4. 兄弟节点为黑色，兄弟右子节点为红 -> 父节点往删除方旋转

            boolean isLeft = cur.parent.left == cur;
            boolean isSiblingLeft = !isLeft;

            // 1. 兄弟节点为红色
            if (sibling.red) {

                cur.parent.color(RED);
                sibling.color(BLACK);

                if (isLeft) {
                    rotateLeft(cur.parent);
                }
                else {
                    rotateRight(cur.parent);
                }
            }
            // 2. 兄弟节点为黑色，兄弟子节点都为黑
            else if (sibling.left != null && !sibling.left.red
                    && sibling.right != null && !sibling.right.red) {
                sibling.color(RED);
            }
            // 3. 兄弟节点为黑色，兄弟左子节点为红
            else if (sibling.left != null && sibling.left.red) {

                sibling.color(RED);
                sibling.left.color(BLACK);

                if (isSiblingLeft) {
                    rotateLeft(sibling);
                }
                else {
                    rotateRight(sibling);
                }
            }
            // 4. 兄弟节点为黑色，兄弟右子节点为红
            else if (sibling.right != null && sibling.right.red) {
                if (isLeft) {
                    rotateLeft(cur.parent);
                }
                else {
                    rotateRight(cur.parent);
                }
            }

            // 向上调整
            cur = cur.parent;
        }

        // 根节点保持黑色
        if (root != null) {
            root.color(BLACK);
        }
    }

    /**
     * 获取兄弟节点
     *
     * @param n 当前查找节点
     * @return 返回兄弟节点，或返回null
     */
    private Node getSibling(Node n) {
        if (n == null) {
            return null;
        }

        if (n.parent == null) {
            return null;
        }

        if (n.parent.left == n) {
            return n.parent.right;
        }
        else {
            return n.parent.left;
        }
    }

    private static class Node {

        // 需要父节点关系来支持
        private Node parent;

        private Node left;
        private Node right;

        private int value;

        // 置换的时候，变成叶子节点的颜色不需要变更，即Black的叶子节点不需要手动变成Red
        // 默认插入的结点为红色。因为红黑树中黑节点至少是红节点的两倍，
        // 因此插入节点的父节点为黑色的概率较大，而此时并不需要作任何调整，因此效率较高。
        private boolean red = true;

        public Node(int value) {
            this.value = value;
        }

        public void color(boolean color) {
            red = color;
        }

        @Override
        public String toString() {
            // return "Node: {v[" + value  + "], red[" + red + "]}";
            return String.valueOf(value);
        }
    }
}
