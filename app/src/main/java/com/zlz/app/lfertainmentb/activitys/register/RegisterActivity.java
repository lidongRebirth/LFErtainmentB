package com.zlz.app.lfertainmentb.activitys.register;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zlz.app.lfertainmentb.R;
import com.zlz.app.lfertainmentb.activitys.register.model.CodeModel;
import com.zlz.app.lfertainmentb.activitys.register.model.RegisterModel;
import com.zlz.app.lfertainmentb.bean.CommonBean;
import com.zlz.app.lfertainmentb.mybaselibrary.BaseActivity;
import com.zlz.app.lfertainmentb.net.Listener.OnNetFinishListener;
import com.zlz.app.lfertainmentb.utils.StringUtil;
import com.zlz.app.lfertainmentb.mybaselibrary.utils.TimeUtil;
import com.zlz.app.lfertainmentb.mybaselibrary.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册界面
 */
public class RegisterActivity extends BaseActivity implements TimeUtil.TimeMesListener,OnNetFinishListener {

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_middle)
    TextView tv_middle;
    @BindView(R.id.tv_right)
    TextView tv_right;

    @BindView(R.id.btn_code)        //验证码
            Button btn_code;
    @BindView(R.id.btn_agree)       //注册
            Button btn_agree;
    @BindView(R.id.et_tel)          //电话
            EditText et_tel;
    @BindView(R.id.et_code)         //验证码
            EditText et_code;
    @BindView(R.id.et_password)     //密码
            EditText et_password;
    @BindView(R.id.et_email)        //邮箱
            EditText et_email;
    @BindView(R.id.et_name)         //名字
            EditText et_name;
    @BindView(R.id.et_sponsor)      //主办方名称
            EditText et_sponsor;


    private TimeUtil timeUtil;
    private long getCodeTime;       //验证码获取时间
    private CodeModel codeModel;            //获取验证码
    private RegisterModel registerModel;

    private String telStr;
    private String codeStr;
    private String passwordStr;
    private String emailStr;
    private String nameStr;
    private String sponsorStr;


    @Override
    protected int setContentView() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        tv_middle.setText("注册");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        timeUtil = new TimeUtil();
        codeModel = new CodeModel(this);
        registerModel = new RegisterModel(this, (OnNetFinishListener) this);

        et_tel.addTextChangedListener(textWatcher);
        et_code.addTextChangedListener(textWatcher);
        et_password.addTextChangedListener(textWatcher);
        et_email.addTextChangedListener(textWatcher);
        et_name.addTextChangedListener(textWatcher);
        et_sponsor.addTextChangedListener(textWatcher);

    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            telStr = et_tel.getText().toString();
            codeStr = et_code.getText().toString();
            passwordStr = et_password.getText().toString();
            emailStr = et_email.getText().toString();
            nameStr = et_name.getText().toString();
            sponsorStr = et_sponsor.getText().toString();

        }
    };

    @OnClick({R.id.iv_back, R.id.btn_code, R.id.btn_agree})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_code:         //获取验证码
                if (!StringUtil.isMobile(telStr)) {
                    ToastUtil.showToast(this, "请输入正确的手机号");
                } else {
                    if (getCodeTime == 0 || System.currentTimeMillis() - getCodeTime >= 60000) {
                        timeUtil.excuse(1000, 60000, this);
                        getCodeTime = System.currentTimeMillis();
                        codeModel.getCode(telStr);     //获取验证码
                    } else {
                        ToastUtil.showToast(this, "请在一分钟之后进行操作");
                    }
                }
                break;
            case R.id.btn_agree:        //注册
                if (StringUtil.isEmpty(telStr)) {
                    ToastUtil.showToast(this, "请输入手机号");
                } else if (!StringUtil.isMobile(telStr)) {
                    ToastUtil.showToast(this, "请输入正确手机号");
                } else if (StringUtil.isEmpty(codeStr)) {
                    ToastUtil.showToast(this, "请输入验证码");
                } else if (StringUtil.isEmpty(passwordStr)) {
                    ToastUtil.showToast(this, "请输入密码");
                } else if (StringUtil.isEmpty(emailStr)) {
                    ToastUtil.showToast(this, "请输入邮箱");
                }else if (!StringUtil.isEmail(emailStr)) {
                    ToastUtil.showToast(this, "请输入正确邮箱");
                } else if (StringUtil.isEmpty(nameStr)) {
                    ToastUtil.showToast(this, "请输入姓名");
                } else if (StringUtil.isEmpty(sponsorStr)) {
                    ToastUtil.showToast(this, "请输入主办方名称");
                } else {
                    registerModel.register(telStr, codeStr, passwordStr, emailStr, nameStr, sponsorStr);
                }
                break;
            default:
                break;
        }

    }

    //*-----秒数监听
    @Override
    public void timeMesListener(long allTime, long shengTime, long cishu) {
        btn_code.setText(shengTime / 1000 + "s");
    }

    @Override
    public void timeStartListener(long allTime) {
        btn_code.setEnabled(false);
        btn_code.setText(allTime / 1000 + "s");
    }

    @Override
    public void timeFinishListener() {
        btn_code.setEnabled(true);
        btn_code.setText("发送验证码");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timeUtil.onDestory();
    }
    //*--

    //注册回调
    @Override
    public void onSuccess(String response) {
        Gson gson = new Gson();
        CommonBean commonBean  = gson.fromJson(response, CommonBean.class);
        ToastUtil.showToast(this,commonBean.getMessage());
        if(commonBean.getStatus().equals("200")){
            finish();
        }
    }

    @Override
    public void onFailed(String response) {
        Gson gson = new Gson();
        CommonBean commonBean  = gson.fromJson(response, CommonBean.class);
        ToastUtil.showToast(this,commonBean.getMessage());
    }

}
