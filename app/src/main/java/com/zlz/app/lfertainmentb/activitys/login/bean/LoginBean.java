package com.zlz.app.lfertainmentb.activitys.login.bean;

public class LoginBean {

    /**
     * isError : false
     * data : {"sponsor":null,"telephone":"18734869839","id":"aa9371ed85b44e7fb15e8feda612a37d","email":null,"realname":null,"token":"caaa6e1ab8834bd49daaa0d0999513c3"}
     * isOk : true
     * message : 登陆成功
     * status : 200
     */

    private boolean isError;
    private DataBean data;
    private boolean isOk;
    private String message;
    private String status;

    public boolean isIsError() {
        return isError;
    }

    public void setIsError(boolean isError) {
        this.isError = isError;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isIsOk() {
        return isOk;
    }

    public void setIsOk(boolean isOk) {
        this.isOk = isOk;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * sponsor : null
         * telephone : 18734869839
         * id : aa9371ed85b44e7fb15e8feda612a37d
         * email : null
         * realname : null
         * token : caaa6e1ab8834bd49daaa0d0999513c3
         */

        private Object sponsor;
        private String telephone;
        private String id;
        private Object email;
        private Object realname;
        private String token;

        public Object getSponsor() {
            return sponsor;
        }

        public void setSponsor(Object sponsor) {
            this.sponsor = sponsor;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getRealname() {
            return realname;
        }

        public void setRealname(Object realname) {
            this.realname = realname;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
