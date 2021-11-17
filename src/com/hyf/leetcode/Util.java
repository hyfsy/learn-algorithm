package com.hyf.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author baB_hyf
 * @date 2021/11/11
 */
class Util {

    public static ListNode listNode(int... ins) {
        if (ins == null || ins.length == 0) {
            return new ListNode();
        }

        ListNode head = new ListNode(ins[0]);

        ListNode cur = head;
        for (int i = 1; i < ins.length; i++) {
            cur = cur.next = new ListNode(ins[i]);
        }

        return head;
    }

    public static TreeNode treeNode(int... ins) {
        if (ins == null || ins.length == 0) {
            return new TreeNode();
        }

        TreeNode root = new TreeNode(ins[0]);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        for (int i = 1; i < ins.length; i++) {
            TreeNode cur = queue.peek();
            while (cur != null && cur.left != null && cur.right != null) {
                queue.poll();
                cur = queue.peek();
            }

            if (cur != null) {
                if (cur.left == null) {
                    queue.offer(cur.left = new TreeNode(ins[i]));
                }
                else {
                    queue.offer(cur.right = new TreeNode(ins[i]));
                }
            }
        }

        return root;
    }
}
