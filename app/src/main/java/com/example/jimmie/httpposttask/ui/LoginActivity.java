package com.example.jimmie.httpposttask.ui;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.example.jimmie.httpposttask.R;
import com.example.jimmie.httpposttask.model.control.LoginModelImpl;
import com.example.jimmie.httpposttask.model.interfaces.LoginModel;
import com.example.jimmie.httpposttask.model.interfaces.OnLoadLoginViewListener;
import com.example.jimmie.httpposttask.model.interfaces.OnLoadWebViewListener;

/**
 * Created by 4399-1126 on 2016/1/26.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private WebView mWebView;
    private ProgressBar mPb;
    private Button mBtnBack;
    private LoginModel mLoginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        init();
    }

    private void init() {
        mLoginModel = new LoginModelImpl();
        mWebView = (WebView) findViewById(R.id.webview);
        mPb = (ProgressBar) findViewById(R.id.pb);
        mBtnBack = (Button) findViewById(R.id.web_back_btn);
        mBtnBack.setOnClickListener(this);

        mLoginModel.loadLoginView(this, new OnLoadLoginViewListener() {
            @Override
            public void onSucceed(String url) {
                mLoginModel.loadWebView(mWebView, url, new MyOnLoadWebListener());
            }
            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        if (mWebView != null && mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            this.finish();
        }
    }


    class MyOnLoadWebListener implements OnLoadWebViewListener {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            mPb.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url,boolean isLogin) {
            mPb.setVisibility(View.GONE);
        }

        @Override
        public void onDataGeted(String result) {
            System.out.println(result);
        }
    }

}
