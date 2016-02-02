package com.example.jimmie.httpposttask.sdk.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.jimmie.httpposttask.R;
import com.example.jimmie.httpposttask.app.AppControl;
import com.example.jimmie.httpposttask.sdk.constance.Const;
import com.example.jimmie.httpposttask.sdk.entity.User;
import com.example.jimmie.httpposttask.sdk.model.OnLoginFinishedListener;
import com.example.jimmie.httpposttask.sdk.controller.LoginOperator;
import com.example.jimmie.httpposttask.sdk.model.LoginModel;
import com.example.jimmie.httpposttask.sdk.model.OnLoadLoginUrlListener;

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
        InJavaScriptObj obj = new InJavaScriptObj();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(obj, Const.JAVESCRIP_METHOD_NAME);
        webView.getSettings().setDomStorageEnabled(true);

        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);

        webView.loadUrl(url);
        webView.setWebViewClient(new MyWebviewClient());
    }


    /**
     * 获取webView的内容,存入本地 (api >= 17 时需要加@JavascriptInterface)
     *
     * @author jimmie
     */
    final class InJavaScriptObj {
        @JavascriptInterface
        public void getBodyContent(String result) {
            if (result != null && result != "" && result.startsWith("{")) {
                operator.handleUserInfo(result, context);
                User user = model.getUserInfo(result);
                listener.onLoginFinished(true, user);
                ((Activity) LoginView.this.context).finish();
            }

        }
    }

    /**
     * 辅助WebView处理各种通知与请求事件
     *
     * @author jimmie
     */
    class MyWebviewClient extends WebViewClient {
        private boolean isRedirected; // onPageFinished多次调用

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (!isRedirected) {
                mPb.setVisibility(View.VISIBLE);
            }
            isRedirected = false;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            isRedirected = true;
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (!isRedirected) {
                if (url.startsWith(Const.START_WITH_URL)) {
                    view.loadUrl(Const.JAVESCRIP_METHOD);
                }
                mPb.setVisibility(View.GONE);
            }
        }

    }
}
