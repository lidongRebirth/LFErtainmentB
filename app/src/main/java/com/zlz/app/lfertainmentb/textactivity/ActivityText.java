package com.zlz.app.lfertainmentb.textactivity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zlz.app.lfertainmentb.R;
import com.zlz.app.lfertainmentb.mybaselibrary.BaseActivity;
import com.zlz.app.lfertainmentb.mybaselibrary.utils.ToastUtil;

import butterknife.BindView;

/**
 * 刷新和加载
 */
public class ActivityText extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.smartlayout)
    RefreshLayout smartlayout;
    private Boolean isSuccess;


    @Override
    protected int setContentView() {
        return R.layout.text;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        smartlayout.setOnRefreshListener(this);
        smartlayout.setOnLoadMoreListener(this);
    }

    //刷新    操作
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isSuccess=true;
                stopRefresh();
            }
        }).start();

    }
    //加载更多      操作
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isSuccess=true;
                stopLoadMore();
            }
        }).start();
    }
    public void stopRefresh(){  //还在子线程中
        if(isSuccess){
            smartlayout.finishRefresh(0);
        }else{
            smartlayout.finishRefresh(false);
        }
    }
    public void stopLoadMore(){  //还在子线程中
        if(isSuccess){
            smartlayout.finishLoadMore(0);
        }else{
            smartlayout.finishLoadMore(false);
        }
    }
}
