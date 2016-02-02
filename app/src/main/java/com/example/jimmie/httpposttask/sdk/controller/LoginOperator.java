package com.example.jimmie.httpposttask.sdk.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.jimmie.httpposttask.sdk.model.OnLoginFinishedListener;
import com.example.jimmie.httpposttask.sdk.model.LoginModel;

/**
 * Created by 4399-1126 on 2016/2/1.
 */
public class LoginOperator {
    private LoginModel model;
    private OnLoginFinishedListener listener;


    public void handleUserInfo(String result, Context context) {
        model.saveUserInfo(result, context);
    }

    public void login(Activity context, OnLoginFinishedListener listener) {
        this.listener = listener;
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public OnLoginFinishedListener getLoginFinishedListener() {
        return this.listener;
    }

    public void setModel(LoginModel model) {
        this.model = model;
    }
}
