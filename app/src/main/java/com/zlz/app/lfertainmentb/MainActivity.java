package com.zlz.app.lfertainmentb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zlz.app.lfertainmentb.activitys.login.LoginActivity;
import com.zlz.app.lfertainmentb.activitys.companycertify.CompanyCertifyActivity;
import com.zlz.app.lfertainmentb.activitys.editpassword.EditPasswordActivity;
import com.zlz.app.lfertainmentb.activitys.home.HomeUI;
import com.zlz.app.lfertainmentb.activitys.interestlable.InterestLabActivity;
import com.zlz.app.lfertainmentb.activitys.personcertify.PersonCertify;
import com.zlz.app.lfertainmentb.activitys.setting.SettingActivity;
import com.zlz.app.lfertainmentb.mybaselibrary.BaseActivity;
import com.zlz.app.lfertainmentb.mybaselibrary.utils.DateUtil;
import com.zlz.app.lfertainmentb.utils.StringUtil;
import com.zlz.app.lfertainmentb.mybaselibrary.utils.ToastUtil;
import com.zlz.app.lfertainmentb.textactivity.ActivityText;
import com.zlz.app.lfertainmentb.views.datepicker.DatePickerDialog;
import com.zlz.app.lfertainmentb.views.roundimageview.RoundedImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_go)
    Button btn_go;
    @BindView(R.id.tv_text)
    TextView tv_text;
    @BindView(R.id.btn_icon)
    Button btn_icon;
    @BindView(R.id.iv_icon)             //圆头像
    RoundedImageView iv_icon;
    @BindView(R.id.btn_to_login)        //登录
    Button btn_to_login;
    @BindView(R.id.btn_to_set)          //设置
    Button btn_to_set;
    @BindView(R.id.btn_to_home)         //首页
    Button btn_to_home;
    @BindView(R.id.btn_to_person)       //个人认证
    Button btn_to_person;
    @BindView(R.id.btn_to_company)      //公司认证
    Button btn_to_company;
    @BindView(R.id.btn_refresh)         //刷新
    Button btn_refresh;
    @BindView(R.id.btn_to_lab)
    Button btn_to_lab;
    @BindView(R.id.btn_to_password)
    Button btn_to_password;


    private Date brithday;
    private boolean isChoose = false;               //*日期是否更改
    //设置布局
    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }
    //View设置
    @Override
    protected void initView() {

    }
    //初始化数据
    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @OnClick({R.id.btn_go,R.id.btn_icon,R.id.btn_to_login,R.id.btn_to_set,R.id.btn_to_home,R.id.btn_to_person,R.id.btn_refresh,R.id.btn_to_lab,R.id.btn_to_company,R.id.btn_to_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_go:
                showDateDialog(DateUtil.getDateForString(DateUtil.getday()));       //日期展示框
                break;
            case R.id.btn_icon:
                iv_icon.setOval(true);                                              //设置圆形
                Glide.with(this).load("http://img4.duitang.com/uploads/item/201411/09/20141109142633_ncKBY.thumb.700_0.jpeg").into(iv_icon);
                break;
            case R.id.btn_to_login:
                startActivity(new Intent(this, LoginActivity.class));                 //登录
                break;
            case R.id.btn_to_set:                                                                   //设置
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.btn_to_home:                                                                  //首页
                startActivity(new Intent(this, HomeUI.class));
                break;
            case R.id.btn_to_person:                                                                //个人认证
                startActivity(new Intent(this, PersonCertify.class));
                break;
            case R.id.btn_to_company:                                                               //公司认证
                startActivity(new Intent(this, CompanyCertifyActivity.class));
                break;
            case R.id.btn_refresh:                                                                  //加载刷新
                startActivity(new Intent(this, ActivityText.class));
                break;
            case R.id.btn_to_lab:                                                                   //兴趣标签
                startActivity(new Intent(this, InterestLabActivity.class));
                break;
            case R.id.btn_to_password:
                startActivity(new Intent(this, EditPasswordActivity.class));
                break;
            default:
                break;
        }
    }

    private void showDateDialog(List<Integer> date) {           //*日期展示框
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {
                String strDateTime = dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2]));


                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String format = df.format(new Date());
                Date time = StringUtil.strToDate(format);
                Date currentTime = DateUtil.getNextDay(time);
                brithday = StringUtil.strToDate(strDateTime);
                int isGood = currentTime.compareTo(brithday);
                if (isGood > 0) {
                    isChoose = true;
                    tv_text.setText(strDateTime);
                } else {
                    ToastUtil.showToast(MainActivity.this, "出生日期不能大于当前日期");     //如何用的
                }
            }

            @Override
            public void onCancel() {

            }
        })

                .setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);

        builder.setMaxYear(DateUtil.getYear());
        builder.setMaxMonth(DateUtil.getDateForString(DateUtil.getToday()).get(1));
        builder.setMaxDay(DateUtil.getDateForString(DateUtil.getToday()).get(2));
        DatePickerDialog dateDialog = builder.create();
        dateDialog.show();
    }
}
