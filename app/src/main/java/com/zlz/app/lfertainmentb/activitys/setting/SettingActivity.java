package com.zlz.app.lfertainmentb.activitys.setting;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlz.app.lfertainmentb.R;
import com.zlz.app.lfertainmentb.mybaselibrary.BaseActivity;

import butterknife.BindView;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_middle)
    TextView tv_middle;
    @BindView(R.id.tv_right)
    TextView tv_right;

    @Override
    protected int setContentView() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        tv_middle.setText("设置");
        tv_right.setText("右边");
    }
}
