package com.example.jimmie.httpposttask.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.jimmie.httpposttask.R;
import com.example.jimmie.httpposttask.utils.HttpUtil;
import com.example.jimmie.httpposttask.utils.JsonUtil;
import com.example.jimmie.httpposttask.utils.PreferencesUtil;

import java.util.HashMap;

/**
 * Created by jimmie on 2016/1/25.
 */
public class WebViewFragment extends Fragment implements View.OnClickListener {
    // 访问的URL
    private final String HTTP_URL = "http://m.4399api.com/openapi/oauth.html";
    // post提交的实体数据
    private final String STATE = "state";
    private final String STATE_VALUE = "";
    private final String DEVICE = "device";
    private final String DEVICE_VALUE = "{\"DEVICE_IDENTIFIER\":\"222925287716791\"," +
            "\"DEVICE_MODEL\":\"TianTian\",\"PLATFORM_TYPE\":\"Android\"," +
            "\"SDK_VERSION\":\"2.7.0.2\",\"GAME_KEY\":\"40001\",\"CANAL_IDENTIFIER\":\"\"," +
            "\"DEBUG\":\"false\"}";

    // 设置JavaScript中的调用方法名称
    private final String JAVESCRIP_METHOD_NAME = "onSumbit";
    // 设置JavaScript中的方法
    private final String JAVESCRIP_METHOD = "javascript:window.onSumbit.getBodyContent(document.body.innerHTML);";
    // 对比此URL,看是否登录成功
    private final String START_WITH_URL = "http://m.4399api.com/openapi/oauth-callback";

    private WebView mWebView;
    private Button mBtn;
    private ProgressBar pb;

    // Lifecycle methods ///////////////////////////////////////////////////////////////////////////////////////////
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webview_fragment, container, false);
        init(view);
        return view;
    }

    // Initialization ///////////////////////////////////////////////////////////////////////////////////////////////
    private void init(View view) {
        mWebView = (WebView) view.findViewById(R.id.webview);
        pb = (ProgressBar) view.findViewById(R.id.pb);
        mBtn = (Button) view.findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
    }

    // Interface methods /////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onClick(View v) {
        new PostTask().execute(HTTP_URL);
    }

    // Util methods /////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 对webView的相关设置
     *
     * @param url
     */
    private void setWebView(String url) {
        mWebView.setVisibility(View.VISIBLE);
        // 嵌入JavaScript代码,获取webView的内容
        InJavaScriptObj obj = new InJavaScriptObj();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(obj, JAVESCRIP_METHOD_NAME);
        mWebView.getSettings().setDomStorageEnabled(true);

        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);

        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new MyWebviewClient());
    }


    // Inner Classes ///////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 异步加载网络请求
     */
    private class PostTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... urls) {
            // 设置post实体参数
            HashMap<String, String> entity = new HashMap<>();
            entity.put(STATE, STATE_VALUE);
            entity.put(DEVICE, DEVICE_VALUE);
            try {
                // 获取请求结果
                return HttpUtil.doPost(urls[0], null, entity);
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            String resultUrl = null;
            if (result != null) {
                // 对返回进行json解析,获取登录URL
                resultUrl = JsonUtil.getJsonResult(result);
                mBtn.setVisibility(View.GONE);
                // 对URL用webview显示
                setWebView(resultUrl);
            }
        }
    }

    /**
     * 获取webView的内容,存入本地 (api >= 17 时需要加@JavascriptInterface)
     *
     * @author jimmie
     */
    final class InJavaScriptObj {
        @JavascriptInterface
        public void getBodyContent(String html) {
            PreferencesUtil.saveUserInfo(getActivity(), PreferencesUtil.USER_INFO, html);
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
                pb.setVisibility(View.VISIBLE);
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
                pb.setVisibility(View.GONE);
                if (url.startsWith(START_WITH_URL)) {
                    super.onPageFinished(view, url);
                    view.loadUrl(JAVESCRIP_METHOD);
                }
            }
        }
    }
}
