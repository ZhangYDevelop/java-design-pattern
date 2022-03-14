package com.zy.java.design.patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR zhangy
 * 2022/3/14  10:05 下午
 * 定义：在对象之间定义了一对多的依赖，这样一来，当一个对象改变状态，依赖它的对象会收到通知并自动更新。
 */
public class ObserverPatterns {
    private static  List<Observer> list = new ArrayList<Observer>();

    public static void main(String[] args) {
        new SubjectImpl().addObserver(new ObserverImpl("zhangsan", 12));
        new SubjectImpl().addObserver(new ObserverImpl("zhangsan2", 22));
    }

    static class SubjectImpl implements Subject {

        public Boolean addObserver(Observer observer) {
            list.add(observer);
            noitfyAll();
            return true;
        }

        public Boolean updateObserver(Observer observer) {
            list.add(observer);
            noitfyAll();
            return true;
        }

        private void noitfyAll() {
            for (Observer observer : list) {
                observer.update(observer);
            }
        }
    }
    static class ObserverImpl implements Observer {
        @Override
        public String toString() {
            return "ObserverImpl{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
        }

        private String name;
        private Integer age;

        public ObserverImpl(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public boolean update(Observer observer) {
            System.out.println("Observer 收到消息" + observer.toString());
            return false;
        }
    }
}
