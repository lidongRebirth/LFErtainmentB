package com.zlz.app.lfertainmentb.activitys.register.bean;

/**
 * 发送验证码返回Bean
 */
public class CodeBean {
    /**
     * msg : 验证码发送成功
     * isError : false
     * isOk : true
     * message : 成功
     * status : 200
     */
    private String msg;
    private boolean isError;
    private boolean isOk;
    private String message;
    private String status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

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
