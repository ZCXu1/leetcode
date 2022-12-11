package com.xzc;

/**
 * @Author: ZCXu1
 * @Date: 2022/10/18 16:11
 * @Version: 1.0.0
 * @Description:
 */
public class Test {
    private int n;

    public static void main(String[] args) {
        String a = "123";
        String b = "123";
        String c = new String("123");
        String d = new String("123");
        System.out.println(a == b);
        System.out.println(b == c);
        System.out.println(a == c);
        System.out.println(c == d);
    }

}
