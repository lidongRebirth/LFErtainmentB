package com.zlz.app.lfertainmentb.utils;

import android.util.Log;

import com.zlz.app.lfertainmentb.App;


/**
 * 创建者：高业
 * 时间：2018/1/31
 * 类描述：日志的工具类
 * 修改人：
 * 修改时间：2018/1/31
 * 修改备注：
 */
public class LogUtil {
	public static String TAG = "com.gy.baselibrary.utils.Log";

	public static void i(String str){
		i(TAG, str);
	}
	
	public static void i(String tag, String str){
		i(tag, str,null);
	}
	
	public static void i(String tag, String str, Throwable e){
		if (!App.getInstance().isDebug()) {
			return;
		}
		if (e == null) {
			Log.i(tag, str);
		}else {
			Log.i(tag, str,e);
		}
		
	}
	
	public static void e(String str){
		e(TAG, str);
	}
	
	public static void e(String tag, String str){
		e(tag, str,null);
	}
	
	public static void e(String tag, String str, Throwable e){
		if (!App.getInstance().isDebug()) {
			return;
		}
		if (e == null) {
			Log.e(tag, str);
		}else {
			Log.e(tag, str,e);
		}
	}



	public static void d(String str){
		d(TAG, str);
	}

	public static void d(String tag, String str){
		d(tag, str,null);
	}

	public static void d(String tag, String str, Throwable e){
		if (!App.getInstance().isDebug()) {
			return;
		}
		if (e == null) {
			Log.d(tag, str);
		}else {
			Log.d(tag, str,e);
		}
	}



	public static void w(String str){
		w(TAG, str);
	}

	public static void w(String tag, String str){
		w(tag, str,null);
	}

	public static void w(String tag, String str, Throwable e){
		if (!App.getInstance().isDebug()) {
			return;
		}
		if (e == null) {
			Log.w(tag, str);
		}else {
			Log.w(tag, str,e);
		}
	}

	public static void v(String str){
		v(TAG, str);
	}

	public static void v(String tag, String str){
		v(tag, str,null);
	}

	public static void v(String tag, String str, Throwable e){
		if (!App.getInstance().isDebug()) {
			return;
		}
		if (e == null) {
			Log.v(tag, str);
		}else {
			Log.v(tag, str,e);
		}
	}

	public static String getLogUtilsTag(Class<? extends Object> clazz) {
		return LogUtil.TAG + "." + clazz.getSimpleName();
	}
}
