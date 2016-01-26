package com.example.jimmie.httpposttask;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.jimmie.httpposttask.fragment.InfoFragment;
import com.example.jimmie.httpposttask.fragment.WebViewFragment;
import com.example.jimmie.httpposttask.utils.PreferencesUtil;

public class MainActivity extends Activity {
    private final String TAG = this.getClass().getSimpleName();

    private InfoFragment info;
    private WebViewFragment webview;

    // Lifecycle methods ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    // Initialization ///////////////////////////////////////////////////////////////////////////////////////////////
    private void init() {
        String userInfo = PreferencesUtil.getJsonUserInfo(this, PreferencesUtil.USER_INFO);
        if (userInfo != null) {
            info = new InfoFragment();
            getFragmentManager().beginTransaction().replace(R.id.content, info).commit();
        } else {
            webview = new WebViewFragment();
            getFragmentManager().beginTransaction().replace(R.id.content, webview).commit();
        }
    }

    @Override
    public void onBackPressed() {

        if (webview != null && webview.getmWebView().canGoBack())
            webview.getmWebView().goBack();
        else
            super.onBackPressed();
    }
}
