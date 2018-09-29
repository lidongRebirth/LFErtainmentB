package com.zlz.app.lfertainmentb.activitys.home;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zlz.app.lfertainmentb.R;
import com.zlz.app.lfertainmentb.activitys.publishactivity.PublishActivity;
import com.zlz.app.lfertainmentb.fragments.first.FirstFragment;
import com.zlz.app.lfertainmentb.fragments.second.SecondFragment;
import com.zlz.app.lfertainmentb.mybaselibrary.BaseUI;
import com.zlz.app.lfertainmentb.mybaselibrary.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

import static com.zlz.app.lfertainmentb.mybaselibrary.BaseActivity.addActivity;
import static com.zlz.app.lfertainmentb.mybaselibrary.BaseActivity.finishAll;
import static com.zlz.app.lfertainmentb.mybaselibrary.BaseActivity.removeActivity;


/**
 * 主页
 */
public class HomeUI extends BaseUI  {

    @BindView(R.id.fl_tab_container)
    FrameLayout fl_tab_container;
    @BindView(R.id.ly_tab_menu_home)            //首页
    LinearLayout ly_tab_menu_home;
    @BindView(R.id.ly_tab_menu_publish)         //发布
    LinearLayout ly_tab_menu_publish;
    @BindView(R.id.ly_tab_menu_mine)            //我的
    LinearLayout ly_tab_menu_mine;
    @BindView(R.id.tv_tab_menu_home)           //首页字体
    TextView tv_tab_menu_home;
    @BindView(R.id.tv_tab_menu_mine)
    TextView tv_tab_menu_mine;                 //我的字体




    private SupportFragment[] fragments = new SupportFragment[2];
    public static final int FIRST = 0;
    public static final int SECOND = 1;

    public int prePosition=0;//*前一个位置



    @Override
    protected int setContentView() {
        return R.layout.activity_home_ui;
    }

    @Override
    protected void initView() {
        tv_tab_menu_home.setSelected(false);    //首页默认选中
        //*看栈内fragment碎片是否为空
        SupportFragment firstFragment = findFragment(FirstFragment.class);
        if (firstFragment == null) {
            fragments[FIRST] = FirstFragment.newInstance();
            fragments[SECOND] = SecondFragment.newInstance();
            loadMultipleRootFragment(R.id.fl_tab_container, FIRST, fragments[FIRST], fragments[SECOND]);
        } else {
            fragments[FIRST] = firstFragment;
            fragments[SECOND] = findFragment(SecondFragment.class);
        }
    }
    @Override
    protected void initData(Bundle savedInstanceState) {
        addActivity(this);
    }

    //导航栏的切换
    @OnClick({R.id.ly_tab_menu_home,R.id.ly_tab_menu_publish,R.id.ly_tab_menu_mine})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ly_tab_menu_home:
                showHideFragment(fragments[0], fragments[prePosition]);  //同级Fragment场景下的切换
                prePosition = 0;
                setSelected();
                tv_tab_menu_home.setSelected(true);
                break;
            case R.id.ly_tab_menu_publish:
                ToastUtil.showToast(this,"发布");
                goTo(PublishActivity.class);
                break;
            case R.id.ly_tab_menu_mine:
                showHideFragment(fragments[1], fragments[prePosition]);  //同级Fragment场景下的切换
                prePosition = 1;
                setSelected();
                tv_tab_menu_mine.setSelected(true);
                break;
        }
    }
    public void setSelected(){
        tv_tab_menu_home.setSelected(false);
        tv_tab_menu_mine.setSelected(false);
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
//        finishAll();      日后完善点两次退出
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishAll();
    }

}
