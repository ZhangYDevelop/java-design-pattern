package com.zy.java.design.patterns.singleton;

/**
 * @AUTHOR zhangy
 * 2020-10-11  13:45
 */
public class SingletonPattern {

    // volatile 修饰，线程可见
    private volatile static SingletonPattern  singleton= null;
    // 私有化构造方案
    private SingletonPattern() {
        if (null != singleton) {
            throw new RuntimeException("单列不允许多实例");
//            //防止以下流氓方法
//            Class<?> clazz = Singleton.class;
//            try {
//                // 通过反射获取构造方法
//                Constructor construct =  clazz.getDeclaredConstructor(null);
//                // 强制访问
//                construct.setAccessible(true);
//                // 实例化对象
//                Object o1 = construct.newInstance();
//                Object o2 = construct.newInstance();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }
    /**
     * 双重检查
     * @return
     */
    public static final SingletonPattern  getInstance() {
        if (null == singleton) {
            synchronized (SingletonPattern.class) {
                if (null == singleton) {
                    singleton = new SingletonPattern();
                }
            }
        }
        return singleton;
    }

    /**
     * 通过内部类获取
     * @return
     */
    public static final SingletonPattern  getInstanceExt() {
        //在返回结果以前，一定会先加载内部类
        return SingleHolder.singleton;
    }
    /**
     * 防止序列化破坏单列
     * @return
     */
    private Object readResolve(){
        return singleton;
    }
    private static class SingleHolder {
        private static final SingletonPattern singleton = new SingletonPattern();
    }
}
