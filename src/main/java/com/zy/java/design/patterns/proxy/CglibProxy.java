package com.zy.java.design.patterns.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @AUTHOR zhangy
 * 2020-10-11  13:54
 */
public class CglibProxy implements MethodInterceptor {

    public Object getCglibProxyInstance(Class<?> clazz) {
        Enhancer enhancer = new Enhancer();
        //要把哪个设置为即将生成的新类父类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object retObj = methodProxy.invokeSuper(o,objects);
        after();
        return retObj;
    }

    /**
     * 可以模拟spring的事务开启
     */
    private void after() {
        System.out.println("开启事务。。。");
    }

    /**
     * 可以模拟spring的事务提交
     */
    private void before() {
        System.out.println("调校事务。。。。");
    }

    public static void main(String[] args) {
        Hanlder handler =  (Hanlder)new CglibProxy().getCglibProxyInstance(Hanlder.class);
        handler.delete("dfdfdfd");
    }



}
