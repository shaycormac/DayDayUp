package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.utils.StatusBarUtil;

/**
 * 测试hierarchy view 树型图
 */
public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView textView = (TextView) findViewById(R.id.tvAndroid2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this, AnimationActivity.class));
            }
        });
        View llLayout=findViewById(R.id.activity_main3);
       ImageView imageView = (ImageView) findViewById(R.id.imgStatusBar);
        StatusBarUtil.setTranslucentForImageView(this,llLayout);
    }
}
