package com.zlz.app.lfertainmentb.mybaselibrary.utils;


import android.os.Handler;
import android.os.Message;

/**
 * Created by DELL on 2017/7/9.
 */

public class TimeUtil {
    private final int MWHAT = 0x12345678;
    private long shutTime;
    private long allTime;
    private long cishu;
    private TimeMesListener listener;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MWHAT){
                if (allTime != -1){
                    cishu++;
                    long shengtime = allTime - cishu * shutTime;
                    if (shengtime <= 0){
                        listener.timeFinishListener();

                    }else {
                        listener.timeMesListener(allTime,shengtime,cishu);
                        sendEmptyMessageDelayed(MWHAT,shutTime);
                    }

                }
            }
        }
    };

    /*public static TimeUtil getInstanc(){
        if (instance == null)
            instance = new TimeUtil();
        return instance;
    }*/

    public void excuse(long shutTime,TimeMesListener listener){
        excuse(shutTime,-1,listener);
    }

    public void excuse(long shutTime,long allTime,TimeMesListener listener){
        handler.removeMessages(MWHAT);
        this.shutTime = shutTime;
        this.listener = listener;
        this.allTime = allTime;
        this.cishu = 0;

        handler.sendEmptyMessageDelayed(MWHAT,shutTime);
        listener.timeStartListener(allTime);
    }

    public void pause(){
        handler.removeMessages(MWHAT);
    }

    public void onDestory(){
        handler.removeMessages(MWHAT);
    }


    public interface TimeMesListener{
        public void timeMesListener(long allTime, long shengTime, long cishu);
        public void timeStartListener(long allTime);
        public void timeFinishListener();
    }

    public static long getMiaoLong(){
        return 1000;
    }

    public static long getFenLong(){
        return 60 * getMiaoLong();
    }

    public static long getHourLong(){
        return 60 * getFenLong();
    }

    public static long getDayLong(){
        return 24 * getHourLong();
    }
}
