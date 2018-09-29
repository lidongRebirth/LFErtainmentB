package com.zlz.app.lfertainmentb.activitys.publishactivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zlz.app.lfertainmentb.R;
import com.zlz.app.lfertainmentb.mybaselibrary.BaseActivity;
import com.zlz.app.lfertainmentb.utils.StringUtil;
import com.zlz.app.lfertainmentb.mybaselibrary.utils.ToastUtil;
import com.zlz.app.lfertainmentb.utils.PhotoSelUtil;
import com.zlz.app.lfertainmentb.views.MyRichEditor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zlz.app.lfertainmentb.utils.PhotoSelUtil.PHOTO_REQUEST_RESTURE;

public class PublishActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_middle)
    TextView tv_middle;
    @BindView(R.id.tv_right)
    TextView tv_right;

    @BindView(R.id.rel_cover)       //封面布局
    RelativeLayout rel_cover;
    @BindView(R.id.iv_cover_icon)   //头像
    ImageView iv_cover_icon;
    @BindView(R.id.et_address)      //地址
    EditText et_address;
    @BindView(R.id.et_persons)      //人数
    EditText et_persons;
    @BindView(R.id.et_sponsor)      //主办方
    EditText et_sponsor;
    @BindView(R.id.et_title)        //标题
    EditText et_title;
    @BindView(R.id.myricheditor)    //富文本内容
    MyRichEditor myricheditor;



    private PhotoSelUtil photoSelUtil;





    @Override
    protected int setContentView() {
        return R.layout.activity_publish;
    }

    @Override
    protected void initView() {
        tv_middle.setText("发布活动");
        tv_right.setText("发布");
        myricheditor.hideTitle();

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        photoSelUtil = new PhotoSelUtil(this, 9, MimeType.ofAll());

    }

    @OnClick({R.id.iv_back, R.id.rel_cover,R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rel_cover:            //封面图
                ToastUtil.showToast(this,"封面图");
                photoSelUtil.setSize(1);
                photoSelUtil.setMimeType(MimeType.ofImage());
                photoSelUtil.sel();
                break;
            case R.id.tv_right:             //发布
                String addressInfoStr = et_address.getText().toString().trim();     //地址
                String personsStr = et_persons.getText().toString().trim();         //人数
                String sponsorStr = et_sponsor.getText().toString().trim();         //主办方
                String titleStr = et_title.getText().toString().trim();             //标题
                if(StringUtil.isEmpty(addressInfoStr)){
                    ToastUtil.showToast(this, "请输入活动详细地址");
                } else if (StringUtil.isEmpty(personsStr)) {
                    ToastUtil.showToast(this, "请输入报名人数");
                } else if(0 == Integer.parseInt(personsStr)){
                    ToastUtil.showToast(this, "报名人数应大于0");
                }else if (StringUtil.isEmpty(sponsorStr)) {
                    ToastUtil.showToast(this, "请输入主办方");
                } else if (StringUtil.isEmpty(titleStr)) {
                    ToastUtil.showToast(this, "请输入活动标题");
                }else if (myricheditor.getData() == null || myricheditor.getData().size() == 0) {
                    ToastUtil.showToast(this, "请输入活动内容");
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            if (requestCode == PHOTO_REQUEST_RESTURE) {//选择照片处理      //*通过photoutil选择完图片的回调      此处少了将图片展示出来
                if (data == null) return;
                List<Uri> mSelected = Matisse.obtainResult(data);
                List<String> paths = new ArrayList<>();
                for (Uri uri : mSelected) {
                    paths.add(StringUtil.getRealFilePath(this, uri));
                }
                Glide.with(this).load(paths.get(0)).into(iv_cover_icon);
//                loadAccessory(paths);               //*压缩图片并上传
            }
        }






    }
}
