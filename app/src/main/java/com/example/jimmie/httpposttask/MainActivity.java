package com.example.jimmie.httpposttask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.jimmie.httpposttask.model.control.LoginControlImpl;
import com.example.jimmie.httpposttask.model.entity.User;
import com.example.jimmie.httpposttask.model.interfaces.LoginControl;
import com.example.jimmie.httpposttask.model.interfaces.OnLoginFinishedListener;

public class MainActivity extends Activity implements View.OnClickListener{
    private final String TAG = this.getClass().getSimpleName();

    // Lifecycle methods ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.post_btn).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        LoginControl control = new LoginControlImpl();
        control.login(this, new OnLoginFinishedListener() {
            @Override
            public void onLoginFinished(boolean isSuccessful, User user) {

            }
        });
    }
}

