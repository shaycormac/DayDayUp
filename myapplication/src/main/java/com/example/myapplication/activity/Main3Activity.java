package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;

public class Main3Activity extends AppCompatActivity
{

    private LinearLayout llContainer;
    private Button btnAdd;
    private Button btnRemove;
    private AppCompatActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        LayoutInflater.from(this).inflate(R.layout.activity_main3, null);
       // LayoutInflaterCompat.setFactory();
       //LayoutInflaterFactory
        activity = this;
        btnAdd = (Button) findViewById(R.id.btnAddPropertityAnimation);
        
        
       btnRemove =   (Button) findViewById(R.id.btnRemovePropertityAnimation);

        llContainer = (LinearLayout) findViewById(R.id.llContainer);
        bindListener();
    }

    private void bindListener() {
        
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = new TextView(activity);
                textView.setText("哈哈");
                llContainer.addView(textView);
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               llContainer.removeAllViews();
            }
        });
    }
}
