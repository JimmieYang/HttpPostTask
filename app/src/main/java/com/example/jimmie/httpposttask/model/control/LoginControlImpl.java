package com.example.jimmie.httpposttask.model.control;

import android.app.Activity;
import android.content.Intent;

import com.example.jimmie.httpposttask.model.interfaces.LoginControl;
import com.example.jimmie.httpposttask.model.interfaces.OnLoginFinishedListener;
import com.example.jimmie.httpposttask.ui.LoginActivity;

/**
 * Created by 4399-1126 on 2016/1/27.
 */
public class LoginControlImpl implements LoginControl {
    private OnLoginFinishedListener finshedlLstener;

    @Override
    public void login(Activity context, OnLoginFinishedListener listener) {
        this.finshedlLstener = listener;
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivityForResult(intent, 100);
    }
}
