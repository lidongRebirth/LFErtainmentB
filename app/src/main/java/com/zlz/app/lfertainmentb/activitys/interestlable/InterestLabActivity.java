package com.zlz.app.lfertainmentb.activitys.interestlable;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zlz.app.lfertainmentb.R;
import com.zlz.app.lfertainmentb.activitys.interestlable.adapter.LinkLabelAdapter;
import com.zlz.app.lfertainmentb.activitys.interestlable.bean.Bean;
import com.zlz.app.lfertainmentb.mybaselibrary.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class InterestLabActivity extends BaseActivity {

    @BindView(R.id.recycler_lab)
    RecyclerView labRecyclerView;

    private List<Bean> lableBeanList = new ArrayList<>();
    private List<Bean> oneList = new ArrayList<>();
    private List<Bean> twoList = new ArrayList<>();
    private List<Bean> threeList = new ArrayList<>();
    private List<Bean> fourList = new ArrayList<>();
    private List<Bean> allList = new ArrayList<>();





    @Override
    protected int setContentView() {
        return R.layout.activity_interest_lab;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //标题
        Bean bean1 = new Bean(100, "行业", 1, 0, false);
        Bean bean2 = new Bean(100, "生活", 2, 0, false);
        Bean bean3 = new Bean(100, "亲子", 3, 0, false);
        Bean bean4 = new Bean(100, "学习", 4, 0, false);
        //行业
        Bean bean5 = new Bean(200, "IT互联网", 5, 1, true);
        Bean bean6 = new Bean(200, "创业", 6, 1, false);
        Bean bean7 = new Bean(200, "科技", 7, 1, true);
        Bean bean8 = new Bean(200, "金融", 8, 1, false);
        Bean bean9 = new Bean(200, "游戏", 9, 1, false);
        Bean bean10 = new Bean(200, "文娱", 10, 1, true);
        //生活
        Bean bean11 = new Bean(200, "演出", 8, 2, false);
        Bean bean12 = new Bean(200, "文艺", 9, 2, false);
        Bean bean13 = new Bean(200, "手工", 10, 2, true);
        //亲子
        Bean bean14 = new Bean(200, "儿童才艺", 8, 3, false);
        Bean bean15 = new Bean(200, "益智潮玩", 9, 3, false);
        Bean bean16 = new Bean(200, "儿童剧", 10, 3, true);
        //学习
        Bean bean17 = new Bean(200, "社团", 8, 4, false);
        Bean bean18 = new Bean(200, "讲座", 9, 4, false);
        Bean bean19 = new Bean(200, "课程", 10, 4, true);

        lableBeanList.add(bean1);
        lableBeanList.add(bean2);
        lableBeanList.add(bean3);
        lableBeanList.add(bean4);
        lableBeanList.add(bean5);
        lableBeanList.add(bean6);
        lableBeanList.add(bean7);
        lableBeanList.add(bean8);
        lableBeanList.add(bean9);
        lableBeanList.add(bean10);
        lableBeanList.add(bean11);
        lableBeanList.add(bean12);
        lableBeanList.add(bean13);
        lableBeanList.add(bean14);
        lableBeanList.add(bean15);
        lableBeanList.add(bean16);
        lableBeanList.add(bean17);
        lableBeanList.add(bean18);
        lableBeanList.add(bean19);

        for(int i = 0;i<lableBeanList.size();i++){
            if(lableBeanList.get(i).getType()==100){
                if(lableBeanList.get(i).getId()==1){
                    oneList.add(lableBeanList.get(i));
                }else if(lableBeanList.get(i).getId()==2){
                    twoList.add(lableBeanList.get(i));
                }else if(lableBeanList.get(i).getId()==3){
                    threeList.add(lableBeanList.get(i));
                }else if(lableBeanList.get(i).getId()==4){
                    fourList.add(lableBeanList.get(i));
                }
            }
        }
        for(int i =0;i<lableBeanList.size();i++){
            if(lableBeanList.get(i).getType()==200&&lableBeanList.get(i).getMid()==1){
                oneList.add(lableBeanList.get(i));
            }
            if(lableBeanList.get(i).getType()==200&&lableBeanList.get(i).getMid()==2){
                twoList.add(lableBeanList.get(i));
            }
            if(lableBeanList.get(i).getType()==200&&lableBeanList.get(i).getMid()==3){
                threeList.add(lableBeanList.get(i));
            }
            if(lableBeanList.get(i).getType()==200&&lableBeanList.get(i).getMid()==4){
                fourList.add(lableBeanList.get(i));
            }
        }
        allList.addAll(oneList);
        allList.addAll(twoList);
        allList.addAll(threeList);
        allList.addAll(fourList);


    }

    @Override
    protected void initView() {
        final GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {       //这个方法返回的是当前位置的 item 跨度大小。
            @Override
            public int getSpanSize(int position) {                          //spanCount除以getSpanSize是每行的item个数  通过设置getSpanSize的返回值来设定每行的item个数
                int type = labRecyclerView.getAdapter().getItemViewType(position);//获得返回值
                if (type == 100) {       //标题类型
                    return mLayoutManager.getSpanCount();       //1个item
                } else {                //标签类型
                    return 1;                                   //3个item
                }
            }
        });
        labRecyclerView.setLayoutManager(mLayoutManager);

        LinkLabelAdapter linkLabelAdapter = new LinkLabelAdapter(getApplicationContext(), allList);
        labRecyclerView.setAdapter(linkLabelAdapter);

    }
}
