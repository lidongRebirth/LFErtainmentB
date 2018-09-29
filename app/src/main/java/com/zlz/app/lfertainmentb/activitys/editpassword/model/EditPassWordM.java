package com.zlz.app.lfertainmentb.activitys.editpassword.model;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zlz.app.lfertainmentb.net.ApiCommon;
import com.zlz.app.lfertainmentb.net.Listener.OnNetFinishListener;
import com.zlz.app.lfertainmentb.net.RequestAddress;

public class EditPassWordM {

    private Context context;
    private OnNetFinishListener onNetFinishListener;

    public EditPassWordM(Context context, OnNetFinishListener onNetFinishListener) {
        this.context = context;
        this.onNetFinishListener = onNetFinishListener;
    }
    public void commit(String tel,String onepassword,String twopassword,String smscode){

        OkGo.<String>post(RequestAddress.URL_CHANGE_PASSWORD)
                .headers(ApiCommon.getHttpHeader())
                .params("telephone",tel)
                .params("onepassword",onepassword)
                .params("twopassword",twopassword)
                .params("smscode",smscode)
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



}
