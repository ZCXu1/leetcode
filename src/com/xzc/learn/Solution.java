package com.xzc.learn;

import com.xzc.ListNode;

/**
 * @Author: ZCXu1
 * @Date: 2022/12/12 22:44
 * @Version: 1.0.0
 * @Description:
 */
public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode pre; // 前驱节点
        ListNode cur; // 当前节点
        ListNode next; // 后继节点
        // 把链表首节点变成尾节点
        cur = head;
        next = cur.next;
        cur.next = null;
        pre = cur;
        cur = next;
        // 先别连这个pre 在下面的循环连
        // 遍历当前节点cur指向前驱节点pre
        while (cur.next != null) {
            // 一定要把正序的下一个节点储存下来到next 不然会丢失
            next = cur.next;
            // 然后把箭头逆序指向前面
            cur.next = pre;
            // 这样就反转了 继续往右移动
            pre = cur;
            cur = next;
        }
        // 最后一个节点指向倒数第二个节点
        cur.next = pre;
        return cur;
    }

    public boolean isPalindrome(ListNode head) {
        if (head.next == null) return true;
        if (head.next.next == null && head.val == head.next.val) return true;
        if (head.next.next == null) return false;
        ListNode pre = new ListNode(0,head);
        ListNode slow = head, fast = head;
        boolean isEven = false;
        while (fast != null) {
            slow = slow.next;
            pre = pre.next;
            if (fast.next != null) {
                if (fast.next.next == null) {
                    // 说明此时fast是倒数第二个节点 走一步就行了
                    fast = fast.next;
                    isEven = true;
                    break;
                } else {
                    fast = fast.next.next;
                }
            } else {
                // 奇数个
                isEven = false;
                // 此时是倒数第一个节点 直接结束
                break;
            }
        }
        // 此时fast走完且指向最后一个节点
        // 如果偶数个 slow就在中间两个的第二个
        // 如果奇数个 slow就在中点的后一个
        if (isEven) {
            ListNode rHead = reverseList(slow);
            pre.next = null;
            while (head != null) {
                if (head.val != rHead.val) return false;
                else {
                    head = head.next;
                    rHead = rHead.next;
                }
            }
        } else {
            ListNode rHead = reverseList(pre);
            while (head != null) {
                if (head.val != rHead.val) return false;
                else {
                    head = head.next;
                    rHead = rHead.next;
                }
            }
        }
        return true;
    }

}
