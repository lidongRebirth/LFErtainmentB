package com.zlz.app.lfertainmentb.activitys.register.model;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.zlz.app.lfertainmentb.net.ApiCommon;
import com.zlz.app.lfertainmentb.activitys.register.bean.CodeBean;
import com.zlz.app.lfertainmentb.mybaselibrary.utils.ToastUtil;
import com.zlz.app.lfertainmentb.net.JsonCallBack;
import com.zlz.app.lfertainmentb.net.RequestAddress;

public class CodeModel {
    private Context context;

    public CodeModel(Context context) {
        this.context = context;
    }

    public void getCode(String tel) {
        OkGo.<CodeBean>get(RequestAddress.URL_GETCODE)
                .params("username", tel)
                .headers(ApiCommon.getHttpHeader())
                .execute(new JsonCallBack<CodeBean>() {
                    @Override
                    public void onSuccess(Response<CodeBean> response) {
                        if(response!=null&&response.body()!=null){
                            ToastUtil.showToast(context, response.body().getMessage());
                        }
                    }
                    @Override
                    public void onError(Response<CodeBean> response) {
                        super.onError(response);
                        if (response != null&&response.body()!=null) {
                            ToastUtil.showToast(context, "错误:"+response.body().getMessage());
                        }
                    }
                });

    }
}
