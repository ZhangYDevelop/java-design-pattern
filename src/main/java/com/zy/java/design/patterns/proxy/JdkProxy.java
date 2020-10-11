package com.zy.java.design.patterns.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @AUTHOR zhangy
 * 2020-10-11  13:49
 */
public class JdkProxy implements InvocationHandler {
    /**
     * 被代理类必须实现接口
     */
    Object target;
    public  Object  getInstance(Object object) {
        this.target = object;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object retObj = method.invoke(target, args);
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


    public interface CrudHandler{
        Object addObject(Object o);

        void delete(String id);
    }
    public static class CrudHandlerImpl implements CrudHandler {

        public Object addObject(Object o) {
            System.out.println("向数据库添加一条数据");
            return null;
        }

        public void delete(String id) {
            System.out.println("删除数据库一条数据, id=" + id );
        }
    }

    public static void main(String[] args) {
        CrudHandler crudHandler = (CrudHandler)new JdkProxy().getInstance(new CrudHandlerImpl());
        crudHandler.delete("dfdfdfdfdf");
        byte[] bytes =   ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{CrudHandler.class});
        try {
            FileOutputStream fos = new FileOutputStream("C://zhangyu//$Proxy0.class");
            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
