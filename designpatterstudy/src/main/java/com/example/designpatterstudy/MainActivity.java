package com.example.designpatterstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.example.designpatterstudy.abstractFactoryDesign.InternetRequestCallback;
import com.example.designpatterstudy.abstractFactoryDesign.InternetRequestManager;
import com.example.designpatterstudy.abstractFactoryDesign.RequestFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView tvShowCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //测试请求
        tvShowCallback = (TextView) findViewById(R.id.tvShowCallback);
        tvShowCallback.setMovementMethod(ScrollingMovementMethod.getInstance());
        String url = "https://api.douban.com/v2/movie/top250";
        InternetRequestManager requestManager = RequestFactory.getInstance();
        requestManager.get(url, new InternetRequestCallback() {
            @Override
            public void onSuccess(String response)
            {
                Log.d(TAG, "onSuccess: " + response);
                tvShowCallback.setText(response);
            }

            @Override
            public void onFailture(Throwable throwable)
            {
                throwable.printStackTrace();
            }

            @Override
            public void onResponse(String response, Throwable throwable) {

            }
        });
    }
}
