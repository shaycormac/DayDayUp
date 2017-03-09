package com.example.studyjs2webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/** 
 * User: 炳华儿(574583006@qq.com) 
 * Date: ${YEAR}-${MONTH}-${DAY} 
 * Time: ${HOUR}:${MINUTE} 
 * FIXME 学习简单的js和原生方法交互。 
 */  
public class MainActivity extends AppCompatActivity 
{
    

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnStudyJs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        findViewById(R.id.btnCardView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) 
            {
                
            }
        });
    }
}
