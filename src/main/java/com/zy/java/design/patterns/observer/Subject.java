package com.zy.java.design.patterns.observer;

/**
 * @AUTHOR zhangy
 * 2022/3/14  10:06 下午
 */
public interface Subject {

    Boolean  addObserver(Observer observer);
    Boolean updateObserver(Observer observer);
}
