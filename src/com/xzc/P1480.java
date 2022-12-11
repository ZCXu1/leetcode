package com.xzc;

/**
 * @Author: ZCXu1
 * @Date: 2022/8/5 13:57
 * @Version: 1.0.0
 * @Description:
 */
public class P1480 {

    class Solution {
        public int[] runningSum(int[] nums) {
            int[] sum = new int[nums.length];
            sum[0] = nums[0];
            for (int i = 1; i < nums.length; i++){
                sum[i] = sum[i-1]+nums[i];
            }
            return sum;
        }
    }

}
