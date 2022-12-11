package com.xzc.daily;

/**
 * @Author: ZCXu1
 * @Date: 2022/10/7 15:19
 * @Version: 1.0.0
 * @Description:
 */
public class P1800 {
    public int maxAscendingSum(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = nums[i] > nums[i - 1] ? dp[i - 1] + nums[i] : nums[i];
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new P1800().maxAscendingSum(new int[]{10,20,30,5,10,50}));
        System.out.println(new P1800().maxAscendingSum(new int[]{10,20,30,40,50}));
        System.out.println(new P1800().maxAscendingSum(new int[]{12,17,15,13,10,11,12}));
        System.out.println(new P1800().maxAscendingSum(new int[]{100,10,1}));
    }
}
