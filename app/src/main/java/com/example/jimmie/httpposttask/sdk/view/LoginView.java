package com.example.jimmie.httpposttask.sdk.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.jimmie.httpposttask.R;
import com.example.jimmie.httpposttask.app.AppControl;
import com.example.jimmie.httpposttask.sdk.controller.LoginOperator;
import com.example.jimmie.httpposttask.sdk.entity.User;
import com.example.jimmie.httpposttask.sdk.model.LoginModel;
import com.example.jimmie.httpposttask.sdk.model.OnLoadLoginUrlListener;
import com.example.jimmie.httpposttask.sdk.model.OnLoginFinishedListener;
import com.example.jimmie.httpposttask.sdk.model.OnWebViewDataListener;

/**
 * Created by 4399-1126 on 2016/2/1.
 */
public class LoginView extends RelativeLayout {
    private Button backBtn;
    private WebView webView;
    private ProgressBar mPb;
    private Context context;

    private LoginModel model;
    private LoginOperator operator;
    private OnLoginFinishedListener listener;

    public LoginView(Context context) {
        super(context);
        initView(context);
        setModel();
    }

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        setModel();
    }

    private void initView(Context context) {
        this.context = context;
        View view = this.inflate(context, R.layout.login_layout, null);
        addView(view);
        backBtn = (Button) findViewById(R.id.web_back_btn);
        webView = (WebView) findViewById(R.id.webview);
        mPb = (ProgressBar) findViewById(R.id.pb);

        backBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView != null && webView.canGoBack()) {
                    webView.goBack();
                } else {
                    listener.onLoginFinished(false, null);
                    ((Activity) LoginView.this.context).finish();
                }
            }
        });
    }

    private void setModel() {
        model = new LoginModel();
        operator = ((AppControl) ((Activity) context).getApplication()).getLoginOperator();
        operator.setModel(model);
        listener = operator.getLoginFinishedListener();

        model.getLoginUrl(context, new OnLoadLoginUrlListener() {
            @Override
            public void onSucceed(String url) {
                setWebView(url);
            }

            @Override
            public void onFailed(String err) {
                Toast.makeText(context, "请检查网络...", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 对webView的相关设置
     *
     * @param url
     */
    private void setWebView(String url) {
        // 嵌入JavaScript代码,获取webView的内容
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);

        webView.loadUrl(url);
        operator.handleWebViewUrl(webView, new OnWebViewDataListener() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mPb.setVisibility(VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mPb.setVisibility(GONE);
            }

            @Override
            public void onResultReturn(String result) {
                operator.handleUserInfo(result, context);
                User user = model.getUserInfo(result);
                listener.onLoginFinished(true, user);
                ((Activity) LoginView.this.context).finish();
            }
        });
    }
}
