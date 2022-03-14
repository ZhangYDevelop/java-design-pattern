package com.zy.java.design.patterns.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略模式
 * 简单列子：支付方式的选择
 */
public class StrategyPattern {
    /**
     * 支付抽象
     */
    public interface Payment {
        void pay(int mount);
    }

    /**
     * 阿里支付
     */
    public static class Alipay implements Payment {
        @Override
        public void pay(int mount) {
            System.out.println("阿里支付，支付金额：" + mount);
        }
    }

    /**
     * 微信支付
     */
    public static class Wechatpay implements Payment {
        @Override
        public void pay(int mount) {
            System.out.println("微信支付，支付金额：" + mount);
        }
    }

    public static class PaymentFactory {

        private static Map<String, Payment> payMentMap  = new HashMap<>();

        private  static  Payment defaultPayMent = new Alipay();

        public PaymentFactory() {
            payMentMap.put(PayType.ALI_PAY, new Alipay());
            payMentMap.put(PayType.WECHAT_PAY, new Wechatpay());
        }

        public static Payment getPayMent(String type) {
            Payment payment = payMentMap.get(type);
            return  payment == null ? defaultPayMent : payment;
        }
    }

    public interface PayType {
        public static final String ALI_PAY = "ALI_PAY";
        public static final String WECHAT_PAY = "WECHAT_PAY";
    }

    public static void main(String[] args) {
        Payment payment =  PaymentFactory.getPayMent(PayType.WECHAT_PAY);
        payment.pay(234);
    }
}
