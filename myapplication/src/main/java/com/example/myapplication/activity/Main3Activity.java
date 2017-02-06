package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.utils.StatusBarUtil;
import com.example.myapplication.widget.niceSpinner.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 测试hierarchy view 树型图
 */
public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView textView = (TextView) findViewById(R.id.tvAndroid2);
        TextView tvAndroid1 = (TextView) findViewById(R.id.tvAndroid1);
        TextView tvAnimationDrawable= (TextView) findViewById(R.id.tvAnimationDrawable);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this, AnimationActivity.class));
            }
        });
        View llLayout=findViewById(R.id.activity_main3);
       ImageView imageView = (ImageView) findViewById(R.id.imgStatusBar);
        StatusBarUtil.setTranslucentForImageView(this,llLayout);
        NiceSpinner niceSpinner = (NiceSpinner) findViewById(R.id.niceSpinner);
        List<String> dataset = new LinkedList<>(Arrays.asList("OneOneOneOneOneOneOneOneOneOneOneOneOne", "Tw", "ThreeOneOneOneOneOne", "FourOne", "Five"));
        niceSpinner.attachDataSource(dataset);
        niceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() 
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) 
            {
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        niceSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
            {
                Toast.makeText(getApplicationContext(),"得到的位置："+position,Toast.LENGTH_SHORT).show();
            }
        });
        tvAndroid1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),MyActivity.class));
            }
        });
        tvAnimationDrawable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),ShowProgressActivity.class));
            }
        });
    }
}
