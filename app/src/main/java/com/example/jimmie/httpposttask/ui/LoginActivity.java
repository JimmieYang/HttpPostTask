package com.example.jimmie.httpposttask.ui;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.jimmie.httpposttask.R;
import com.example.jimmie.httpposttask.app.AppControl;
import com.example.jimmie.httpposttask.model.control.LoginControl;
import com.example.jimmie.httpposttask.model.entity.User;
import com.example.jimmie.httpposttask.model.interfaces.OnLoadLoginViewListener;
import com.example.jimmie.httpposttask.model.interfaces.OnLoadWebViewListener;
import com.example.jimmie.httpposttask.utils.JsonUtil;
import com.example.jimmie.httpposttask.utils.PreferencesUtil;

/**
 * Created by 4399-1126 on 2016/1/26.
 */
public class LoginActivity extends Activity {

    private WebView mWebView;
    private ProgressBar mPb;
    private Button mBtnBack;
    LoginControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        init();
    }

    private void init() {
        mWebView = (WebView) findViewById(R.id.webview);
        mPb = (ProgressBar) findViewById(R.id.pb);
        mBtnBack = (Button) findViewById(R.id.web_back_btn);
        control = ((AppControl) getApplication()).getLoginModel();
        control.loadLoginView(this, new OnLoadLoginViewListener() {
            @Override
            public void onSucceed(String url) {
                control.loadWebView(mWebView, url, new MyOnLoadWebListener());
            }

            @Override
            public void onFailed(VolleyError error) {
                Toast.makeText(LoginActivity.this,"请检查网络...",Toast.LENGTH_SHORT).show();
                LoginActivity.this.finish();
            }
        });

        control.clickBackBtn(mBtnBack, mWebView, this);

    }

    class MyOnLoadWebListener implements OnLoadWebViewListener {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            mPb.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url, boolean isLogin) {
            mPb.setVisibility(View.GONE);
        }

        @Override
        public void onDataGeted(String result) {
            String userInfo = JsonUtil.getJsonResult(result);
            if (userInfo != null && !userInfo.equals("")) {
                PreferencesUtil.saveUserInfo(LoginActivity.this, PreferencesUtil.USER, userInfo);
                User user = new User(userInfo);
                PreferencesUtil.saveNames(LoginActivity.this, user.getUsername());
            }
            finish();
        }
    }


}
