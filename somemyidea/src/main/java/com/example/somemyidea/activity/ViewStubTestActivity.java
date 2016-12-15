package com.example.somemyidea.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Button;

import com.example.somemyidea.R;

public class ViewStubTestActivity extends AppCompatActivity {
    private Button button;
    private ViewStubCompat stubCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub_test);
        button = (Button) findViewById(R.id.btnViewStub);
        stubCompat = (ViewStubCompat) findViewById(R.id.viewStubV7);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) 
            {
                stubCompat.setVisibility(View.VISIBLE);
                View view = findViewById(R.id.inflatedId);
                if (stubCompat.getVisibility()==View.VISIBLE)
                {
                    //改变东西
                    AppCompatButton btnButton = (AppCompatButton) view.findViewById(R.id.btnButton);
                    btnButton.setText("改变值");
                }
            }
        });
    }

}
