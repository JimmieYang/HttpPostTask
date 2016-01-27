package com.example.jimmie.httpposttask.model.control;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jimmie.httpposttask.model.constance.Const;
import com.example.jimmie.httpposttask.model.entity.PostEntiy;
import com.example.jimmie.httpposttask.model.interfaces.LoginModel;
import com.example.jimmie.httpposttask.model.interfaces.OnLoadLoginViewListener;
import com.example.jimmie.httpposttask.model.interfaces.OnLoadWebViewListener;
import com.example.jimmie.httpposttask.utils.DeviceUtil;
import com.example.jimmie.httpposttask.utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 4399-1126 on 2016/1/27.
 */
public class LoginModelImpl implements LoginModel {
    private OnLoadLoginViewListener loadListener;
    private OnLoadWebViewListener webListener;

    public void loadLoginView(final Context context, OnLoadLoginViewListener listener) {
        this.loadListener = listener;

        RequestQueue mQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.POST, Const.HTTP_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String result = JsonUtil.getJsonResult(response);
                        loadListener.onSucceed(result);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadListener.onFailed(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                PostEntiy entiy = getPostEntity(context);
                params.put(Const.STATE, entiy.getState());
                params.put(Const.DEVICE, entiy.getDevice());
                params.put(Const.TOPBAR, entiy.getTopbar());
                params.put(Const.USERNAMES, entiy.getUsernames());
                return params;
            }
        };
        mQueue.add(request);
    }

    private PostEntiy getPostEntity(Context context) {
        PostEntiy entiy = new PostEntiy();
        entiy.setUsernames("yangqianjian,lakdsjfl,algjkafsjd");
        entiy.setDevice(new DeviceUtil(context).getDeviceParams());
        Log.d("dsf", new DeviceUtil(context).getDeviceParams());
        return entiy;
    }

    @Override
    public void loadWebView(WebView view, String url, OnLoadWebViewListener listener) {
        this.webListener = listener;
        setWebView(view,url);
    }

    /**
     * 对webView的相关设置
     *
     * @param url
     */
    private void setWebView(WebView view, String url) {
        // 嵌入JavaScript代码,获取webView的内容
        InJavaScriptObj obj = new InJavaScriptObj();
        view.getSettings().setJavaScriptEnabled(true);
        view.addJavascriptInterface(obj, Const.JAVESCRIP_METHOD_NAME);
        view.getSettings().setDomStorageEnabled(true);

        view.getSettings().setUseWideViewPort(true);
        view.getSettings().setLoadWithOverviewMode(true);
        view.getSettings().setSupportZoom(true);
        view.getSettings().setBuiltInZoomControls(true);

        view.loadUrl(url);
        view.setWebViewClient(new MyWebviewClient());
    }

    // Inner Classes ///////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取webView的内容,存入本地 (api >= 17 时需要加@JavascriptInterface)
     *
     * @author jimmie
     */
    final class InJavaScriptObj {
        @JavascriptInterface
        public void getBodyContent(String result) {
//            PreferencesUtil.saveUserInfo(LoginActivity.this, PreferencesUtil.USER_INFO, html);
            webListener.onDataGeted(result);
        }
    }

    /**
     * 辅助WebView处理各种通知与请求事件
     *
     * @author jimmie
     */
    class MyWebviewClient extends WebViewClient {
        private boolean isRedirected;// 对重定向进行处理,onPageFinished()方法被多次调用

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (!isRedirected) {
                webListener.onPageStarted(view,url,favicon);
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
            boolean isLogin = false;
            if (!isRedirected) {
                if (url.startsWith(Const.START_WITH_URL)) {
                    super.onPageFinished(view, url);
                    view.loadUrl(Const.JAVESCRIP_METHOD);
                    isLogin = true;
                }
                webListener.onPageFinished(view,url,isLogin);
            }
        }
    }
}
