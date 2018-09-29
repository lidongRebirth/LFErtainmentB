package com.zlz.app.lfertainmentb.activitys.personcertify;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zlz.app.lfertainmentb.R;
import com.zlz.app.lfertainmentb.mybaselibrary.BaseActivity;
import com.zlz.app.lfertainmentb.mybaselibrary.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonCertify extends BaseActivity {


    @BindView(R.id.ll_image)
    LinearLayout ll_image;
    @BindView(R.id.iv_pic)
    ImageView iv_pic;

    @Override
    protected int setContentView() {
        return R.layout.activity_person_certify;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {

    }
    @OnClick({R.id.ll_image,R.id.iv_pic})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_image:             //照片
                ToastUtil.showToast(this,"选择照片");
                ll_image.setVisibility(View.GONE);
                iv_pic.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_pic:               //照片

                break;
        }
    }
}
