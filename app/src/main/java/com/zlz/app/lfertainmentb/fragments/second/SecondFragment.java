package com.zlz.app.lfertainmentb.fragments.second;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zlz.app.lfertainmentb.R;
import com.zlz.app.lfertainmentb.mybaselibrary.BaseFM;
import com.zlz.app.lfertainmentb.mybaselibrary.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置界面碎片
 */
public class SecondFragment extends BaseFM {

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_middle)
    TextView tv_middle;
    @BindView(R.id.tv_right)
    TextView tv_right;

    @BindView(R.id.rel_suggest)          //意见反馈
            RelativeLayout rel_suggest;
    @BindView(R.id.rel_nav)             //新手导航
            RelativeLayout rel_nav;
    @BindView(R.id.rel_wallet)          //我的钱包
            RelativeLayout rel_wallet;
    @BindView(R.id.rel_newversion)      //检测版本
            RelativeLayout rel_newversion;
    @BindView(R.id.rel_aboutours)       //关于我们
            RelativeLayout rel_aboutours;
    @BindView(R.id.rel_quit)            //退出
            RelativeLayout rel_quit;


    public static SecondFragment newInstance() {
        Bundle args = new Bundle();
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fm_secondfragment;
    }

    @Override
    protected void initView(View view) {
        tv_middle.setText("设置");
    }
    @Override
    protected void initData() {

    }

    @OnClick({R.id.rel_suggest, R.id.rel_nav, R.id.rel_wallet, R.id.rel_newversion, R.id.rel_aboutours, R.id.rel_quit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rel_suggest:
                ToastUtil.showToast(getContext(),"意见反馈");
                break;
            case R.id.rel_nav:
                ToastUtil.showToast(getContext(),"新手导航");

                break;
            case R.id.rel_wallet:
                ToastUtil.showToast(getContext(),"我的钱包");
                break;
            case R.id.rel_newversion:
                ToastUtil.showToast(getContext(),"检测新版本");
                break;
            case R.id.rel_aboutours:
                ToastUtil.showToast(getContext(),"关于我们");
                break;
            case R.id.rel_quit:
                ToastUtil.showToast(getContext(),"退出");
                getActivity().onBackPressed();
                break;
            default:
                break;
        }
    }
}
