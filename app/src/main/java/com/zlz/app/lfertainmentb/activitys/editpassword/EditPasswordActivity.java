package com.zlz.app.lfertainmentb.activitys.editpassword;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.zlz.app.lfertainmentb.R;
import com.zlz.app.lfertainmentb.activitys.editpassword.model.EditPassWordM;
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

public class EditPasswordActivity extends BaseActivity implements TimeUtil.TimeMesListener, OnNetFinishListener {


    @BindView(R.id.btn_code)
    Button btn_code;
    @BindView(R.id.iv_clear_tel)
    ImageView iv_clear_tel;
    @BindView(R.id.iv_clear_code)
    ImageView iv_clear_code;
    @BindView(R.id.iv_clear_password)
    ImageView iv_clear_password;
    @BindView(R.id.iv_clear_password2)
    ImageView iv_clear_password2;


    @BindView(R.id.et_telnum)
    EditText et_telnum;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.et_newpw)
    EditText et_newpw;
    @BindView(R.id.et_newpw2)
    EditText et_newpw2;

    @BindView(R.id.btn_commit)  //提交
            Button btn_commit;


    private long getCodeTime;               //验证码获取时间
    private TimeUtil timeUtil;
    private CodeModel codeModel;            //获取验证码
    private EditPassWordM editPassWordM;    //提交更改

    private String tel;                     //电话
    private String onepassword;             //密码1
    private String twopassword;             //密码2
    private String smscode;                 //验证码


    @Override
    protected int setContentView() {
        return R.layout.activity_editpassword;
    }

    @Override
    protected void initView() {
        et_telnum.addTextChangedListener(textWatcher);
        et_code.addTextChangedListener(textWatcher);
        et_newpw.addTextChangedListener(textWatcher);
        et_newpw2.addTextChangedListener(textWatcher);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        timeUtil = new TimeUtil();
        codeModel = new CodeModel(this);
        editPassWordM = new EditPassWordM(this, (OnNetFinishListener) this);

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
        btn_code.setText("获取验证码");
    }
    //*--

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            iv_clear_tel.setVisibility(StringUtil.isEmpty(et_telnum.getText().toString().trim()) ? View.GONE : View.VISIBLE);
            iv_clear_code.setVisibility(StringUtil.isEmpty(et_code.getText().toString().trim()) ? View.GONE : View.VISIBLE);
            iv_clear_password.setVisibility(StringUtil.isEmpty(et_newpw.getText().toString().trim()) ? View.GONE : View.VISIBLE);
            iv_clear_password2.setVisibility(StringUtil.isEmpty(et_newpw2.getText().toString().trim()) ? View.GONE : View.VISIBLE);

            tel = et_telnum.getText().toString();
            onepassword = et_newpw.getText().toString();
            twopassword = et_newpw2.getText().toString();
            smscode = et_code.getText().toString();
        }
    };

    @OnClick({R.id.iv_clear_tel, R.id.iv_clear_code, R.id.iv_clear_password, R.id.iv_clear_password2, R.id.btn_code, R.id.btn_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_clear_tel:
                et_telnum.setText("");
                break;
            case R.id.iv_clear_code:
                et_code.setText("");
                break;
            case R.id.iv_clear_password:
                et_newpw.setText("");
                break;
            case R.id.iv_clear_password2:
                et_newpw2.setText("");
                break;
            case R.id.btn_code:                                         //获取验证码
                if (StringUtil.isEmpty(tel)) {
                    ToastUtil.showToast(this, "请填写手机号");
                } else if (!StringUtil.isMobile(tel)) {
                    ToastUtil.showToast(this, "请填写正确手机号");
                } else {
                    if (getCodeTime == 0 || System.currentTimeMillis() - getCodeTime >= 60000) {
                        timeUtil.excuse(1000, 60000, this);
                        getCodeTime = System.currentTimeMillis();
                        codeModel.getCode(tel);     //获取验证码
                    } else {
                        ToastUtil.showToast(this, "请在一分钟之后进行操作");
                    }
                }
                break;
            case R.id.btn_commit:       //提交
                if (StringUtil.isEmpty(tel)) {
                    ToastUtil.showToast(this, "请输入手机号");
                } else if (StringUtil.isEmpty(smscode)) {
                    ToastUtil.showToast(this, "请输入验证码");
                } else if (StringUtil.isEmpty(onepassword)) {
                    ToastUtil.showToast(this, "请输入密码");
                } else if (StringUtil.isEmpty(twopassword)) {
                    ToastUtil.showToast(this, "请再次输入密码");
                } else {
                    editPassWordM.commit(tel, onepassword, twopassword, smscode);//提交更改
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timeUtil.onDestory();
    }

    //提交回调
    @Override
    public void onSuccess(String response) {
        Gson gson = new Gson();
        CommonBean commonBean = gson.fromJson(response, CommonBean.class);
        ToastUtil.showToast(this, commonBean.getMessage());
        if(commonBean.getStatus().equals("200")){
            finish();
        }

    }

    @Override
    public void onFailed(String response) {
        Gson gson = new Gson();
        CommonBean commonBean = gson.fromJson(response, CommonBean.class);
        ToastUtil.showToast(this, commonBean.getMessage());
    }
}
