package com.xzc.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZCXu1
 * @Date: 2022/10/11 19:14
 * @Version: 1.0.0
 * @Description:
 */
public class P1790 {
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)){
            return true;
        }
        int n = s1.length();
        int count = 0;
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < n; i++){
            if (s1.charAt(i) != s2.charAt(i)){
                count++;
                l.add(i);
            }
        }
         if (count != 2){
             return false;
         }
         return s1.charAt(l.get(0))==s2.charAt(l.get(1))&&s1.charAt(l.get(1))==s2.charAt(l.get(0));
    }

    public static void main(String[] args) {
        System.out.println(new P1790().areAlmostEqual("bank","kanb"));
        System.out.println(new P1790().areAlmostEqual("attack","defend"));
        System.out.println(new P1790().areAlmostEqual("abcd","dcba"));
        System.out.println(new P1790().areAlmostEqual("kelb","kelb"));
        System.out.println(new P1790().areAlmostEqual("caa","aaz"));
    }
}
