package com.xzc.daily;

/**
 * @Author: ZCXu1
 * @Date: 2022/10/20 9:26
 * @Version: 1.0.0
 * @Description:
 */
public class P779 {
    public String helper(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '0'){
                sb.append("01");
            }else{
                sb.append("10");
            }
        }
        return sb.toString();
    }

    public int kthGrammar(int n, int k) {
        if (n == 1){
            return 0;
        }
        String init = "0";
        for (int i = 1; i < n; i++){
            init = helper(init);
        }
        return (int) init.charAt(k - 1) - '0';
    }

    public static void main(String[] args) {
        System.out.println(new P779().kthGrammar(1,1));
        System.out.println(new P779().kthGrammar(2,1));
        System.out.println(new P779().kthGrammar(2,2));
    }
}
