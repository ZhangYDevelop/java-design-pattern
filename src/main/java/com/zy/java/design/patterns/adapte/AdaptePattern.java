package com.zy.java.design.patterns.adapte;


/**
 * @AUTHOR zhangy
 * 2020-10-11  20:51
 * 适配器模式（adapter pattern),不改变原有规则的前提下适应新的规则需求
 */
public class AdaptePattern {

    public static  class LoginService {
        public Object login(String userName, String password) {
            System.out.println("账号密码登录");
            return  new Object();
        }
    }

    /**
     * 登录业务适配的抽象
     */
    public interface LoginAdapter {
        Object login(LoginAdapter adapter);

        boolean support(Object adapter);
    }

    public static  class LoginForQQ implements LoginAdapter {

        public Object login(LoginAdapter adapter) {
            System.out.println("qq 登录");
            return new Object();
        }

        public boolean support(Object adapter) {
            return adapter instanceof LoginForQQ;
        }
    }

    public static class LoginForWechat implements LoginAdapter {
        public Object login(LoginAdapter adapter) {
            System.out.println("wechat 登录");
            return new Object();
        }

        public boolean support(Object adapter) {
            return adapter instanceof LoginForWechat;
        }
    }

    /**
     * 第三方登录
     */
    public interface LoginForthirdPart {

        Object loginForQQ();

        Object loginForWechat();

    }

    /**
     *扩展原有的业务逻辑，不改变原有代码，适应新的需求
     */
    public static class LoginForthirdPartService  extends LoginService implements LoginForthirdPart {

        public Object loginForQQ() {

            return doLogin(LoginForQQ.class);
        }

        public Object loginForWechat() {
            return doLogin(LoginForWechat.class);
        }

       private Object  doLogin (Class<? extends  LoginAdapter> clazz) {
           try {
               LoginAdapter adapter = clazz.newInstance();
               if (adapter.support(adapter) ) {
                   return  adapter.login(adapter);
               } else {
                   return  null;
               }
           } catch (InstantiationException e) {
               e.printStackTrace();
           } catch (IllegalAccessException e) {
               e.printStackTrace();
           }

           return  null;
       }

    }

    public static void main(String[] args) {

        new LoginForthirdPartService().loginForWechat();
    }

}
