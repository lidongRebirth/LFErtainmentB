package com.zlz.app.lfertainmentb.mybaselibrary;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zlz.app.lfertainmentb.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 供fragmentation使用的activity基类
 */
public abstract class BaseUI extends SupportActivity {
    protected Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());   //要在绑定之前
        mUnbinder= ButterKnife.bind(this);
        initView();
        initData(savedInstanceState);
    }

    /**
     * 设置布局文件
     *
     * @return
     */
    protected abstract int setContentView();

    /**
     * 设置数据
     */
    protected abstract void initData(Bundle savedInstanceState);
    /**
     * 设置View
     */
    protected abstract void initView();
    //跳转页面的基础方法
    public void goToBase(Class clz, final Bundle bundle, final int requestCode, final boolean isFinish){
        Intent intent = new Intent(this,clz);
        //设置bundle
        if (bundle != null){
            intent.putExtras(bundle);
        }
        //设置requestcode
        startActivityForResult(intent,requestCode);
        //动画效果
        //overridePendingTransition(R.anim.activity_open_common, 0);
        //设置是否结束当前页面
        if (isFinish){
            finish();
        }
    }
    //跳转页面
    public void goTo(Class clz){
        goTo(clz,null);
    }
    //跳转页面
    public void goTo(Class clz, Bundle bundle){
        goToBase(clz,bundle,-1,false);
    }
    //跳转页面
    public void goTo(Class clz, int requestCode){
        goToBase(clz,null,requestCode,false);
    }

    //跳转页面
    public void goTo(Class clz, int requestCode, Bundle bundle){
        goToBase(clz,bundle,requestCode,false);
    }

    //跳转页面并销毁当前页面
    public void goToAndFinish(final Class clz){
        goToBase(clz,null,-1,true);
    }

    //跳转页面并销毁当前页面
    public void goToAndFinish(final Class clz,Bundle bundle){
        goToBase(clz,bundle,-1,true);
    }


    @Override
    protected void onDestroy() {
        if (this.mUnbinder != null) {
            this.mUnbinder.unbind();            //Butterknife解绑
        }
        super.onDestroy();
    }
}
