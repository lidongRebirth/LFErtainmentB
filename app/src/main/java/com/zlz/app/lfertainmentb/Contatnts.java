package com.zlz.app.lfertainmentb;

import android.os.Environment;



/**
 * Created by gaoye on 2018/1/30.
 * 常量类
 */

public class Contatnts {
    public static final String CODE = "UTF-8";
    public static final int RESULT_OK = -1;
    public static final String IDEA_IMAGE_CACHE = Environment.getExternalStorageDirectory().toString() + "/ideaCache/";
    public static final String TYPE = "type";
    public static final String DELETE_POSITIONS = "deletepositions";

    public static final String WXAPPID = "wx85c752c2b9fd1176";
    public static final String WXSECRET = "134bdb46f29e0d6877b9f24957341747";

    public static final int ACTIONCOMMENTTYPE = 3;
    public static final int DYNAMICCOMMENTTYPE = 2;  // 动态评论标注
    public static final int COMMENTREPLY = 1;
    public static final int COMMENTPRAISE = 1;
    public static final int ENTERPRISELIKE = 7;
    public static final String KEY_SEARCH_HISTORY_KEYWORD = "key_search_history_keyword";

    public static final int SDK_APPID = 1400094321;
    public static final int BANNER_TYPE = 1;//轮播图类型
    public static final int TAG_LOAD = 0;//加载更多
    public static final int TAG_REFRESH = 1;//下拉刷新

    public static final int ISSUEDYNAMICREQUEST = 10000;//下拉刷新
    public static final String CACHE_FRIEND = "CACHE_FRIEND";
    public static final String CACHE_ROOM = "CACHE_ROOM";
    public static final String CACHE_GROUP = "CACHE_GROUP";
    public static String identify = null;
    public static final String SHART_TRANSITION_NAIE="SHARE";
    public static final int PHOTO_REQUEST_RESTURE = 300;//选择照片回调码
    public static final int PHOTO_REQUEST_CROP = 301;//裁剪回调码


    public static final int GET_CODE = 1000;
}
