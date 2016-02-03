package com.example.jimmie.httpposttask.sdk.model;

import android.graphics.Bitmap;
import android.webkit.WebView;

/**
 * Created by 4399-1126 on 2016/2/3.
 */
public interface OnWebViewDataListener {
    void onPageStarted(WebView view, String url, Bitmap favicon);

    void onPageFinished(WebView view, String url);

    void onResultReturn(String result);
}
