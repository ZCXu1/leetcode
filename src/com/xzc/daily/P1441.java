package com.xzc.daily;

import java.util.*;

/**
 * @Author: ZCXu1
 * @Date: 2022/10/15 11:38
 * @Version: 1.0.0
 * @Description:
 */
public class P1441 {
    public List<String> buildArray(int[] target, int n) {
        int i = 0;
        List<String> res = new ArrayList<>();
        for (int j = 1; j <= n; j++){
            if (target[i] == j){
                res.add("Push");
                i++;
            }else{
                res.add("Push");
                res.add("Pop");
            }
            if (i == target.length){
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new P1441().buildArray(new int[]{1,3},3));
        System.out.println(new P1441().buildArray(new int[]{1,2,3},3));
        System.out.println(new P1441().buildArray(new int[]{1,2},4));
    }
}
