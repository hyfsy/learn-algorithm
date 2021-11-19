package com.hyf.leetcode;

/**
 * @author baB_hyf
 * @date 2021/11/01
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    @Override
    public String toString() { return String.valueOf(val); }
}
