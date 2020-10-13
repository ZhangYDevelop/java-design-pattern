package com.zy.java.design.patterns.decorator;

/**
 * 装饰者模式（Decorator Pattern）
 * 列：煎饼加香肠、加鸡蛋
 */
public class DecoratorPattern {

    public static abstract class BatterCake {
       protected abstract String getBatterCake();
       protected abstract int getPrice();
    }

    /**
     * 煎饼类
     */
    public static class BaseBatterCake  extends BatterCake{

        @Override
        protected String getBatterCake() {
            return "煎饼";
        }

        @Override
        protected int getPrice() {
            return 5;
        }
    }

    public static   abstract class BatterCakeDecorator extends BatterCake {
        // 委派 静态代理
        private BatterCake  batterCake;

        public BatterCakeDecorator(BatterCake batterCake) {
            this.batterCake = batterCake;
        }

        @Override
        protected String getBatterCake() {
            return this.batterCake.getBatterCake();
        }

        @Override
        protected int getPrice() {
            return this.batterCake.getPrice();
        }
        abstract void doSomething();
    }
    public static   class BatterCakeWithEgg extends BatterCakeDecorator {

        public BatterCakeWithEgg(BatterCake batterCake) {
            super(batterCake);
        }

        @Override
        protected String getBatterCake() {
            return super.getBatterCake() + "鸡蛋";
        }

        @Override
        protected int getPrice() {
            return super.getPrice() + 2;
        }

        @Override
        void doSomething() {
            System.out.println("business ....");
        }
    }
    public static class BatterCakeWithEggAndHotDogs extends  BatterCakeDecorator {

        public BatterCakeWithEggAndHotDogs(BatterCake batterCake) {
            super(batterCake);
        }

        @Override
        protected String getBatterCake() {
            return super.getBatterCake() + "鸡蛋 + 肠";
        }

        @Override
        protected int getPrice() {
            return super.getPrice() + 3;
        }

        @Override
        void doSomething() {
            System.out.println("business ....");
        }
    }
    public static void main(String[] args) {
        BatterCake cake = new BaseBatterCake();
        System.out.println(cake.getBatterCake());
        cake = new BatterCakeWithEgg(cake);
        System.out.println(cake.getBatterCake());
        cake = new BatterCakeWithEggAndHotDogs(cake);
        System.out.println(cake.getBatterCake());
    }
}
