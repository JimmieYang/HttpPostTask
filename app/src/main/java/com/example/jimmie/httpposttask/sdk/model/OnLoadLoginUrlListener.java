package com.example.jimmie.httpposttask.sdk.model;

/**
 * Created by 4399-1126 on 2016/2/2.
 */
public interface OnLoadLoginUrlListener {
    void onSucceed(String url);

    void onFailed(String err);
}
