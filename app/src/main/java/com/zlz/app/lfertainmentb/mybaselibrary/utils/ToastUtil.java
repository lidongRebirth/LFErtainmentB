package com.zlz.app.lfertainmentb.mybaselibrary.utils;

import android.content.Context;
import android.widget.Toast;



/**
 * Created by bbx on 2016/5/6.
 */
public class ToastUtil {
    private static Toast mToast;

    public static void showToast(Context context, String text, int duration) {
        if (mToast != null) {
            mToast.setText(text);
            mToast.setDuration(duration);
        } else {
            mToast = Toast.makeText(context,text, duration);
        }
        mToast.show();
    }

    public static void showToast(Context context, String message){
        showToast(context,message, Toast.LENGTH_SHORT);
    }

    /*public static void showToast(DatePickerDialog.OnDateSelectedListener onDateSelectedListener, String message){
        showToast(App.getInstance(),message);
    }*/
//*获取资源目录下的文件
//    public static void showToast(Context context, int strResure){
//        showToast(context,StringUtil.getResourceString(strResure), Toast.LENGTH_SHORT);
//    }

//    public static void showToast(int strResure){
//        showToast(App.getInstance(),strResure);
//    }

}
