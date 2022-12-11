package com.xzc.daily;

import com.xzc.ListNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: ZCXu1
 * @Date: 2022/10/12 19:38
 * @Version: 1.0.0
 * @Description:
 */
public class P817 {

    private List<Integer> list2ArrayList(ListNode head){
        ListNode node = head;
        List<Integer> list = new ArrayList<>();
        while (node != null){
            list.add(node.val);
            node = node.next;
        }
        return list;
    }

    public int numComponents(ListNode head, int[] nums) {
        List<Integer> list = list2ArrayList(head);
        int n = 0;
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length;i++){
            set.add(nums[i]);
        }
        for (int i = 0; i < list.size(); i++){
            if (set.contains(list.get(i))){
                if (i == 0 || !set.contains(list.get(i-1))){
                    n++;
                }
            }
        }
        return n;
    }


}
