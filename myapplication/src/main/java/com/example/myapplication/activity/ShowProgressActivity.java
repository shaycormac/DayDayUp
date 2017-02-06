package com.example.myapplication.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplication.R;

public class ShowProgressActivity extends AppCompatActivity {

    private LinearLayout llProgress;
    private ImageView imgProgress;
    private AnimationDrawable imgProgressDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_progress);
        llProgress = (LinearLayout) findViewById(R.id.llProgress);
        imgProgress = (ImageView) findViewById(R.id.imgProgress);
        showProgress();
    }
  //播放动画
    private void showProgress()
    {
        //得到播放动画
        imgProgressDrawable = (AnimationDrawable) imgProgress.getDrawable();
        if (!imgProgressDrawable.isRunning())
            imgProgressDrawable.start();
        
    }
}
