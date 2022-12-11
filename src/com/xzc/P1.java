package com.xzc;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZCXu1
 * @Date: 2022/10/4 19:39
 * @Version: 1.0.0
 * @Description:
 */
public class P1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> int2index = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++){
            if (int2index.containsKey(target-nums[i])){
                return new int[]{i,int2index.get(target-nums[i])};
            }else{
                int2index.put(nums[i],i);
            }
        }
        return null;
    }
}
