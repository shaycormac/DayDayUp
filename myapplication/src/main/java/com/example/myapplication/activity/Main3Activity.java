package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.example.myapplication.R;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        LayoutInflater.from(this).inflate(R.layout.activity_main3, null);
       // LayoutInflaterCompat.setFactory();
       //LayoutInflaterFactory
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
       // startActivityForResult();
    }
}
