package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;

public class MyActivity extends AppCompatActivity {

    private ImageView imgHaha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ImageView imgFullTest = (ImageView) findViewById(R.id.imgFullTest);
        imgFullTest.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        imgHaha = (ImageView) findViewById(R.id.imgHaha);
        imgFullTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) 
            {
                if (imgHaha.getVisibility()==View.GONE)
                {
                    imgHaha.setVisibility(View.VISIBLE);
                }else if (imgHaha.getVisibility()==View.VISIBLE)
                    imgHaha.setVisibility(View.GONE);
                
            }
        });
        imgHaha.setOnClickListener(new View.OnClickListener() 
        {
            @Override
            public void onClick(View v) 
            {
                startActivity(new Intent(getApplication(),ShowStatusActivity.class));
                Toast.makeText(getApplicationContext(), "呵呵", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
