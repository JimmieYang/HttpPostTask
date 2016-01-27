package com.example.jimmie.httpposttask.model.interfaces;

import com.android.volley.VolleyError;

/**
 * Created by 4399-1126 on 2016/1/27.
 */
public interface OnLoadLoginViewListener {
    void onSucceed(String url);

    void onFailed(VolleyError error);
}
