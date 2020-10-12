package com.zy.java.design.patterns.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 原型模式（Prototype Pattern），相当于拷贝
 */
public class PrototypePattern {

    public interface ProtoType {
        ProtoType clone();

        ProtoType deepClone();
    }

    public static class PrototypeA implements ProtoType {
        int age;
        String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        // 浅克隆
        public ProtoType clone() {
            PrototypeA prototypeA = new PrototypeA();
            prototypeA.setAge(this.age);
            prototypeA.setName(this.name);
            return prototypeA;
        }
        // 深克隆
        public ProtoType deepClone() {
            try{
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(this);
                ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bis);
                ProtoType copy = (ProtoType)ois.readObject();
                return copy;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }
    public static void main(String[] args) {
        PrototypeA prototypeA = new PrototypeA();
        prototypeA.setName("zhangsan");
        prototypeA.setAge(12);
        ProtoType protoType = prototypeA.clone();
    }
}
