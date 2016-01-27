package com.example.jimmie.httpposttask.model.interfaces;

import android.graphics.Bitmap;
import android.webkit.WebView;

/**
 * Created by 4399-1126 on 2016/1/27.
 */
public interface OnLoadWebViewListener {
    void onPageStarted(WebView view, String url, Bitmap favicon);

    void onPageFinished(WebView view, String url,boolean isLogin);

    void onDataGeted(String result);
}
