package com.zlz.app.lfertainmentb.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.zlz.app.lfertainmentb.App;
import com.zlz.app.lfertainmentb.bean.CheckVersionBean;
import com.zlz.app.lfertainmentb.bean.UserBean;


/**
 * 创建者：张磊
 * 时间：2018/2/6
 * 类描述：
 * 修改人：
 * 修改时间：2018/2/6
 * 修改备注：
 */
public class UserUtils {
    public static final String USERSP = "usersp";
    public static final String USER = "user";
    public static final String KEYBORDHEIGHT = "keybordheight";
    public static final String RESOUCEVERSION = "resourceversion";
    public static final String PUSHTOKEN = "pushtoken";
    public static final String ISFIRST = "isfirst";
    public static final String MSGINFO = "msginfo";
    public static final String ISFOCUEUPDATE = "isfocueupdate";
    public static final String UNREADMES = "unreadmes";
    public static final String ADDFRIENDMES = "addfriendmes";
    public static final String LOGINTEL = "logintel";

    public static void saveUser(UserBean userBean, Context context) {
        if (context != null) {
            SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
            if (sp != null) {
                SharedPreferences.Editor editor = sp.edit();
                if (editor != null) {
                    if (userBean == null) {
                        editor.putString(USER, "");
                        editor.commit();
                    } else {
                        Gson gson = new Gson();
                        editor.putString(USER, gson.toJson(userBean));
                        editor.commit();
                    }
                }
            }
        }
    }

    public static void setUser(UserBean userBean, Context context) {
        SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
        String userStr = sp.getString(USER, "");
        Gson gson = new Gson();
        UserBean currentUser = gson.fromJson(userStr, UserBean.class);
        /*userBean.setSessionId(currentUser.getSessionId());
        userBean.setToken(currentUser.getToken());*/
        saveUser(userBean, context);
    }

    public static UserBean getUser(Context context) {
        if (context != null) {
            SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
            String userStr = sp.getString(USER, "");
            if (StringUtil.isEmpty(userStr)) {
                return null;
            } else {
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(userStr, UserBean.class);
                return userBean;
            }
        } else {
            return null;
        }
    }


    //设置软键盘高度
    public static void setKeybordheight(Context context, int height) {
        SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (editor != null) {
            editor.putInt(KEYBORDHEIGHT, height);
            editor.commit();
        }
    }

    //获取软键盘高度 默认为607*屏幕高度/1280
    public static int getKeybordheight(Context context) {
        SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
        return sp.getInt(KEYBORDHEIGHT, App.getInstance().getsHeight() * 607 / 1280);
    }

    public static void setResouceversion(String version, Context context) {
        if (context != null) {
            SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
            if (sp != null) {
                SharedPreferences.Editor editor = sp.edit();
                if (editor != null) {
                    editor.putString(RESOUCEVERSION, version);
                    editor.commit();
                }
            }
        }
    }

    public static String getResouceversion(Context context) {
        if (context != null) {
            String version = null;
            SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
            version = sp.getString(RESOUCEVERSION, "");
            if (version == null) {
                version = "";
            }
            return version;
        } else {
            return "";
        }
    }

    public static String getPushToken(Context context) {
        if (context != null) {
            SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
            return sp.getString(PUSHTOKEN, "");
        } else {
            return "";
        }
    }

    public static void setPushToken(Context context, String pushToken) {
        if (context != null) {
            SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
            if (sp != null) {
                SharedPreferences.Editor editor = sp.edit();
                if (editor != null) {
                    editor.putString(PUSHTOKEN, StringUtil.getUIStr(pushToken));
                    editor.commit();
                }
            }
        }
    }

    public static boolean getIsFirstLoad(Context context) {
        SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
        return sp.getBoolean(ISFIRST, true);
    }


    public static void setIsFirstLoad(Context context, boolean isFirst) {
        if (context != null) {
            SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
            if (sp != null) {
                SharedPreferences.Editor editor = sp.edit();
                if (editor != null) {
                    editor.putBoolean(ISFIRST, isFirst);
                    editor.commit();
                }
            }
        }
    }

    public static boolean getMsgInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
        return sp.getBoolean(MSGINFO, false);
    }


    public static void setMsgInfo(Context context, boolean msginfo) {
        if (context != null) {
            SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
            if (sp != null) {
                SharedPreferences.Editor editor = sp.edit();
                if (editor != null) {
                    editor.putBoolean(MSGINFO, msginfo);
                    editor.commit();
                }
            }
        }
    }

        public static void setUpdateInfo(Context context, CheckVersionBean versionBean) {
        SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
        if (sp != null) {
            SharedPreferences.Editor editor = sp.edit();
            if (editor != null) {
                String info = new Gson().toJson(versionBean);
                editor.putString(ISFOCUEUPDATE + App.getInstance().getVersionCode(), info);
                editor.commit();
            }
        }
    }

    public static CheckVersionBean getUpdateInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
        String versionInfo = sp.getString(ISFOCUEUPDATE + App.getInstance().getVersionCode(), null);
        if (StringUtil.isEmpty(versionInfo)) {
            return null;
        } else {
            return new Gson().fromJson(versionInfo, CheckVersionBean.class);
        }
    }

    public static int getUnreadGroupMes(Context context) {
        SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
        return sp.getInt(UNREADMES, 0);
    }


    public static void setUnreadGroupMes(Context context, int unReadMes) {
        if (context != null) {
            SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
            if (sp != null) {
                SharedPreferences.Editor editor = sp.edit();
                if (editor != null) {
                    editor.putInt(UNREADMES, unReadMes);
                    editor.commit();
                }
            }
        }
    }

    public static int getAddFriendMes(Context context) {
        SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
        return sp.getInt(ADDFRIENDMES, 0);
    }


    public static void setAddFriendMes(Context context, int unMes) {
        if (context != null) {
            SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
            if (sp != null) {
                SharedPreferences.Editor editor = sp.edit();
                if (editor != null) {
                    editor.putInt(ADDFRIENDMES, unMes);
                    editor.commit();
                }
            }
        }
    }

    public static String getLoginTel(Context context) {
        SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
        return sp.getString(LOGINTEL, "");
    }


    public static void setLoginTel(Context context, String tel) {
        if (context != null) {
            SharedPreferences sp = context.getSharedPreferences(USERSP, Context.MODE_PRIVATE);
            if (sp != null) {
                SharedPreferences.Editor editor = sp.edit();
                if (editor != null) {
                    editor.putString(LOGINTEL, tel);
                    editor.commit();
                }
            }
        }
    }
}
