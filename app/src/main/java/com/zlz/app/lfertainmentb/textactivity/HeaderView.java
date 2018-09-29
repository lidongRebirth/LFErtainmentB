package com.zlz.app.lfertainmentb.textactivity;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.zlz.app.lfertainmentb.R;

import java.text.SimpleDateFormat;

public class HeaderView extends LinearLayout implements RefreshHeader {
    TextView textView, tv_time;
    View tagImg;
    private long perRefreshTime;
    private SimpleDateFormat sdf;


    public HeaderView(Context context) {
        super(context);
        initView(context);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public HeaderView(Context context, int color) {
        super(context);
        initView(context);
        LinearLayout ll_header = findViewById(R.id.ll_header);
        ll_header.setBackgroundColor(context.getColor(R.color.colorPrimary));
    }

    private void initView(Context context) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_header_normal, this, true);
        textView = (TextView) findViewById(R.id.text);
        tagImg = findViewById(R.id.tag);
        tv_time = (TextView) findViewById(R.id.tv_time);
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    }

    public HeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.initView(context);
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.net_dialog_anim);
        tagImg.startAnimation(animation);

    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        tagImg.clearAnimation();
        if (success) {
            textView.setText("刷新完成");
        } else {
            textView.setText("刷新失败");
        }
        return 0;//延迟500毫秒之后再弹回
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
                tagImg.clearAnimation();

            case PullDownToRefresh:
                String timeStr = sdf.format(perRefreshTime == 0 ? System.currentTimeMillis() : perRefreshTime);
                tv_time.setText(timeStr);
                break;
            case Refreshing:
                textView.setText("正在刷新");
                perRefreshTime = System.currentTimeMillis();
                break;
            case ReleaseToRefresh:
                textView.setText("松开刷新");

                break;
        }
    }
}
