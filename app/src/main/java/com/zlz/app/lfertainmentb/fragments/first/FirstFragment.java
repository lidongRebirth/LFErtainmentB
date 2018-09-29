package com.zlz.app.lfertainmentb.fragments.first;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.zlz.app.lfertainmentb.R;
import com.zlz.app.lfertainmentb.mybaselibrary.BaseFM;
import com.zlz.app.lfertainmentb.views.roundimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页碎片
 */
public class FirstFragment extends BaseFM {

    @BindView(R.id.tv_selectactivity)   //全部
    TextView tv_selectactivity;
    @BindView(R.id.riv_icon)            //头像
    RoundedImageView riv_icon;
    @BindView(R.id.tv_name)             //姓名
    TextView tv_name;
    @BindView(R.id.tv_identify)         //认证
    TextView tv_identify;



    public static FirstFragment newInstance() {
        Bundle args = new Bundle();
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fm_firstfragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.tv_selectactivity)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_selectactivity:        //显示popwindow
                break;
            default:
                break;
        }
    }


}
