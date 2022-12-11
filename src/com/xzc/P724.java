package com.xzc;

/**
 * @Author: ZCXu1
 * @Date: 2022/8/5 14:02
 * @Version: 1.0.0
 * @Description:
 */
public class P724 {
    class Solution {
        public int[] runningSum(int[] nums) {
            int[] sum = new int[nums.length];
            sum[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                sum[i] = sum[i - 1] + nums[i];
            }
            return sum;
        }

        public int pivotIndex(int[] nums) {
            int n = nums.length;
            if (n == 1) return 0;
            int[] sum = runningSum(nums);

            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    if (sum[n - 1] - nums[0] == 0) return 0;
                } else if (i == n - 1) {
                    if (sum[n - 1] - nums[n - 1] == 0) return n - 1;
                } else {
                    if (sum[i - 1] == sum[n - 1] - sum[i]) return i;

                }
            }
            return -1;
        }
    }
}
