package com.xzc.learn;

/**
 * @Author: ZCXu1
 * @Date: 2022/12/11 18:55
 * @Version: 1.0.0
 * @Description:
 */
// 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
public class Sword51 {
    // 本质就是求数组中每个数的右边有几个数比它小
    // 逆归并排序过程中求逆序对
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        return helper(nums, 0, nums.length - 1);
    }

    // 把nums的l~r部分做归并排序从大到小并统计逆序对个数
    private int helper(int[] nums, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return helper(nums, l, mid) +
                helper(nums, mid + 1, r) +
                merge(nums, l, mid, r);
    }

    private int merge(int[] nums, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        int res = 0;
        while (p1 <= mid && p2 <= r) {
            if (nums[p1]>nums[p2]){
                temp[i++]=nums[p1++];
                res += r-p2+1;
            }else {
                temp[i++]=nums[p2++];
            }
        }
        while (p1<=mid){
            temp[i++] = nums[p1++];
        }
        while (p2<=r){
            temp[i++] = nums[p2++];
        }
        for (i = 0; i < temp.length;i++){
            nums[l+i] = temp[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Sword51().reversePairs(new int[]{7,5,6,4}));
    }
}
