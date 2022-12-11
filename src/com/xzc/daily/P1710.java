package com.xzc.daily;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: ZCXu1
 * @Date: 2022/11/15 16:26
 * @Version: 1.0.0
 * @Description:
 */
public class P1710 {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (o1, o2) -> o2[1] - o1[1]);
        int cnt = 0;
        int res = 0;
        boolean flag = false;
        for (int[] boxType : boxTypes) {
            int a = boxType[0];
            int b = boxType[1];
            if (cnt + a > truckSize) {
                for (int j = a - 1; j >= 0; j--) {
                    if (cnt + j == truckSize) {
                        res += j * b;
                        flag = true;
                    }
                }

            } else {
                cnt += a;
                res += a * b;
            }
            if (flag){
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new P1710().maximumUnits(new int[][]{{5,10},{2,5},{4,7},{3,9}},10));
    }
}
