package com.example.jimmie.httpposttask.sdk.controller;


import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.jimmie.httpposttask.sdk.view.LoginView;

/**
 * Created by 4399-1126 on 2016/1/26.
 */
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginView view = new LoginView(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(view, params);
    }

}
