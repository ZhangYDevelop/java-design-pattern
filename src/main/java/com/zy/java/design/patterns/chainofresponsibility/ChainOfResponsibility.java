package com.zy.java.design.patterns.chainofresponsibility;

/**
 * 责任链模式（Chain of Responsibility）
 * 列：对参数校验（非空、登录、权限）
 */
public class ChainOfResponsibility {

    public static class Params {
        private String userName;
        private String token;
        private String userRule;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUserRule() {
            return userRule;
        }

        public void setUserRule(String userRule) {
            this.userRule = userRule;
        }
    }


    public static abstract class Handler {
        public Handler chain;

        public void  setNextHandler (Handler handler) {
            this.chain = handler;
        }

        public abstract  void doHandler (Params params);
    }

    /**
     * 非空参数验证Handler
     */
    public static class  VolidateHandler extends  Handler {
        @Override
        public void doHandler(Params params) {
            System.out.println("参数非空验证通过。。。");
            chain.doHandler(params);
        }
    }
    /**
     * 角色验证Handler
     */
    public static class RoleHandler extends  Handler {
        @Override
        public void doHandler(Params params) {
            System.out.println("角色验证通过。。。");
            chain.doHandler(params);
        }
    }
    /**
     * 权限验证Handler
     */
    public static class AuthorHandler extends  Handler {
        @Override
        public void doHandler(Params params) {
            System.out.println("权限验证通过。。。");
            if (null != chain) {
                chain.doHandler(params);
            }
        }
    }

    public static class HandlerService {
        public boolean userLogin(String userName) {
            Params params = new Params();
            params.setUserName(userName);
            params.setToken("token");
            params.setUserRule("admin");
            VolidateHandler volidateHandler = new VolidateHandler();
            RoleHandler roleHandler = new RoleHandler();
            AuthorHandler authorHandler = new AuthorHandler();
            volidateHandler.setNextHandler(roleHandler);
            roleHandler.setNextHandler(authorHandler);
            volidateHandler.doHandler(params);
            System.out.println("login success");
            return  true;
        }
    }

    public static void main(String[] args) {
        new HandlerService().userLogin("zhangsan ");
    }
}
