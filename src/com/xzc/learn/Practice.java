package com.xzc.learn;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author: ZCXu1
 * @Date: 2022/12/11 16:00
 * @Version: 1.0.0
 * @Description:
 */
public class Practice {

    // 在一个数组中，只有一个数出现奇数次，其余的数都出现偶数次，找出这个数
    private static int getOnlyOdd(int[] a) {
        // 利用异或的特性，所有的数异或起来就是答案
        int res = 0; // 0 ^ x = x
        for (int j : a) {
            res ^= j;
        }
        return res;
    }


    private static int getIThDigitNum(int n, int i) {
        return (n >> i) & 1;
    }

    // 在一个数组中，有两个不相同的数出现奇数次，其余的数都出现偶数次，找出这两个数
    private static int[] get2Odds(int[] a) {
        // 假设这两个数是x和y，利用异或的特性，所有的数异或起来就是x^y
        // 因为x!=y，那么x^y的结果的二进制表示中至少有一位是1，假设这是第k位
        // 则（x的第k位为1且y的第k位为0）或（x的第k位为0且y的第k位为1），不妨设为第一种情况
        // 此时再设一个变量把数组中第i位为1的全部异或起来结果就是x，再拿x^y和x异或就是y
        int res = 0; // 0 ^ x = x
        for (int j : a) {
            // res = x ^ y
            res ^= j;
        }
        // 找出第几位为1
        int k = 0;
        for (int i = 0; i < 32; i++) {
            if (getIThDigitNum(res, i) == 1) {
                k = i;
                break;
            }
        }
        int res2 = 0;
        for (int j : a) {
            if (getIThDigitNum(j, k) == 1) {
                res2 ^= j;
            }
        }
        return new int[]{res ^ res2, res2};
    }
    // 不是数组有序才能二分
    // 返回无序且任意相邻两数都不相等的数组中的任意一个局部最小值，时间复杂度O(logn)
    // 局部最小值定义：
    // 若a[0] < a[1] 则 a[0]是一个局部最小值
    // 若a[n-2] > a[n-1] 则 a[n-1]是一个局部最小值
    // 若a[i-1]>a[i]&&a[i]<a[i+1] 则a[i]是一个局部最小值

    private static boolean isMin(int[] a, int i) {
        // 判断数组i位置上是否是局部最小值
        if (i == 0) return a[0] < a[1];
        if (i == a.length - 1) return a[a.length - 1] < a[a.length - 2];
        return a[i - 1] > a[i] && a[i] < a[i + 1];
    }

    private static int getPartMin(int[] a) {
        int n = a.length;
        if (n < 2) {
            return n == 1 ? a[0] : 0;
        } else if (n == 2) {
            return Math.min(a[0], a[1]);
        }
        if (a[0] < a[1]) return a[0];
        if (a[n - 2] > a[n - 1]) return a[n - 1];
        // 接下来 a[0]>a[1]且a[n-2]<a[n-1]
        // 把整个数组堪称函数 最左边下降 最右边上升 则 0~n-1范围必有导数为0的点 也就是局部最小值 利用这个思路可以一直二分下去
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isMin(a,mid)){
                return a[mid];
            }else if (a[mid - 1] < a[mid]){
                // 则 0 ~ mid 上有极小值
                right = mid;
            }else if (a[mid + 1] < a[mid]){
                left = mid;
            }
        }
        return a[left];
    }

    public static void almostInOrder(int[] a, int k){
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        // k << a.length
        int index = 0;
        while (index <= k){
            // 把前k+1个数放进堆
            heap.add(a[index++]);
        }
        // 此时index是堆的右边界(开区间)
        int i = 0;
        while (index < a.length){
            // 直到堆右边界和数组右边界重合停下
            heap.add(a[index++]);
            a[i++] = heap.poll();
        }
        while (!heap.isEmpty()){
            a[i++] = heap.poll();
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 1, 2, 2, 3, 4, 3, 5, 6, 6, 5};
        System.out.println(Arrays.toString(a));
        almostInOrder(a,3);
        System.out.println(Arrays.toString(a));

    }

}
