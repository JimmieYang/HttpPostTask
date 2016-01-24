package com.example.jimmie.httpposttask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jimmie.httpposttask.utils.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private final String HTTP_URL = "http://m.4399api.com/openapi/oauth.html";
    private final String STATE = "state";
    private final String STATE_VALUE = "";
    private final String DEVICE = "device";
    private final String DEVICE_VALUE = "{\"DEVICE_IDENTIFIER\":\"222925287716791\"," +
            "\"DEVICE_MODEL\":\"TianTian\",\"PLATFORM_TYPE\":\"Android\"," +
            "\"SDK_VERSION\":\"2.7.0.2\",\"GAME_KEY\":\"40001\",\"CANAL_IDENTIFIER\":\"\"," +
            "\"DEBUG\":\"false\"}";
    private final String RESULT = "result";
    private TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (TextView) findViewById(R.id.view);
    }

    /**
     * 异步加载网络请求
     */
    private class PostTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... urls) {
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
        protected void onPostExecute(final String result) {
            if (result != null)
                view.setText(getJsonResult(result));
            else
                view.setText("出错了.....");
        }
    }

    /**
     * 从json结果中获取 result 字段
     *
     * @param jsonString JSON字段
     * @return result字段
     */
    private String getJsonResult(String jsonString) {
        JSONObject object = null;
        String result = null;
        try {
            object = new JSONObject(jsonString);

            result = (String) object.get(RESULT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void onClick(View view) {
        new PostTask().execute(HTTP_URL);
    }
}
