package com.xzc.learn;

import java.util.Arrays;

/**
 * @Author: ZCXu1
 * @Date: 2022/12/11 19:42
 * @Version: 1.0.0
 * @Description: 荷兰国旗问题，给定一个数组和一个值
 * 把这个数组从左到右划分为小于等于和大于这个值的三个区域
 */

public class NetherlandFlag {

    private static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void nf(int[] a,int k){
        // 三指针 分别是左边小于区域A的右边界（包含） 初始是-1
        // 大于区域B的左边界（包含） 初始是n
        // 遍历数组的指针位置 i
        // 算法逻辑是 若a[i] < k，则A边界的右边第一个元素和a[i]交换 A边界右扩，i++
        // 若a[i] = k，则i++
        // 若a[i] > k，则B边界左边第一个元素和a[i]交换 B边界左扩，i保持不动，i不动的原因是数组后边是我们没遍历过的元素 要继续审查
        if (a == null || a.length < 2){
            return;
        }
        int n = a.length;
        int p1  = -1, p2 = n;
        for (int i = 0; i < p2;) {
            if (a[i] < k){
                swap(a,i++,++p1);
            }else if (a[i] == k){
                i++;
            }else{
                swap(a,i,--p2);
            }
        }

    }

    public static void main(String[] args) {
        int[] a = new int[]{3,5,6,3,4,5,2,6,9,0};
        nf(a,5);
        System.out.println(Arrays.toString(a));
    }
}
