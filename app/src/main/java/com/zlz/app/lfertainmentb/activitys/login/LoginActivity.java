package com.zlz.app.lfertainmentb.activitys.login;

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
import com.zlz.app.lfertainmentb.activitys.editpassword.EditPasswordActivity;
import com.zlz.app.lfertainmentb.activitys.home.HomeUI;
import com.zlz.app.lfertainmentb.net.Listener.OnNetFinishListener;
import com.zlz.app.lfertainmentb.activitys.login.bean.LoginBean;
import com.zlz.app.lfertainmentb.activitys.login.model.LoginModel;
import com.zlz.app.lfertainmentb.activitys.register.RegisterActivity;
import com.zlz.app.lfertainmentb.mybaselibrary.BaseActivity;
import com.zlz.app.lfertainmentb.utils.StringUtil;
import com.zlz.app.lfertainmentb.mybaselibrary.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements OnNetFinishListener{

    @BindView(R.id.btn_login)                //登录
    Button btn_login;
    @BindView(R.id.tv_register)             //注册
    TextView tv_register;
    @BindView(R.id.tv_forget_password)       //忘记密码
    TextView tv_forget_password;

    @BindView(R.id.iv_clear_tel)
    ImageView iv_clear_tel;
    @BindView(R.id.iv_clear_password)
    ImageView iv_clear_password;

    @BindView(R.id.et_tel)          //电话
    EditText et_tel;
    @BindView(R.id.et_password)     //密码
    EditText et_password;

    private LoginModel loginModel;

    @Override
    protected int setContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        //编辑框监听
        et_tel.addTextChangedListener(textWatcher);
        et_password.addTextChangedListener(textWatcher);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        loginModel = new LoginModel(this, (OnNetFinishListener) this);
        addActivity(this);
    }

    @OnClick({R.id.btn_login, R.id.tv_register, R.id.tv_forget_password, R.id.iv_clear_tel, R.id.iv_clear_password})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_login:                        //登录
                String tel = et_tel.getText().toString();
                String password = et_password.getText().toString();
                if(StringUtil.isEmpty(tel)){
                    ToastUtil.showToast(this,"请输入手机号");
                }else if(StringUtil.isEmpty(password)){
                    ToastUtil.showToast(this,"请输入密码");
                }else {//手机号验证
                    loginModel.login(tel,password);
                }
                break;
            case R.id.tv_register:
                goTo(RegisterActivity.class);
                break;
            case R.id.tv_forget_password:
                goTo(EditPasswordActivity.class);
                break;
            case R.id.iv_clear_tel:
                et_tel.setText("");
                break;
            case R.id.iv_clear_password:
                et_password.setText("");
                break;
            default:
                break;
        }
    }

    //按键监听
    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            iv_clear_tel.setVisibility(StringUtil.isEmpty(et_tel.getText().toString().trim()) ? View.GONE : View.VISIBLE);
            iv_clear_password.setVisibility(StringUtil.isEmpty(et_password.getText().toString().trim()) ? View.GONE : View.VISIBLE);
        }
    };

    //登录监听回调
    @Override
    public void onSuccess(String response) {
        Gson gson = new Gson();
        LoginBean loginBean =gson.fromJson(response, LoginBean.class);
        ToastUtil.showToast(this,loginBean.getMessage());
        if(loginBean.getStatus().equals("200")){
            goTo(HomeUI.class);
        }

    }
    @Override
    public void onFailed(String response) {
        Gson gson = new Gson();
        LoginBean loginBean =gson.fromJson(response, LoginBean.class);
        ToastUtil.showToast(this,loginBean.getMessage());
    }
}
