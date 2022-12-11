package com.xzc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: ZCXu1
 * @Date: 2022/8/6 8:16
 * @Version: 1.0.0
 * @Description:
 */
public class P205 {
    class Solution {
        public boolean isIsomorphic(String s, String t) {
            if(s.length() != t.length()) return false;
            int n = s.length();
            Map<Character,Character> map = new HashMap<>();
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (map.containsKey(s.charAt(i))){
                    if (t.charAt(i) != map.get(s.charAt(i))){
                        return false;
                    }
                }else{
                    if (set.contains(t.charAt(i))){
                        return false;
                    }
                    map.put(s.charAt(i),t.charAt(i));
                    set.add(t.charAt(i));
                }
            }
            return true;
        }
    }
}
