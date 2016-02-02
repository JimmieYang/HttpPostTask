package com.example.jimmie.httpposttask.sdk.model;

import com.example.jimmie.httpposttask.sdk.entity.User;

/**
 * Created by 4399-1126 on 2016/1/26.
 */
public interface OnLoginFinishedListener {
    void onLoginFinished(boolean isSuccessful,User user);
}
