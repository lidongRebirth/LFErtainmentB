package com.zlz.app.lfertainmentb.activitys.login.bean;

public class JsonBean1 {
    /**
     * msg : 验证码发送成功
     * isError : false
     * isOk : true
     * message : 成功
     * status : 300
     */

    private String msg;
    private boolean isError;
    private boolean isOk;

    @Override
    public String toString() {
        return "JsonBean1{" +
                "msg='" + msg + '\'' +
                ", isError=" + isError +
                ", isOk=" + isOk +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

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
