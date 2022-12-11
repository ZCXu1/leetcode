package com.xzc.staticproxy;

/**
 * @Author: ZCXu1
 * @Date: 2022/10/19 16:22
 * @Version: 1.0.0
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        SmsMessage smsMessage = new SmsMessageImpl();
        SmsProxy smsProxy = new SmsProxy(smsMessage);
        smsProxy.send("Java");
    }
}
