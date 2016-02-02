package com.example.jimmie.httpposttask.app;

import android.app.Application;

import com.example.jimmie.httpposttask.sdk.controller.LoginOperator;

/**
 * Created by 4399-1126 on 2016/1/28.
 */
public class AppControl extends Application {
    private LoginOperator operator;

    @Override
    public void onCreate() {
        super.onCreate();

        operator = new LoginOperator();
    }

    public LoginOperator getLoginOperator() {
        return operator;
    }
}
