package com.example.studyjs2webview.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.DatePicker;

import com.example.studyjs2webview.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Js2JavaActivity extends AppCompatActivity {

    private WebView webView;
    //设置一些websetting
    private WebSettings settings;
    private Calendar calendar;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js2_java);
        webView = (WebView) findViewById(R.id.webView);
        calendar = Calendar.getInstance();
        initWebview();
        webView.loadUrl("file:///android_asset/jsToJava.html");
        webView.addJavascriptInterface(new JavaInterface(webView,this), "jk");
        //被玩坏的节奏。多玩几个runnable试试呗
        initRunnable();

        String ss = "范方方";
      //-  String sb = new String(ss.getBytes(),);
        //测试删除list集合中某一元素
        List<String> stringList = new ArrayList<>();
        initList(stringList);
        reMove(stringList);
    }

    private void reMove(List<String> stringList) 
    {
        int size = stringList.size();
        for (int i = 0; i < size; i++) 
        {
            if ("花花1".equals(stringList.get(i))) 
            {
                stringList.remove(i);
                break;
            }
        }
    }

    private void initList(List<String> stringList) 
    {
        for (int i = 0; i <10 ; i++) 
        {
            stringList.add(String.valueOf("花花" + i));
        }
    }

    private void initRunnable()
    {
        //逐个执行，那么在主线程的runnable就会堵塞线程
        RunnableB.run();
        RunnableA.run();
        RunnableC.run();
    }

    Runnable RunnableA = new Runnable() {
        @Override
        public void run()
        {
            System.out.println("你们看错频，我是A，我要执行了");

        }
    };
    Runnable RunnableB = new Runnable() {
        @Override
        public void run()
        {
            System.out.println("你们看错频，我是B，我要执行了");

        }
    };
    Runnable RunnableC = new Runnable() {
        @Override
        public void run()
        {
            System.out.println("你们看错频，我是C，我要执行了");

        }
    };
    

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    private void initWebview() {
        settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
       
    }

    
    
    
    class JavaInterface
    {
        private WebView webView;
        private Context context;
        private Calendar calendar;
        public JavaInterface(WebView webView,Context context)
        {
            this.webView = webView;
            this.context = context;
            calendar = Calendar.getInstance(Locale.CHINA);
        }
        @JavascriptInterface
        public void showDatePicker()
        {
            DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            webView.loadUrl("javascript:alert('success~')");
                        }
                    });
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        }
        
    }
}
