package com.xzc.staticproxy;

/**
 * @Author: ZCXu1
 * @Date: 2022/10/19 16:20
 * @Version: 1.0.0
 * @Description:
 */
public class SmsMessageImpl implements SmsMessage{

    @Override
    public String send(String message) {
        System.out.println("send message: "+message);
        return message;
    }
}
