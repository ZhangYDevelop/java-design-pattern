package com.zy.java.design.patterns.template;

/**
 * @AUTHOR zhangy
 * 2020-10-11  14:22
 * 模版方法模式（Template Method Pattern）是指定义一个算法的骨 架，并允许子类为一个或者多个步骤提供实现。
 */
public abstract class TemplateMethodPattern {


    /**
     * 创建订单
     * @param info
     */
    protected final void  createOrder(Object info) {

        // 创建订单
        saveOrderInfo(info);
        
        // 支付
        pay(info);

        // 产生物流信息
        saveWuliuInfo(info);

        // 发送短信给用户
        sendMessage(info);

        // .....

    }

    private final  void sendMessage(Object info) {
        System.out.println("发送短信");
    }

    protected final void saveWuliuInfo(Object info) {
        System.out.println("产生物流信息");
    }

    /**
     * 交给不同的子类实现 可能有不同的支付方式：支付宝 微信 。。。
     * @param info
     */
    protected abstract void pay(Object info);

    protected final void saveOrderInfo(Object info) {
        System.out.println("产生一条订单：insert into order_info ....");
    }
}
