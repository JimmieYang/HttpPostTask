package com.example.jimmie.httpposttask.model.interfaces;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by 4399-1126 on 2016/1/27.
 */
public interface LoginModel {
    void loadLoginView(Context context, OnLoadLoginViewListener listener);

    void loadWebView(WebView view, String url, OnLoadWebViewListener listener);
}

