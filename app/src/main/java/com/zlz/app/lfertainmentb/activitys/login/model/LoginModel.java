package com.zlz.app.lfertainmentb.activitys.login.model;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zlz.app.lfertainmentb.net.Listener.OnNetFinishListener;
import com.zlz.app.lfertainmentb.net.ApiCommon;
import com.zlz.app.lfertainmentb.mybaselibrary.utils.ToastUtil;
import com.zlz.app.lfertainmentb.net.RequestAddress;

/**
 * 登录网络验证实现
 */
public class LoginModel {


    private Context context;
    private OnNetFinishListener onNetFinishListener;

    public LoginModel(Context context, OnNetFinishListener onNetFinishListener) {
        this.context = context;
        this.onNetFinishListener = onNetFinishListener;
    }


    public void login(String tel, String password) {

        OkGo.<String>post(RequestAddress.URL_LOGIN)
                .headers(ApiCommon.getHttpHeader())
                .params("telephone", tel)
                .params("password", password)
                .params("deviceToken", "")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null && response.body() != null) {
                            onNetFinishListener.onSuccess(response.body());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        if (response != null && response.body() != null) {
                            onNetFinishListener.onFailed(response.body());
                        }
                    }
                });


    }
}
