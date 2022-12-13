# 链表

## 单链表就地逆序

```java
import com.xzc.ListNode;

class Solution {
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
        // 遍历当前节点cur指向前驱节点pre
        while (cur.next != null){
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
}
```

## 判断一个单链表是否是回文的

样例： 1->20->3->20->1 是回文的

正常做法是利用栈，顺序压入栈中，然后从栈里一个个弹出比对，弹出的是逆序的。

但是有个技巧是**快慢指针**，快指针一次走两步，慢指针一次走一步，快指针走完的时候， 慢指针就在中点位置，这样可以压栈压一半，节省一半的空间。

```java

import com.xzc.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head.next == null) return true;
        if (head.next.next == null && head.val == head.next.val) return true;
        if (head.next.next == null) return false;
        Deque<Integer> stack = new ArrayDeque<>();
        ListNode slow = head, fast = head;
        while (fast !=  null) {
            slow = slow.next;
            if (fast.next != null)
                fast = fast.next.next;
            else break;
            
        }
        while (slow != null) {
            stack.addLast(slow.val);
            slow = slow.next;
        }
        while (!stack.isEmpty()){
            if (stack.pollLirst()!= head.val){
                return false;
            }else{
                head = head.next;
            }
        }
        return true;
    }
}

```

这样还不是最完美的。完美的方法是时间复杂度O(N)，空间复杂度O(1)。

如果就地判断 比如1->2->3->2->1，则在慢指针在3，快指针在最后一个1时，
就地反转为1->2->3<-2<-1，左边从head遍历，右边从tail遍历，判断是否一致，
注意区分奇偶情况。就地判断很考验coding能力。

更常用的快慢指针写法是
```java
ListNode slow = head;
ListNode fast = head;
while(fast.next != null && fast.next.next != null){
    slow = slow.next;
    fast = fast.next.next;
}


```
```java

import com.xzc.ListNode;

class Solution {
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
        // 如果偶数个 slow就在中间两个的第二个 pre在第一个
        
        if (isEven) {
            ListNode rHead = reverseList(slow);
            ListNode tmp = rHead; // 留给后续再复原链表用
            pre.next = null;
            while (head != null) {
                if (head.val != rHead.val) return false;
                else {
                    head = head.next;
                    rHead = rHead.next;
                }
            }
            // 复原原链表
            pre.next = reverseList(tmp);
        } else {
            // 如果奇数个 slow就在中点的后一个 pre在中点
            ListNode rHead = reverseList(pre);
            ListNode tmp = rHead;
            while (head != null) {
                if (head.val != rHead.val) return false;
                else {
                    head = head.next;
                    rHead = rHead.next;
                }
            }
            // 复原原链表
            tmp = reverseList(tmp);
        }
        return true;
    }

}


```

## 单链表的荷兰国旗问题

把链表划分为<区域，=区域和>区域。最直观的思路是转化为数组，然后应用数组的划分，再
转化回去。笔试可以这样，但是面试显然不行，其实链表是一样的，而且链表插入更方便。
定义六个变量，分别是<，=，>区域的头指针和尾指针，初始都是null，遍历一遍往对应区域插入即可。
最后<区域的尾连=区域的头，=区域的尾连>区域的头。但是要考虑清楚边界，比如如果没<区域，没=区域等情况。
必须讨论清楚。

```java
import com.xzc.ListNode;

class Solution {
    public ListNode listPartition(ListNode head, int pivot) {
        ListNode sH = null; // small head
        ListNode sT = null; // small tail
        ListNode eH = null; // equal head
        ListNode eT = null; // equal tail
        ListNode bH = null; // big head
        ListNode bT = null; // big tail
        Node next = null;
        while (head != null){
            next = head.next;
            head.next = null;
            if (head.val < pivot){
                if (sH == null){
                    sH = head;
                    sT = head;
                }else{
                    sT.next = head;
                    st = head;
                }
            }else if (head.val == pivot){
                if (eH == null){
                    eH = head;
                    eT = head;
                }else{
                    eT.next = head;
                    et = head;
                }
            }else{
                if (bH == null){
                    bH = head;
                    bT = head;
                }else{
                    bT.next = head;
                    bt = head;
                }
            }
            head = next;
        }
        // small -> equal
        if (sT!=null){
            // 有小于区域
            sT.next = eH;
            // 谁去连大于区域的头 谁就变eT
            eT = eT == null ? sT : eT;
        }
        // 上面的if 确定了谁要连大于区域的头
        if (eT != null){
            // 如果小于区域和大于区域至少存在一个
            eT.next = bH;
        }
        return sH != null ? sH : (eH != null ? eH : bH);
    }

}

```

## 复制带随机指针的链表

LeetCode138

> 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
> 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
> 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
> 返回复制链表的头节点。
> 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
> val：一个表示 Node.val 的整数。 random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。 你的代码 只 接受原链表的头节点 head 作为传入参数。
> 来源：力扣（LeetCode）
> 链接：https://leetcode.cn/problems/copy-list-with-random-pointer
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

难的是要求时间复杂度O(N)，额外空间复杂度O(1)。
如果用额外空间就比较简单，就是哈希表，key是原节点，value是新分配的节点。

第一次遍历，遍历一遍链表，对每一个节点生成一个新节点，值一样，然后这两个节点分别存入map的k和v

第二次遍历，对每一个老节点，找出next和random指向谁，通过哈希表再新节点中可以知道指向哪两个新的节点。

`map.get(node1).next = map.get(node1.next)`

`map.get(node1).random = map.get(node1.random)`

```java
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        Node h = head;
        Map<Node,Node> map = new HashMap<>();
        while (h != null){
            map.put(h,new Node(h.val));
            h = h.next;
        }
        h = head;
        while(h != null){
            map.get(h).next = map.get(h.next);
            map.get(h).random = map.get(h.random);
            h = h.next;
        }
        return map.get(head);
    }
}
```

如果不用额外的哈希表，算法是，每遍历一个老链表的节点，生成一个对应的新节点，并串在后面
比如对链表 1->2->3->null，先不管random指针，遍历完第一遍，生成1->1'->2->2'->3->3'->null。
接下来我们处理random指针假设最初1的random指向3，那么1'的random应该指向3'，
也就是3的next，这时候如果我们遍历到1，那么我们给1'的
random赋值为1.random.next，以此类推，这样这次遍历就把新链表的random指针处理完了。
最后一次遍历处理新链表的next指针即可。

核心思路就是两个节点配对相邻出现，方便操作。

```java
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node h = head;
        // 第一遍遍历 生成新链表节点
        while(h!=null){
            Node node = new Node(h.val);
            node.next = h.next;
            h.next = node;
            h = node.next;
        }
        // 第二遍遍历 处理新链表random指针
        h = head;
        while (h != null){
            if (h.random != null){
                h.next.random = h.random.next;
            }else{
                h.next.random = null;
            }
            
            h = h.next.next;
        }
        // 第三遍遍历 处理新链表的next指针
        h = head;
        Node res = h.next;
        while (h != null){
            // h是老链表节点 h.next也就是h2是新链表对应节点
            Node h2 = h.next;
            h.next = h2.next;
            // 判断是不是到尾部了
            if (h.next != null){
                h2.next = h.next.next;
            }else{
                h2.next = null;
            }
            
            h = h.next;
        }
        return res;
    }
}
```

## 两个链表相交的一系列问题
