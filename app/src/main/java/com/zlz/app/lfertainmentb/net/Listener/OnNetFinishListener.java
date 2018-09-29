package com.zlz.app.lfertainmentb.net.Listener;

/**
 * 网络回调
 */
public interface OnNetFinishListener {
    void onSuccess(String response);
    void onFailed(String response);
}
