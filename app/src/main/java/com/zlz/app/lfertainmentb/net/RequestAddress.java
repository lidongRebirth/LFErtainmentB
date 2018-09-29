package com.zlz.app.lfertainmentb.net;

import com.zlz.app.lfertainmentb.App;

/**
 *  应用的全部接口
 */
public class RequestAddress {
    //测试用，自己写的
    public static final String URL_BASE="http://www.wanandroid.com/tools/mockapi/10592/json1";
    //基础接口
    public static final String BASE_URL=App.getInstance().isDebug() ? "http://192.168.1.192:9997/" : "http://47.94.241.166:8191/";   // release
    //localhost
    public static final String URL_LOCALHOST="http://192.168.1.88:8080/";
    //获取验证码
    public static final String URL_GETCODE= URL_LOCALHOST+"zlz/api/sms";
    //注册接口
    public static final String URL_REGISTER=URL_LOCALHOST+"zlz/api/buser/register";//192.168.1.33    http://localhost:8080/zlz/api/buser/register
    //密码验证接口
    public static final String URL_LOGIN = URL_LOCALHOST+"zlz/api/buser/login";
    //忘记密码
    public static final String URL_CHANGE_PASSWORD = URL_LOCALHOST+"zlz/api/buser/updatepassword";


}
