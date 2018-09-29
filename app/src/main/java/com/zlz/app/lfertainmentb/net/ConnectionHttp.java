package com.zlz.app.lfertainmentb.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zlz.app.lfertainmentb.App;


/**
 * Created by Administrator on 2016/10/14.
 */
public class ConnectionHttp {
    public static boolean connection_Internet(Context context){
        try {
            // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();

                boolean available=info.isAvailable();
                // 获取网络连接管理的对象
                if (info != null && info.isConnected()) {
                    // 判断当前网络是否已经连接
                    if (info.getState() == NetworkInfo.State.CONNECTED) {

                        return true;
                    }
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;

    }
    //网络类型判断
    public static String HttpType(Context con){
        if (ConnectionHttp.connection_Internet(App.getInstance())) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) con
                    .getSystemService(con.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            int netWorkType =mNetworkInfo.getType();
            if(netWorkType== ConnectivityManager.TYPE_WIFI){
                String nettype ="WIFI";
                return nettype;
            }else if(netWorkType== ConnectivityManager.TYPE_MOBILE){
                String nettype ="3G";
                return nettype;
            }else{
                String nettype ="No";
                return nettype;
            }
        }
        return null;
    }

}
