package com.zlz.app.lfertainmentb.net;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpHeaders;
import com.zlz.app.lfertainmentb.App;
import com.zlz.app.lfertainmentb.bean.UserBean;
import com.zlz.app.lfertainmentb.utils.StringUtil;
import com.zlz.app.lfertainmentb.utils.AppUtil;
import com.zlz.app.lfertainmentb.utils.DESUtil;
import com.zlz.app.lfertainmentb.utils.UserUtils;

/**
 * 公共请求头，手机信息等
 */
public class ApiCommon {

    public static HttpHeaders getHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        CommonParam commonParam = new CommonParam();
        commonParam.setD_brand(android.os.Build.MODEL);
        String dcode = AppUtil.getDcode();
        if (StringUtil.isEmpty(dcode)) {
            dcode = DESUtil.encryptDES(System.currentTimeMillis() + "", DESUtil.key);
            AppUtil.setDcode(dcode);
        }
        commonParam.setD_code(dcode);
        commonParam.setD_model(android.os.Build.MODEL);
        commonParam.setD_platform(android.os.Build.VERSION.RELEASE);
        commonParam.setIiod("0");
        commonParam.setNc(ConnectionHttp.HttpType(App.getInstance()));
        commonParam.setV_code(App.getInstance().getVersionName());
        //commonParam.setToken(UserUtils.getUser(App.getInstance()).getToken());
        httpHeaders.put("API-Common", new Gson().toJson(commonParam));
        UserBean userBean = UserUtils.getUser(App.getInstance());       //获取token
        if (userBean == null) {
            httpHeaders.put("Token", "0");
        } else {
            httpHeaders.put("API-Token", userBean.getToken() == null ? "0" : userBean.getToken());
            //Log.e("UserTOKEN", userBean.getToken());
        }
        return httpHeaders;
    }
    //设备信息
    static class CommonParam {
        private String iiod;
        private String d_brand;
        private String d_platform;
        private String d_model;
        private String d_code;
        private String v_code;
        private String nc;


        public String getIiod() {
            return iiod;
        }

        public void setIiod(String iiod) {
            this.iiod = iiod;
        }

        public String getD_brand() {
            return d_brand;
        }

        public void setD_brand(String d_brand) {
            this.d_brand = d_brand;
        }

        public String getD_platform() {
            return d_platform;
        }

        public void setD_platform(String d_platform) {
            this.d_platform = d_platform;
        }

        public String getD_model() {
            return d_model;
        }

        public void setD_model(String d_model) {
            this.d_model = d_model;
        }

        public String getD_code() {
            return d_code;
        }

        public void setD_code(String d_code) {
            this.d_code = d_code;
        }

        public String getV_code() {
            return v_code;
        }

        public void setV_code(String v_code) {
            this.v_code = v_code;
        }

        public String getNc() {
            return nc;
        }

        public void setNc(String nc) {
            this.nc = nc;
        }
    }
}
