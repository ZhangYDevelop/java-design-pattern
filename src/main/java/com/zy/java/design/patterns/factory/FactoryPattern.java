package com.zy.java.design.patterns.factory;


/**
 * @AUTHOR zhangy
 * 2020-10-11  20:22
 * 工厂模式(simple factory pattern) ,列子：Spring 中的BeanFactory 通过getBean()
 */
public class FactoryPattern {

    /**
     * 生产汽车的工厂
     */
    private static  class CarFactory {

        public  static Car getCarInstance(String type) {
            if (ICar.BEN_CHI.equals(type)) {
                return  new BenChiCar().createCar();
            }
            if (ICar.DA_ZHONG.equals(type)) {
                return  new DaZhongCar().createCar();
            }

            return  null;
        }

    }

    /**
     * 制造商
     */
    private static class BenChiCar implements ICar {
        public Car createCar() {
            System.out.println("生产奔驰车。。。");
            return new Car();
        }
    }
    /**
     * 制造商
     */
    private static  class DaZhongCar implements ICar {
        public Car createCar() {
            System.out.println("生产大众车...");
            return new Car();
        }
    }

    /**
     * 对生产车辆抽象
     */
    private interface ICar {
        String BEN_CHI = "BEN_CHI";
        String DA_ZHONG = "DA_ZHONG";

        Car createCar();
    }

    /**
     * 物质抽象
     */
    private  static  class Car {

        private String whell;

        private String engine;

    }

    public static void main(String[] args) {
        CarFactory.getCarInstance(ICar.DA_ZHONG);
    }
}
