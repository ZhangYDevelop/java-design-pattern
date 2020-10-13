package com.zy.java.design.patterns.observe;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式（Observe Pattern）
 * 场景：学生反馈问题，老师接收
 */
public class ObservePattern {

    public static class Question {
        private  String user;
        private String questionDescribe;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getQuestionDescribe() {
            return questionDescribe;
        }

        public void setQuestionDescribe(String questionDescribe) {
            this.questionDescribe = questionDescribe;
        }
    }

    /**
     *  JDK提供观察者、被观察者模式
     */
    public static class MesageObserve extends Observable {
        private static MesageObserve mesageObserve = null;
        private String name = "学生管理平台";

        public String getName() {
            return name;
        }

        public static MesageObserve getInstance() {
            if (null == mesageObserve) {
                mesageObserve = new MesageObserve();
            }
            return mesageObserve;
        }

        public void publishQuestion(Question question) {
            System.out.println("学生：" + question.getUser() + "在平台上反馈了一个问题");
            setChanged();
            notifyObservers(question);
        }
    }

    public static class  Teacher  implements Observer{
        private String name;
        public Teacher(String name){
            this.name = name;
        }
        public void update(Observable o, Object arg) {
            MesageObserve gper = (MesageObserve)o;
            Question question = (Question)arg;
            System.out.println("===============================");
            System.out.println(name + "老师，你好！\n" +
                            "您收到了一个来自“" + gper.getName() + "”的提问，希望您解答，问题内容如下：\n" +
                            question.getQuestionDescribe() + "\n" +
                    "提问者：" + question.getUser());
        }
    }

    public static void main(String[] args) {
        MesageObserve mesageObserve = MesageObserve.getInstance();
        Teacher zhang = new Teacher("zhang");
        Teacher huang = new Teacher("huang");
        mesageObserve.addObserver(zhang);
        mesageObserve.addObserver(huang);

        Question question = new Question();
        question.setUser("小明");
        question.setQuestionDescribe("装饰者模式怎么使用。。。");
        mesageObserve.publishQuestion(question);
    }
}
