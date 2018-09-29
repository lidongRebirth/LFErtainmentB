package com.zlz.app.lfertainmentb.bean;

/**
 * 公用回调Bean   注册/忘记密码
 */
public class CommonBean {
    /**
     * isError : false
     * isOk : true
     * message : 注册成功
     * status : 200
     */

    private boolean isError;
    private boolean isOk;
    private String message;
    private String status;

    public boolean isIsError() {
        return isError;
    }

    public void setIsError(boolean isError) {
        this.isError = isError;
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
}
