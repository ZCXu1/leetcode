package com.xzc.staticproxy;

/**
 * @Author: ZCXu1
 * @Date: 2022/10/19 16:20
 * @Version: 1.0.0
 * @Description:
 */
public class SmsProxy implements SmsMessage{

    private SmsMessage smsMessage;

    SmsProxy(SmsMessage smsMessage){
        this.smsMessage = smsMessage;
    }

    @Override
    public String send(String message) {
        // 扩展被代理对象的方法
        System.out.println("发送前");
        smsMessage.send(message);
        System.out.println("发送后");
        return message;
    }
}
