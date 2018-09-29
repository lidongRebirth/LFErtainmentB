package com.zlz.app.lfertainmentb.activitys.register.model;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zlz.app.lfertainmentb.net.ApiCommon;
import com.zlz.app.lfertainmentb.bean.CommonBean;
import com.zlz.app.lfertainmentb.mybaselibrary.utils.ToastUtil;
import com.zlz.app.lfertainmentb.net.JsonCallBack;
import com.zlz.app.lfertainmentb.net.Listener.OnNetFinishListener;
import com.zlz.app.lfertainmentb.net.RequestAddress;

/**
 * 注册
 */
public class RegisterModel {

    private Context context;
    private OnNetFinishListener onNetFinishListener;

    public RegisterModel(Context context, OnNetFinishListener onNetFinishListener) {
        this.context = context;
        this.onNetFinishListener = onNetFinishListener;
    }

    public void register(String tel, String smscode, String password, String email, final String realname, String sponsor) {
        OkGo.<String>post(RequestAddress.URL_REGISTER)
                .params("telephone", tel)
                .params("password", password)
                .params("smscode", smscode)
                .params("email", email)
                .params("realname", realname)
                .params("sponsor", sponsor)
                .headers(ApiCommon.getHttpHeader())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response!=null&&response.body()!=null){
                            onNetFinishListener.onSuccess(response.body());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        if(response!=null&&response.body()!=null){
                            onNetFinishListener.onFailed(response.body());
                        }
                    }
                });
    }

    //不行
    public void register2(String tel, String smscode, String password, String email, final String realname, String sponsor) {
        OkGo.<CommonBean>post(RequestAddress.URL_REGISTER)
                .params("telephone", tel)
                .params("password", password)
                .params("smscode", smscode)
                .params("email", email)
                .params("realname", realname)
                .params("sponsor", sponsor)
                .headers(ApiCommon.getHttpHeader())
                .execute(new JsonCallBack<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        if (response != null && response.body() != null){
                            ToastUtil.showToast(context, response.body().getMessage());
                        }
                    }
                });
    }
}
