package com.xzc;

/**
 * @Author: ZCXu1
 * @Date: 2022/10/12 19:39
 * @Version: 1.0.0
 * @Description:
 */
public class ListNode {
    public int val;
    public ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
