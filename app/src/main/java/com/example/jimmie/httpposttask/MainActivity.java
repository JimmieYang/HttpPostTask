package com.example.jimmie.httpposttask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.jimmie.httpposttask.ui.LoginActivity;

public class MainActivity extends Activity implements View.OnClickListener{
    private final String TAG = this.getClass().getSimpleName();
    Button mBtn;

    // Lifecycle methods ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    // Initialization ///////////////////////////////////////////////////////////////////////////////////////////////
    private void init() {
        mBtn = (Button) findViewById(R.id.post_btn);
    }

    // Interface methods //////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivityForResult(intent,100);
    }

}

