package com.zlz.app.lfertainmentb;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;


import com.umeng.analytics.MobclickAgent;
import com.zlz.app.lfertainmentb.mybaselibrary.utils.ScreenUtil;
import com.zlz.app.lfertainmentb.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：高业
 * 时间：2018/1/31
 * 类描述： Application
 * 修改人：
 * 修改时间：2018/1/31
 * 修改备注：
 */
public class App extends Application {
    //activity列表
    private List<Activity> activities;
    private static App instance;
    //版本
    private String versionName;
    //版本号
    private int versionCode;
    //activity显示的个数，用来判断APP是否在前台显示
    public static int resumeCount;
    //是否是debug模式
    private boolean isDebug = false;
    //屏幕宽度
    private int sWidth;
    //屏幕高度
    private int sHeight;
   public int getsWidth() {
        return sWidth;
    }

    public int getsHeight() {
        return sHeight;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setVersionName() {
        if (versionName == null) {
            versionName = AppUtil.getAppVersionName(this);
        }
    }

    public void setVersionCode() {
        if (versionCode == 0) {
            versionCode = AppUtil.getVersionCode(this);
        }
    }

    public String getVersionName() {
        return versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public static synchronized App getInstance() {
        return instance;
    }

    public List<Activity> getActivitieList() {
        return activities;
    }


    //退出所有的activity
    public void destoryActivity(Class<? extends Activity> clz) {
        for (int i = activities.size() - 1; i >= 0; i--) {
            Activity activity = activities.get(i);
            if (activity != null) {
                if (activity.getClass() == clz) {
                    activity.finish();
                    return;
                }
            }
        }
    }

    //除了某个activity外，退出其他所有
    public void destoryOther(Class<? extends Activity> clz) {
        for (int i = activities.size() - 1; i >= 0; i--) {
            Activity activity = activities.get(i);
            if (activity != null) {
                if (activity.getClass() != clz) {
                    activity.finish();
                    return;
                }
            }
        }
    }

    /*public void loginOut(Activity thisActivity){
        Intent intent = new Intent(thisActivity,LoginActivity.class);
        thisActivity.startActivity(intent);
        for (int i = activities.size() - 1;i >= 0;i--){
            Activity activity = activities.get(i);
            if (activity != null){
                if (activity.getClass() != LoginActivity.class){
                    activity.finish();
                }
            }
        }
    }*/

    /*public void destoryNotSplash(){
        for (int i = activities.size() - 1;i >= 0;i--){
            Activity activity = activities.get(i);
            if (activity != null){
                if (!activity.getClass().getName().equals(SplashActivity.class.getName())){
                    activity.finish();
                }
            }
        }
    }*/

    //退出
    public void exit() {
        for (int i = activities.size() - 1; i >= 0; i--) {
            Activity activity = activities.get(i);
            if (activity != null) {
                activity.finish();
            }
        }
    }


    //判断某个activity是否存在
    public boolean isActivityRun(String activityName) {
        if (activityName == null)
            return false;
        for (Activity activity : App.getInstance().getActivitieList()) {
            if (activityName.equals(activity.getClass().getName())) {
                return true;
            }
        }
        return false;
    }

    //获取主线程的Looper
    public Looper getMainThreadLooper() {
        return getMainLooper();
    }


    @Override
    public void onCreate() {
        super.onCreate();
//        if (isDebug()){
//            Debug.startMethodTracing("zhangfangyuan");
//        }
        //SDKInitializer.initialize(this);
        instance = this;
        /*Intent intent = new Intent(this, PlamLiveService.class);
        startService(intent);*/
        activities = new ArrayList<>();

        //MobSDK.init(this);
        setVersionName();
        setVersionCode();
        sWidth = ScreenUtil.getScreenWidth(instance);
        sHeight = ScreenUtil.getScreenHeight(instance);
        registerActivityLifecycleCallbacks(cb);
    }


    /**
     * 保存到本地偏好
     * @param list 圈子uuid集合
     */
    /*private static void preference(List<String> list){
        SharedPreferences mySharedPreferences= instance.getSharedPreferences("scenelist", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = mySharedPreferences.edit();
        try {
            String liststr = PreferenceList.SceneList2String(list);
            edit.putString("list",liststr);
            edit.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getPreferenceCiruid(){
        SharedPreferences sharedPreferences= instance.getSharedPreferences ("scenelist", Context.MODE_PRIVATE);
        String liststr = sharedPreferences.getString("list", "");
        try {
            if(!"".equals(liststr)){
                List<String> showSceneList = PreferenceList.String2SceneList(liststr);
                return showSceneList;
            }
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }*/



    //activity生命周期的回调
    private ActivityLifecycleCallbacks cb = new ActivityLifecycleCallbacks(){


        @Override
        public void onActivityCreated(final Activity activity, Bundle savedInstanceState) {
            getActivitieList().add(activity);

        }

        @Override
        public void onActivityStarted(Activity activity){

        }

        @Override
        public void onActivityResumed(final Activity activity) {
            MobclickAgent.onResume(activity);
            resumeCount++;
        }

        @Override
        public void onActivityPaused(final Activity activity){
            MobclickAgent.onPause(activity);
            resumeCount--;
        }

        @Override
        public void onActivityStopped(Activity activity){

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            getActivitieList().remove(activity);
        }
    };




    //重启APP
    public void restartApp(){
        //重启app代码
        Intent intent = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
