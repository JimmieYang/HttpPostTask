package com.example.jimmie.httpposttask.sdk.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jimmie.httpposttask.sdk.constance.Const;
import com.example.jimmie.httpposttask.sdk.entity.PostEntiy;
import com.example.jimmie.httpposttask.sdk.entity.User;
import com.example.jimmie.httpposttask.sdk.utils.DeviceUtil;
import com.example.jimmie.httpposttask.sdk.utils.JsonUtil;
import com.example.jimmie.httpposttask.sdk.utils.PreferencesUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 4399-1126 on 2016/2/1.
 */
public class LoginModel {

    OnWebViewDataListener mListener;

    public User getUserInfo(String result) {
        String userInfo = JsonUtil.getJsonResult(result);
        if (userInfo != null && !userInfo.equals("")) {
            User user = new User(userInfo);
            return user;
        }
        return null;
    }

    public void saveUserInfo(String result, Context context) {
        String userInfo = JsonUtil.getJsonResult(result);
        if (userInfo != null && !userInfo.equals("")) {
            PreferencesUtil.saveUserInfo(context, PreferencesUtil.USER, userInfo);
            User user = new User(userInfo);
            PreferencesUtil.saveNames(context, user.getUsername());
        }
    }

    public void getLoginUrl(final Context context, final OnLoadLoginUrlListener listener) {
        RequestQueue mQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.POST, Const.HTTP_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String result = JsonUtil.getJsonResult(response);
                        listener.onSucceed(result);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailed(error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                PostEntiy entiy = LoginModel.this.getPostParams(context);
                params.put(Const.STATE, entiy.getState());
                params.put(Const.DEVICE, entiy.getDevice());
                params.put(Const.TOPBAR, entiy.getTopbar());
                params.put(Const.USERNAMES, entiy.getUsernames());
                return params;
            }
        };
        request.setRetryPolicy( // 设置网络超时和重试参数
                new DefaultRetryPolicy(
                        10000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,//默认最大尝试次数
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );
        mQueue.add(request);
    }

    private PostEntiy getPostParams(Context context) {
        PostEntiy entity = new PostEntiy();
        entity.setUsernames(PreferencesUtil.getUserInfo(context, PreferencesUtil.USER_NAMES) + "");
        entity.setDevice(new DeviceUtil(context).getDeviceParams());
        return entity;
    }

    public void setWebViewDataConfig(WebView view, OnWebViewDataListener listener) {
        this.mListener = listener;
        view.setWebViewClient(new MyWebviewClient());
        view.addJavascriptInterface(new InJavaScriptObj(), Const.JAVESCRIP_METHOD_NAME);
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
                mListener.onResultReturn(result);
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
                mListener.onPageStarted(view, url, favicon);
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
                mListener.onPageFinished(view, url);
            }
        }

    }
}
