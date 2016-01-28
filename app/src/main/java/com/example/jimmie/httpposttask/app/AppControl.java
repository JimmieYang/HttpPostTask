package com.example.jimmie.httpposttask.app;

import android.app.Application;

import com.example.jimmie.httpposttask.model.control.LoginControlImpl;
import com.example.jimmie.httpposttask.model.control.LoginControl;

/**
 * Created by 4399-1126 on 2016/1/28.
 */
public class AppControl extends Application {
    private LoginControl loginModel;

    @Override
    public void onCreate() {
        super.onCreate();

        loginModel = new LoginControlImpl();
    }

    public LoginControl getLoginModel() {
        return loginModel;
    }
}
