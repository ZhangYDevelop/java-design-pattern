package com.zy.java.design.patterns.delegate;

import java.util.HashMap;
import java.util.Map;

/**
 * 委派模式
 * 简单列子：老板叫领导干活
 */
public class DelegatePattern {
    /**
     * 员工接口
     */
    public interface Employee {
        void   doSomething(String commond);
    }
    /**
     * 打印员
     */
    public static class EmployeeA implements Employee{
        public void doSomething(String commond) {
            System.out.println("打印员接到通知：" + commond);
        }
    }
    /**
     * 前台
     */
    public static  class EmployeeB implements Employee{
        public void doSomething(String commond) {
            System.out.println("前台接到命令：" + commond);
        }
    }
    /**
     * 领导，不干活，指挥别人干
     */
    public static class Leader implements Employee {

        private Map<String, Employee> employees  = new HashMap<>();

        public Leader() {
            employees.put("前台", new EmployeeB());
            employees.put("打印员", new EmployeeA());
        }
        public void doSomething(String commond) {
            employees.get(commond).doSomething(commond);
        }
    }
    /**
     * 老板下达命令
     */
    public static class Boss {
        public void commond(String commond) {
            new Leader().doSomething(commond);
        }
    }
    public static void main(String[] args) {
        //客户请求（Boss）、委派者（Leader）、被被委派者（Target）
        //委派者要持有被委派者的引用
        //代理模式注重的是过程， 委派模式注重的是结果
        //策略模式注重是可扩展（外部扩展），委派模式注重内部的灵活和复用
        //委派的核心：就是分发、调度、派遣
        //委派模式：就是静态代理和策略模式一种特殊的组合
        new Boss().commond("前台");
    }
}
