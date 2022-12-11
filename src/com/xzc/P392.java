package com.xzc;

/**
 * @Author: ZCXu1
 * @Date: 2022/8/6 8:31
 * @Version: 1.0.0
 * @Description:
 */
public class P392 {
    class Solution {
        public boolean isSubsequence(String s, String t) {
            int m = s.length(), n = t.length();
            if (m > n) return false;
            if (m == 0) return true;
            // double pointer
            int i = 0, j = 0;
            while (i < m && j < n){
                if (s.charAt(i)==t.charAt(j)){
                    i++;
                    j++;
                }
                if (i < m && j < n){
                    while (j < n && s.charAt(i)!=t.charAt(j)){
                        j++;
                    }
                }
            }
            return i == m;
        }
    }
}
