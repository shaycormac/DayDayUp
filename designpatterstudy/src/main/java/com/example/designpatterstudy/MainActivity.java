package com.example.designpatterstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.example.designpatterstudy.abstractFactoryDesign.InternetRequestCallback;
import com.example.designpatterstudy.abstractFactoryDesign.InternetRequestManager;
import com.example.designpatterstudy.abstractFactoryDesign.RequestFactory;
import com.example.designpatterstudy.widget.NumberAnimation;
import com.example.designpatterstudy.widget.RuningNumView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView tvShowCallback;
    private TextView tvStartAnimation;

    RuningNumView tx_num1, tx_num2, tx_num3;
    String score = "750";
    List<String> strings=null;


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

        tvStartAnimation = (TextView) findViewById(R.id.tvStartAnimation);
        NumberAnimation animation = new NumberAnimation(tvStartAnimation);
        animation.setNum(0,5);

        tx_num1 = (RuningNumView) findViewById(R.id.tx_num1);
        tx_num2 = (RuningNumView) findViewById(R.id.tx_num2);
        tx_num3 = (RuningNumView) findViewById(R.id.tx_num3);
        tx_num1.setDuration(4000).setTextAndDigit(score, 3);
        tx_num2.setDuration(4000).setTextAndDigit(score, 2);
        tx_num3.setDuration(4000).setTextAndDigit(score, 1);

        tx_num1.startAnimation();
        tx_num2.startAnimation();
        tx_num3.startAnimation();
        Log.d(TAG, "---------------------------------");
        //通过表达式截取字符串
        Log.d(TAG, TextUtils.split("  Hello world!  ", " ")[0]);
        //字符串拼接
        Log.d(TAG, TextUtils.concat("Hello", " ", "world!").toString());
        //判断是否为空字符串
        Log.d(TAG, TextUtils.isEmpty("Hello") + "");
        //判断是否只有数字（左右空格不算，数字中间有空格就不算只有数字）
        Log.d(TAG, TextUtils.isDigitsOnly("1 23 45 5") + "");
        //判断字符串是否相等
        Log.d(TAG, TextUtils.equals("Hello", "Hello") + "");
        //获取字符串的倒序
        Log.d(TAG, TextUtils.getReverse("Hello", 0, "Hello".length()).toString());
        //获取字符串的长度(去掉首位空格)
        Log.d(TAG, TextUtils.getTrimmedLength("Hello world!") + "");
        Log.d(TAG, TextUtils.getTrimmedLength("  Hello world!  ") + "");
        //获取html格式的字符串
        Log.d(TAG, TextUtils.htmlEncode("<html>\n" +
                "<body>\n" +
                "这是一个非常简单的HTML。\n" +
                "</body>\n" +
                "</html>"));
        //获取字符串中第一次出现子字符串的字符位置
        Log.d(TAG, TextUtils.indexOf("Hello world!", "Hello") + "");
        //截取字符串（左闭右开）
        Log.d(TAG, TextUtils.substring("Hello world!", 0, 5));
       
    }
}
