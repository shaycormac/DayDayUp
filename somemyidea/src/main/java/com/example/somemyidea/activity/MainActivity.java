package com.example.somemyidea.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ToggleButton;

import com.example.somemyidea.R;
import com.example.somemyidea.utils.StringBufferUtils;

public class MainActivity extends AppCompatActivity {
    private Button btnTotast;
    private Button btnIntentService;
    private ToggleButton toggleButton;
    private CheckedTextView checkedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTotast = (Button) findViewById(R.id.btnTotast);
        btnIntentService = (Button) findViewById(R.id.btnIntentService);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        checkedTextView = (CheckedTextView) findViewById(R.id.checkedTextView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, StringBufferUtils.contactObjectToString("我是",10," 哈哈",20.5,true,20f), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bindListener();
    }

    private void bindListener()
    {
        btnTotast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ToastActivity.class));
            }
        });
        
        btnIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TestIntentServiceActivity.class));
            }
        });
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SingleItemRecycleViewActivity.class));  
            }
        });
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ViewStubTestActivity.class));
            }
        });
    }

}
