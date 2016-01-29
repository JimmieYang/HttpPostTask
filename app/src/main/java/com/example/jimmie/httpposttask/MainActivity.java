package com.example.jimmie.httpposttask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.jimmie.httpposttask.app.AppControl;
import com.example.jimmie.httpposttask.model.control.LoginControl;
import com.example.jimmie.httpposttask.model.entity.User;
import com.example.jimmie.httpposttask.model.interfaces.OnLoginFinishedListener;
import com.example.jimmie.httpposttask.utils.PreferencesUtil;

public class MainActivity extends Activity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();

    private ImageView imgView;
    private TextView textView;

    // Lifecycle methods ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.post_btn).setOnClickListener(this);
        imgView = (ImageView) findViewById(R.id.img);
        textView = (TextView) findViewById(R.id.text);
        String userInfo = PreferencesUtil.getUserInfo(this, PreferencesUtil.USER);
        if (userInfo != null && !"".equals(userInfo)) {
            showInfo(new User(userInfo));
        }

    }


    @Override
    public void onClick(View v) {
        LoginControl control = ((AppControl) getApplication()).getLoginModel();
        control.login(this, new OnLoginFinishedListener() {
            public void onLoginFinished(boolean isSuccessful, User user) {
                if (isSuccessful)
                    showInfo(user);
                else
                    Toast.makeText(MainActivity.this, "登录失败!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showInfo(User user) {
        RequestQueue mQueue = Volley.newRequestQueue(this);
        ImageRequest request = new ImageRequest(user.getAvatar_middle(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imgView.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imgView.setImageResource(R.mipmap.ic_launcher);
            }
        });

        mQueue.add(request);
        StringBuffer sb = new StringBuffer();
        sb.append(isNotEmpty(user.getUsername()) ? user.getUsername() + "\n" : null);
        sb.append(isNotEmpty(user.getNick()) ? user.getNick() + "\n" : null);
        sb.append(isNotEmpty(user.getBindedphone()) ? user.getBindedphone() : null);
        textView.setText(sb.toString());

    }

    private boolean isNotEmpty(String str) {
        if (str != null && !str.equals(""))
            return true;
        else
            return false;
    }
}

