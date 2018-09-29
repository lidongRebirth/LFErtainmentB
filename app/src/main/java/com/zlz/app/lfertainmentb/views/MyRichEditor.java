package com.zlz.app.lfertainmentb.views;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;

import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.zlz.app.lfertainmentb.R;
import com.zlz.app.lfertainmentb.activitys.publishactivity.Content;
import com.zlz.app.lfertainmentb.mybaselibrary.utils.ScreenUtil;
import com.zlz.app.lfertainmentb.utils.StringUtil;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义原生富文本控件       可以显示图片文字等
 * 注：1.Glide需要有依赖  2.删除图片的弹框是依赖的项目的工具类  3.使用了NullUtils  4.使用Jsoup  5.使用键盘显示工具类
 * Created by WaterWood on 2018/2/25 0025.
 */

public class MyRichEditor extends LinearLayout {

    private Context context;
    private List<TextEntity> etList;//文本集合
    private List<ImageEntity> ivList;//图片集合
    private LinearLayout linearLayout;//scrollview中的线性布局
    private int width;
    private int height;
    private int ivNum;//Img拼接计数器
    private EditText editText;//插入分割线时获取到光标的控件
    private EditText editText0;//删除分割线时用到的焦点当前控件
    private EditText editTitle;//题目
    private EditText etAfterImg;//图片后面添加的输入框
    private ChangeWord changeWord;
    private LayoutParams paramsScroll;//Scrollview的布局管理器
    private ScrollView scrollView;
    private boolean isFirst;

    public MyRichEditor(Context context) {
        super(context);
        setOrientation(VERTICAL);
        this.context = context;
        etList = new ArrayList<>();
        ivList = new ArrayList<>();
        initLayout();
        insertEdit();
        etList.get(0).getEditText().setHint("请输入内容");
    }

    public MyRichEditor(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        this.context = context;
        etList = new ArrayList<>();
        ivList = new ArrayList<>();
        initLayout();
        insertEdit();
        etList.get(0).getEditText().setHint("请输入内容");
    }

    public MyRichEditor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        this.context = context;
        etList = new ArrayList<>();
        ivList = new ArrayList<>();
        initLayout();
        insertEdit();
        etList.get(0).getEditText().setHint("请输入内容");
    }

    /**
     * 插入图片
     */
    public void insertImg(final String url) {
        if (editTitle.hasFocus()) {
            etList.get(etList.size() - 1).getEditText().requestFocus();
        }
        editText = null;
        //这里更换成一个图片
        final ImageView imageView = new ImageView(context);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, ScreenUtil.instance(context).dip2px(175));
        imageView.setLayoutParams(params);
        final ImageEntity imageEntity = new ImageEntity();
        imageEntity.setImageView(imageView);
        imageEntity.setImgUrl("");
        //添加到对应的布局位置
        //确认当前光标的一个位置
        for (int i = 0; i < etList.size(); i++) {
            if (etList.get(i).getEditText().hasFocus()) {
                //如果当前控件获取到了焦点
                editText = etList.get(i).getEditText();//这个是当前操作的那个输入框控件
            }
        }
        for (int i = 2; i < linearLayout.getChildCount(); i++) {
            View view0 = linearLayout.getChildAt(i);
            if (view0 == editText) {
                linearLayout.addView(imageView, i + 1);
                //在图片之后，如果是图片，加一个EditText
                if (i + 1 < linearLayout.getChildCount()) {
                    View nextView = linearLayout.getChildAt(i + 2);
                    if (!(nextView instanceof EditText)) {
                        //这下一个是图片
                        etAfterImg = new EditText(context);
                        etAfterImg.setBackgroundColor(Color.WHITE);
                        etAfterImg.setTextSize(16);
                        addEditListen(etAfterImg);
                        TextEntity textEntity = new TextEntity();
                        textEntity.setEditText(etAfterImg);
                        textEntity.setBold(false);
                        textEntity.setItalic(false);
                        textEntity.setStrike(false);
                        //计算添加好的控件在什么位置添加到list中
                        for (int j = 0; j < etList.size(); j++) {
                            //循环找有焦点的那个输入框位置
                            if (etList.get(j).getEditText() == this.editText) {
                                etList.add(j + 1, textEntity);
                                break;
                            }
                        }
                        linearLayout.addView(etAfterImg, i + 2);
                    }
                }
                break;
            }
        }
        imageEntity.setImgUrl(url);
        ivList.add(imageEntity);
        //添加到对应的列表位置
        /*int count = 0;
        for (int i = 2; i < linearLayout.getChildCount(); i++) {
            View view = linearLayout.getChildAt(i);
            if (view instanceof ImageView) {
                //如果是一个图片
                ImageView img = (ImageView) view;
                if (ivList.size() == 0) {
                    ivList.add(imageEntity);
                } else {
                    //先确定有没有这个图
                    //如果有count加一循环下一遍
                    //如果没有，添加到count的位置
                    //如果count大于size，直接加在最后

                    //确定了列表里是不是有这个图片
                    boolean isHave = true;//true:有  false：没有
                    for (int j = 0; j < ivList.size(); j++) {
                        if (img != ivList.get(j).getImageView()) {
                            isHave = false;
                        }
                    }
                    if (isHave) {
                        //如果有
                        count++;
                        continue;
                    } else {
                        //如果没有
                        if (count > ivList.size()) {
                            ivList.add(imageEntity);
                        } else {
                            ivList.add(count, imageEntity);
                        }
                    }
                }
            }
        }*/
        //glide获取图片的宽高


        Glide.with(context)//强制Glide返回一个Bitmap对象
                .load(url)
//                .asBitmap()//强制Glide返回一个Bitmap对象
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable bitmap, Transition<? super Drawable> glideAnimation) {
//                        width = bitmap.getWidth();
//                        height = bitmap.getHeight();
                        width = bitmap.getIntrinsicWidth();
                        height = bitmap.getIntrinsicHeight();
                        //图片控件计划的高度
                        int screenHeight = height * getScreenWidth() / width;
                        //修改控件的宽高
                        LayoutParams params = (LayoutParams) imageView.getLayoutParams();
                        params.height = ScreenUtil.instance(context).dip2px(175);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        imageView.setLayoutParams(params);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        Glide.with(context).load(url).into(imageView);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (etAfterImg != null) {
                                    etAfterImg.requestFocus();
                                    InputMethodManager imm = (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                                    //visibleWord(true);
                                }
                            }
                        }, 100);
                        final ImageView iv = imageView;
                        imageView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //这里使用一个弹出框效果，如果这个自定义控件用在别的项目里要重写
                                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.dialogNoBg);
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.setCanceledOnTouchOutside(true);
                                View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_delete, null);
                                view.findViewById(R.id.tv_delete).setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        linearLayout.removeView(iv);
                                        for (int i = 0; i < ivList.size(); i++) {
                                            if (imageView == iv) {
                                                ivList.remove(iv);
                                                break;
                                            }
                                        }
                                        alertDialog.dismiss();
                                    }

                                });
                                alertDialog.setView(view);
                                alertDialog.show();
                            }
                        });
                    }

//                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
//
//                    }
                });
    }

    /**
     * 字体加粗
     */
    public void bold() {
        for (int i = 0; i < etList.size(); i++) {
            if (etList.get(i).getEditText().hasFocus()) {
                //如果当前控件获取到了焦点
                if (etList.get(i).isBold()) {
                    Typeface font = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL);
                    etList.get(i).getEditText().setTypeface(font);
                    etList.get(i).setBold(false);
                    etList.get(i).setItalic(false);
                } else {
                    etList.get(i).getEditText().getPaint().setFlags(0);
                    Typeface font = Typeface.create(Typeface.DEFAULT, Typeface.BOLD);
                    etList.get(i).getEditText().setTypeface(font);
                    etList.get(i).setBold(true);
                    etList.get(i).setItalic(false);
                    etList.get(i).setStrike(false);
                }
                break;
            }
        }
    }

    /**
     * 斜体
     */
    public void italic() {
        for (int i = 0; i < etList.size(); i++) {
            if (etList.get(i).getEditText().hasFocus()) {
                //如果当前控件获取到了焦点
                if (etList.get(i).isItalic()) {
                    Typeface font = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL);
                    etList.get(i).getEditText().setTypeface(font);
                    etList.get(i).setItalic(false);
                    etList.get(i).setBold(false);
                } else {
                    etList.get(i).getEditText().getPaint().setFlags(0);
                    Typeface font = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC);
                    etList.get(i).getEditText().setTypeface(font);
                    etList.get(i).setItalic(true);
                    etList.get(i).setBold(false);
                    etList.get(i).setStrike(false);
                }
                break;
            }
        }
    }

    /**
     * 中划线
     */
    public void strike() {
        for (int i = 0; i < etList.size(); i++) {
            if (etList.get(i).getEditText().hasFocus()) {
                //如果当前控件获取到了焦点
                if (etList.get(i).isStrike()) {
                    //取消中划线
                    etList.get(i).getEditText().getPaint().setFlags(0);
                    String str = etList.get(i).getEditText().getText().toString();
                    etList.get(i).getEditText().setText(str);
                    etList.get(i).getEditText().setSelection(etList.get(i).getEditText().getText().toString().length());
                    etList.get(i).setStrike(false);
                } else {
                    //设置中划线
                    Typeface font = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL);
                    etList.get(i).getEditText().setTypeface(font);
                    etList.get(i).setItalic(false);
                    etList.get(i).setBold(false);
                    etList.get(i).getEditText().getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    String str = etList.get(i).getEditText().getText().toString();
                    etList.get(i).getEditText().setText(str);
                    etList.get(i).getEditText().setSelection(etList.get(i).getEditText().getText().toString().length());
                    etList.get(i).setStrike(true);
                }
                break;
            }
        }
    }

    /**
     * 设置字号到24
     */
    public void h1() {
        for (int i = 0; i < etList.size(); i++) {
            if (etList.get(i).getEditText().hasFocus()) {
                //如果当前控件获取到了焦点
                etList.get(i).getEditText().setTextSize(28);
                etList.get(i).setSize(1);
                break;
            }
        }
    }

    /**
     * 设置字号到20
     */
    public void h2() {
        for (int i = 0; i < etList.size(); i++) {
            if (etList.get(i).getEditText().hasFocus()) {
                //如果当前控件获取到了焦点
                etList.get(i).getEditText().setTextSize(24);
                etList.get(i).setSize(2);
                break;
            }
        }
    }

    /**
     * 设置字号到16
     */
    public void h3() {
        for (int i = 0; i < etList.size(); i++) {
            if (etList.get(i).getEditText().hasFocus()) {
                //如果当前控件获取到了焦点
                etList.get(i).getEditText().setTextSize(20);
                etList.get(i).setSize(3);
                break;
            }
        }
    }

    /**
     * 插入文本
     */
    private void insertEdit() {
        //添加文字输入
        EditText editText = new EditText(context);
        editText.setBackgroundColor(Color.WHITE);
        editText.setTextSize(16);
        addEditListen(editText);
        TextEntity textEntity = new TextEntity();
        textEntity.setEditText(editText);
        textEntity.setBold(false);
        textEntity.setItalic(false);
        textEntity.setStrike(false);
        etList.add(textEntity);
        linearLayout.addView(editText, linearLayout.getChildCount() - 1);
        if (isFirst)
            editText.requestFocus();
        isFirst = true;
    }

    /**
     * 初始化布局的创建
     */
    private void initLayout() {
        //添加滑动控件
        scrollView = new ScrollView(context);
        paramsScroll = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(paramsScroll);
        scrollView.setVerticalScrollBarEnabled(false);
        addView(scrollView);

        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(VERTICAL);
        FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params1);
        scrollView.addView(linearLayout);

        editTitle = new EditText(context);
        editTitle.setTextSize(16);
        editTitle.setBackgroundColor(Color.WHITE);
        editTitle.setHint("请输入标题");
        editTitle.setMaxEms(30);
        linearLayout.addView(editTitle);

        View view = new View(context);
        LayoutParams params3 = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
        view.setLayoutParams(params3);
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.bg_line));
        linearLayout.addView(view);

        View seizeView = new View(context);
        LayoutParams params2 = new LayoutParams(LayoutParams.MATCH_PARENT, 600);
        seizeView.setLayoutParams(params2);
        seizeView.setBackgroundColor(Color.TRANSPARENT);
        linearLayout.addView(seizeView);
        seizeView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                etList.get(etList.size() - 1).getEditText().requestFocus();
                InputMethodManager imm = (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                //visibleWord(true);
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                editTitle.requestFocus();
            }
        }, 100);
    }

    /**
     * 文字实体类
     */
    public class TextEntity {
        private EditText editText;
        private boolean isBold;//记录是不是粗体
        private boolean isItalic;//记录是不是斜体
        private boolean isStrike;//记录是不是中划线
        private int size = 3;//字号,默认h3

        public EditText getEditText() {
            return editText;
        }

        public void setEditText(EditText editText) {
            this.editText = editText;
        }

        public boolean isBold() {
            return isBold;
        }

        public void setBold(boolean bold) {
            isBold = bold;
        }

        public boolean isItalic() {
            return isItalic;
        }

        public void setItalic(boolean italic) {
            isItalic = italic;
        }

        public boolean isStrike() {
            return isStrike;
        }

        public void setStrike(boolean strike) {
            isStrike = strike;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    /**
     * 图片实体类
     */
    public class ImageEntity {
        private ImageView imageView;
        private String imgUrl;

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }


    public List<Content> getData() {
        int size = ivList.size();
        List<Content> contents = new ArrayList<>();
        for (int i = 2; i < linearLayout.getChildCount() - 1; i++) {
            View view = linearLayout.getChildAt(i);
            if (view instanceof EditText) {
                EditText editText = (EditText) view;
                for (int j = 0; j < etList.size(); j++) {
                    if (etList.get(j).getEditText() == editText && !StringUtil.isEmpty(editText.getText().toString().trim())) {
                        Content content = new Content();
                        content.setType("0");
                        content.setContent(editText.getText().toString());
                        contents.add(content);
                        break;
                    }
                }
            } else if (view instanceof ImageView) {
                Content content = new Content();
                content.setType("1");
                content.setContent(ivList.get(ivNum).getImgUrl());
                contents.add(content);
                ivNum++;
            }
        }
        ivNum = 0;
        return contents;
    }

    /**
     * 获取任意文本的光标位置
     *
     * @return
     */
    public int getSelect() {
        for (int i = 0; i < etList.size(); i++) {
            if (etList.get(i).getEditText().hasFocus()) {
                //如果当前控件获取到了焦点
                return etList.get(i).getEditText().getSelectionStart();
            }
        }
        return -1;
    }

    /**
     * 确认光标是不是在最后
     *
     * @return
     */
    public boolean isLastSite() {
        for (int i = 0; i < etList.size(); i++) {
            if (etList.get(i).getEditText().hasFocus()) {
                //如果当前控件获取到了焦点
                return etList.get(i).getEditText().getSelectionStart() == etList.get(i).getEditText().getText().toString().length();
            }
        }
        return false;
    }

    /**
     * 插入分割线
     */
    public void insertHr() {
        editText = null;
        View view = new View(context);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
        params.setMargins(0, 5, 0, 5);
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.bg_line));
        view.setLayoutParams(params);
        //确认当前光标的一个位置
        for (int i = 0; i < etList.size(); i++) {
            if (etList.get(i).getEditText().hasFocus()) {
                //如果当前控件获取到了焦点
                editText = etList.get(i).getEditText();//这个是当前操作的那个输入框控件
            }
        }
        for (int i = 2; i < linearLayout.getChildCount(); i++) {
            View view0 = linearLayout.getChildAt(i);
            if (view0 == editText) {
                linearLayout.addView(view, i + 1);
                //在线之后，如果是图片，加一个EditText
                if (i + 1 < linearLayout.getChildCount()) {
                    View nextView = linearLayout.getChildAt(i + 2);
                    if (!(nextView instanceof EditText)) {
                        //这下一个是图片
                        EditText editText = new EditText(context);
                        editText.setBackgroundColor(Color.WHITE);
                        editText.setTextSize(20);
                        addEditListen(editText);
                        TextEntity textEntity = new TextEntity();
                        textEntity.setEditText(editText);
                        textEntity.setBold(false);
                        textEntity.setItalic(false);
                        textEntity.setStrike(false);
                        //计算添加好的控件在什么位置添加到list中
                        for (int j = 0; j < etList.size(); j++) {
                            //循环找有焦点的那个输入框位置
                            if (etList.get(j).getEditText() == this.editText) {
                                etList.add(j + 1, textEntity);
                                break;
                            }
                        }
                        linearLayout.addView(editText, i + 2);
                        editText.requestFocus();
                    }
                }
                break;
            }
        }
    }

    /**
     * 监听软件盘退格键
     *
     * @param editTextx
     */
    private void addEditListen(final EditText editTextx) {
        editTextx.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (getSelect() == 0) {
                        //光标位于最前面
                        for (int i = 0; i < etList.size(); i++) {
                            if (etList.get(i).getEditText().hasFocus()) {
                                editText0 = etList.get(i).getEditText();
                            }
                        }
                        for (int i = 2; i < linearLayout.getChildCount(); i++) {
                            View view = linearLayout.getChildAt(i);
                            if (view == editText0) {
                                //确定控件位置为i
                                if (i > 0) {
                                    //不是第一个控件
                                    View view0 = linearLayout.getChildAt(i - 1);
                                    if (!(view0 instanceof EditText) && !(view0 instanceof ImageView)) {
                                        //是分割线
                                        if (view0 != linearLayout.getChildAt(1)) {
                                            linearLayout.removeView(view0);
                                        } else {
                                            return false;
                                        }
                                    } else if (view0 instanceof EditText) {
                                        //是输入框
                                        EditText forEdit = (EditText) view0;
                                        String str = editText0.getText().toString();
                                        for (int j = 0; j < etList.size(); j++) {
                                            if (etList.get(j).getEditText() == forEdit) {
                                                forEdit.setText(forEdit.getText().toString() + str);
                                                forEdit.requestFocus();
                                                forEdit.setSelection(forEdit.getText().toString().length());
                                                etList.remove(editText0);
                                                linearLayout.removeView(editText0);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return true;
                    } else {
                        return false;
                    }
                } else if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    //回车键
                    if (isLastSite()) {
                        editText = null;
                        //新建一个文本框控件
                        EditText editText0 = new EditText(context);
                        editText0.setBackgroundColor(Color.WHITE);
                        editText0.setTextSize(16);
                        addEditListen(editText0);
                        TextEntity textEntity = new TextEntity();
                        textEntity.setEditText(editText0);
                        textEntity.setBold(false);
                        textEntity.setItalic(false);
                        textEntity.setStrike(false);
                        //检测当前所在的文本框是哪个
                        for (int i = 0; i < etList.size(); i++) {
                            if (etList.get(i).getEditText().hasFocus()) {
                                //如果当前控件获取到了焦点
                                editText = etList.get(i).getEditText();//这个是当前操作的那个输入框控件
                            }
                        }
                        //确认当前文本框在etList中的位置
                        for (int i = 0; i < etList.size(); i++) {
                            if (etList.get(i).getEditText() == editText) {
                                etList.add(i + 1, textEntity);
                            }
                        }
                        //确认当前文本框在LinearLayout中的位置
                        for (int i = 2; i < linearLayout.getChildCount(); i++) {
                            if (linearLayout.getChildAt(i) instanceof EditText) {
                                EditText et = (EditText) linearLayout.getChildAt(i);
                                if (et == editText) {
                                    linearLayout.addView(editText0, i + 1);
                                }
                            }
                        }
                        editText0.requestFocus();
                        return true;
                    } else {
                        return false;
                    }
                }
                return false;
            }
        });
        editTextx.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 获得焦点
                    //获取当前输入框封装对象
                    for (int i = 0; i < etList.size(); i++) {
                        if (editTextx == etList.get(i).getEditText()) {
                            //找到了获取焦点的对象
                            TextEntity textEntity = etList.get(i);
                            if (changeWord != null) {
                                changeWord.changeWordFace(textEntity.isBold, textEntity.isItalic, textEntity.isStrike, textEntity.size);
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * 解析html成原生内容
     */
    public void setHtml(String html) {
        Document document = Jsoup.parse(html);
        Elements elements = document.getAllElements();
        elements.remove(0);
        elements.remove(0);
        elements.remove(0);
        elements.remove(0);
        //elements中包含现在所有的页面元素，开始翻译成原生控件内容分成三种
        linearLayout.removeAllViews();//清空布局中所有控件
        //把人家题目和线再给放回去
        linearLayout.addView(editTitle);

        View view = new View(context);
        LayoutParams params3 = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
        view.setLayoutParams(params3);
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.bg_line));
        linearLayout.addView(view);

        View seizeView = new View(context);
        LayoutParams params2 = new LayoutParams(LayoutParams.MATCH_PARENT, 600);
        seizeView.setLayoutParams(params2);
        seizeView.setBackgroundColor(Color.TRANSPARENT);
        linearLayout.addView(seizeView);
        seizeView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                etList.get(etList.size() - 1).getEditText().requestFocus();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                editTitle.requestFocus();
            }
        }, 100);
        //循环处理每一个标签
        for (int i = 0; i < elements.size(); i++) {
            final Element element = elements.get(i);
            if (element.tag().getName().equals("p")) {
                if (!(element.childNodes().size() == 0)) {
                    if (element.childNodes().get(0) instanceof TextNode) {
                        //没有字体格式的时候就进来这里了
                        TextNode element1 = (TextNode) element.childNodes().get(0);
                        //创建文本框
                        EditText editText = new EditText(context);
                        editText.setBackgroundColor(Color.WHITE);
                        //获取到字体大小-----
                        String textSize = element1.parentNode().attributes().get("style");
                        textSize = textSize.replace("font-size: ", "");
                        textSize = textSize.replace("px", "");
                        int textSizeInt = Integer.parseInt(textSize);
                        editText.setTextSize(textSizeInt);
                        //-------------------------------------
                        addEditListen(editText);
                        TextEntity textEntity = new TextEntity();
                        textEntity.setEditText(editText);
                        textEntity.setBold(false);
                        textEntity.setItalic(false);
                        textEntity.setStrike(false);
                        etList.add(textEntity);
                        linearLayout.addView(editText, linearLayout.getChildCount() - 1);
                        editText.requestFocus();
                        editText.setText(element1.getWholeText());
                    } else {
                        //有字体格式的时候就进来这里了
                        Element element2 = (Element) element.childNodes().get(0);
                        //获取文字内容
                        if (!(element2.childNodes().size() == 0)) {
                            TextNode node = (TextNode) element2.childNodes().get(0);
                            String content = node.getWholeText();
                            //创建文本框
                            EditText editText = new EditText(context);
                            editText.setBackgroundColor(Color.WHITE);
                            addEditListen(editText);
                            TextEntity textEntity = new TextEntity();
                            textEntity.setEditText(editText);
                            textEntity.setBold(false);
                            textEntity.setItalic(false);
                            textEntity.setStrike(false);
                            etList.add(textEntity);
                            linearLayout.addView(editText, linearLayout.getChildCount() - 1);
                            editText.requestFocus();
                            if (element2.tag().getName().equals("b")) {
                                //字体是粗体
                                bold();
                            } else if (element2.tag().getName().equals("i")) {
                                //字体是斜体
                                italic();
                            } else if (element2.tag().getName().equals("strike")) {
                                //字体带中划线
                                strike();
                            }
                            editText.setText(content);
                            //获取到字体大小-----
                            String textSize = element2.parentNode().attributes().get("style");
                            textSize = textSize.replace("font-size: ", "");
                            textSize = textSize.replace("px", "");
                            int textSizeInt = Integer.parseInt(textSize);
                            editText.setTextSize(textSizeInt);
                            //-------------------------------------
                        } else {
                            insertEdit();
                            if (element2.tag().getName().equals("b")) {
                                //字体是粗体
                                bold();
                            } else if (element2.tag().getName().equals("i")) {
                                //字体是斜体
                                italic();
                            } else if (element2.tag().getName().equals("strike")) {
                                //字体带中划线
                                strike();
                            }
                        }
                    }
                } else {
                    insertEdit();
                }
            } else if (element.tag().getName().equals("img")) {
                //识别到图片添加
                final ImageView imageView = new ImageView(context);
                LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 600);
                imageView.setLayoutParams(params);
                ImageEntity imageEntity = new ImageEntity();
                imageEntity.setImageView(imageView);
                imageEntity.setImgUrl(element.attr("src"));
                ivList.add(imageEntity);
                linearLayout.addView(imageView, linearLayout.getChildCount() - 1);
                //glide获取图片的宽高
                Glide.with(context)
                        .load(element.attr("src"))
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(Drawable resource, Transition<? super Drawable> glideAnimation) {
//*                                width = resource.getWidth();
//*                                height = resource.getHeight();
                                width = resource.getIntrinsicWidth();
                                height = resource.getIntrinsicHeight();
                                //图片控件计划的高度
                                int screenHeight = height * getScreenWidth() / width;
                                //修改控件的宽高
                                LayoutParams params = (LayoutParams) imageView.getLayoutParams();
                                params.height = screenHeight;
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageView.setLayoutParams(params);
                                Glide.with(context).load(element.attr("src")).into(imageView);
                                final ImageView iv = imageView;
                                imageView.setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //这里使用一个弹出框效果，如果这个自定义控件用在别的项目里要重写
                                        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.dialogNoBg);
                                        final AlertDialog alertDialog = builder.create();
                                        alertDialog.setCanceledOnTouchOutside(true);
                                        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_delete, null);
                                        view.findViewById(R.id.tv_delete).setOnClickListener(new OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                linearLayout.removeView(iv);
                                                for (int i = 0; i < ivList.size(); i++) {
                                                    if (imageView == iv) {
                                                        ivList.remove(i);
                                                        break;
                                                    }
                                                }
                                                alertDialog.dismiss();
                                            }
                                        });
                                        alertDialog.setView(view);
                                        alertDialog.show();
                                    }
                                });
                            }

//                            @Override
//                            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
//
//                            }
                        });
            } else if (element.tag().getName().equals("hr")) {
                //识别到分割线添加
                View view0 = new View(context);
                LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
                params.setMargins(0, 5, 0, 5);
                view0.setBackgroundColor(ContextCompat.getColor(context, R.color.bg_line));
                view0.setLayoutParams(params);
                linearLayout.addView(view0, linearLayout.getChildCount() - 1);
            }
        }
    }

    /**
     * 获取题目的内容
     *
     * @return
     */
    public String getTitle() {
        return editTitle.getText().toString();
    }

    /**
     * 获取到富文本标题控件
     *
     * @return
     */
    public EditText getEditText() {
        return editTitle;
    }

    /**
     * 设置标题内容
     */
    public void setTitle(String title) {
        editTitle.setText(title);
    }

    /**
     * 隐藏标题内容
     */
    public void hideTitle() {
        editTitle.setVisibility(GONE);
    }

    /**
     * 选择图片前获取到光标的位置
     *
     * @return
     */
    public int getFocusSite() {
        for (int i = 0; i < etList.size(); i++) {
            if (etList.get(i).getEditText().hasFocus()) {
                //如果当前控件获取到了焦点
                return i;
            }
        }
        return -1;
    }

    /**
     * 指定位置输入框获取到焦点
     *
     * @param site
     */
    public void requstFocus(int site) {
        etList.get(site).getEditText().requestFocus();
    }

    /**
     * 题目获取焦点
     */
    public void titleRequestFocus() {
        editTitle.requestFocus();
    }

    /**
     * 提供的外部调用接口
     */
    public interface ChangeWord {
        public void changeWordFace(boolean isBold, boolean isItalic, boolean isStrike, int size);
    }

    /**
     * 设置监听接口
     *
     * @param changeWord
     */
    public void setListen(ChangeWord changeWord) {
        this.changeWord = changeWord;
    }

    /**
     * 软键盘弹起后不要遮挡文字
     */
    public void visibleWord(boolean flag) {
        if (paramsScroll != null) {
            if (flag) {
                paramsScroll.bottomMargin = ScreenUtil.instance(context).dip2px(45);
            } else {
                paramsScroll.bottomMargin = 0;
            }
            scrollView.setLayoutParams(paramsScroll);
        }
    }
}