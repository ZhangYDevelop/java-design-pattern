package com.zy.java.design.patterns.template;

/**
 * @AUTHOR zhangy
 * 2020-10-11  14:36
 *  模版方法模式不同的子类有不同的实现，pay() 方法相当于一个钩子方法，类似与SpringMvc启动流程中的的 onRefresh()
 */
public class TempalteChild extends TemplateMethodPattern {

    @Override
    protected void pay(Object info) {
        System.out.println("采用支付宝支付");
    }

    public static void main(String[] args) {
        new TempalteChild().createOrder(new Object());
    }
}
