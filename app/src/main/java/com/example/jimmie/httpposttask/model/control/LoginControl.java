package com.example.jimmie.httpposttask.model.control;

import android.app.Activity;
import android.content.Context;
import android.webkit.WebView;
import android.widget.Button;

import com.example.jimmie.httpposttask.model.interfaces.OnLoadLoginViewListener;
import com.example.jimmie.httpposttask.model.interfaces.OnLoadWebViewListener;
import com.example.jimmie.httpposttask.model.interfaces.OnLoginFinishedListener;

/**
 * Created by 4399-1126 on 2016/1/27.
 */
public interface LoginControl {
    void loadLoginView(Context context, OnLoadLoginViewListener listener);

    void loadWebView(WebView view, String url, OnLoadWebViewListener listener);

    void login(Activity context, OnLoginFinishedListener listener);

    void clickBackBtn(Button btn, WebView view, Context context);
}

