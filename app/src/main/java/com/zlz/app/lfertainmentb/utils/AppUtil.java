package com.zlz.app.lfertainmentb.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.TelephonyManager;

import com.zlz.app.lfertainmentb.App;

import java.io.File;
import java.util.UUID;

//获取APP信息的工具类
public class AppUtil {
	//获取设备信息
    /*public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();
            //json.put("设备mac地址", mac);

            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }

            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            }

            //json.put("设备id", device_id);
            //json.put("手机型号", android.os.Build.MODEL);
            json.put("version", getAppVersionName(context));
            json.put("system", android.os.Build.VERSION.RELEASE);

            return json.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
    
    
    //获取版本号
    public static int getVersionCode(Context context) {
        String versionName = "";
        int versioncode = 0;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versioncode = pi.versionCode;
        }
        catch (Exception e) {
            LogUtil.e("VersionInfo", "Exception", e);
        }
        return versioncode;
    }
    
    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        int versioncode;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        }
        catch (Exception e) {
            LogUtil.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }


    //拨打电话
    /*public static void takePhone(Activity context, TextView textView) {
        String phone = textView.getText().toString().trim();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.requestPermissions(new String[]{"android.permission.CALL_PHONE"}, 111);
        }
        //用intent启动拨打电话
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
        }
        context.startActivity(intent);
    }*/


    //防止连续点击的,此方法已不用
    private static long lastClickTime;
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 300) {

            return true;
        }
        lastClickTime = time;
        return false;
    }

    //获取uuid
    @SuppressLint("MissingPermission")
    public static String getUUID(Activity activity){
        try {
            final TelephonyManager tm = (TelephonyManager) activity.getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

            final String tmDevice, tmSerial, tmPhone, androidId;
            tmDevice = "" + tm.getDeviceId();
            tmSerial = "" + tm.getSimSerialNumber();
            androidId = "" + android.provider.Settings.Secure.getString(activity.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

            UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
            return deviceUuid.toString();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }


    //安装apk
    public static void installApk(Context context, File file) {
        //UserUtil.getInstance().setFirstOpen(context);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }



    //获取dcode
    public static String getDcode() {
            SharedPreferences sp = App.getInstance().getSharedPreferences("mappsp", Context.MODE_PRIVATE);
            return sp.getString("dcode","");
    }

    //设置dcode
    public static void setDcode(String pushToken) {
            SharedPreferences sp = App.getInstance().getSharedPreferences("mappsp", Context.MODE_PRIVATE);
            if (sp != null) {
                SharedPreferences.Editor editor = sp.edit();
                if (editor != null) {
                    editor.putString("dcode", StringUtil.getUIStr(pushToken));
                    editor.commit();
                }
            }
    }
}
