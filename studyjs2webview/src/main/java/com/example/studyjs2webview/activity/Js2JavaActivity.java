package com.example.studyjs2webview.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.DatePicker;

import com.example.studyjs2webview.R;

import java.util.Calendar;

public class Js2JavaActivity extends AppCompatActivity {

    private WebView webView;
    //设置一些websetting
    private WebSettings settings;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js2_java);
        webView = (WebView) findViewById(R.id.webView);
        calendar = Calendar.getInstance();
        initWebview();
       // webView.loadUrl("file:///android_asset/diaglog.html");
        
    }

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    private void initWebview() {
        settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(this, "jk");
    }

    @JavascriptInterface
    public void showDatePicker()
    {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
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
