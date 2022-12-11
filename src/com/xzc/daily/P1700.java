package com.xzc.daily;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * @Author: ZCXu1
 * @Date: 2022/10/19 13:07
 * @Version: 1.0.0
 * @Description:
 */
public class P1700 {
    public int countStudents(int[] students, int[] sandwiches) {
        int n = students.length;
        Deque<Integer> queue = new ArrayDeque<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++){
            queue.addLast(students[i]);
            stack.addLast(sandwiches[i]);
        }
        while (!queue.isEmpty()){
            if (!queue.contains(stack.peek())){
                return queue.size();
            }
            if (queue.peek().equals(stack.peek())){
                queue.removeFirst();
                stack.removeFirst();
            }else{
                queue.addLast(queue.peek());
                queue.removeFirst();
            }

        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new P1700().countStudents(new int[]{1,1,0,0},new int[]{0,1,0,1}));
        System.out.println(new P1700().countStudents(new int[]{1,1,1,0,0,1},new int[]{1,0,0,0,1,1}));
    }
}
