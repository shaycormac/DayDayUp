package com.example.somemyidea.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.somemyidea.R;

public class LayoutTestActivity extends AppCompatActivity {
    View view1;
    View view2;
    TextView textView1;
    TextView textView2;
    ImageView imageView1;
    ImageView imageView2;

    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_test);
       /* view1 = findViewById(R.id.lay1);
        view2 = findViewById(R.id.lay2);
        textView1 = (TextView) view1.findViewById(R.id.tvTextView);
        textView2 = (TextView) view2.findViewById(R.id.tvTextView);
        imageView1 = (ImageView) view1.findViewById(R.id.ivImg);
        imageView2 = (ImageView) view2.findViewById(R.id.ivImg);
        textView1.setText("我是1");
        textView2.setText("我是2");*/
        textView = (TextView) findViewById(R.id.tvTextView);
        textView.setText("我是1");
        imageView = (ImageView) findViewById(R.id.ivImg);
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
     //   bindListener();
    }

  /*  private void bindListener()
    {
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LayoutTestActivity.this, "1被点击", Toast.LENGTH_SHORT).show();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LayoutTestActivity.this, "2被点击", Toast.LENGTH_SHORT).show();
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LayoutTestActivity.this, "1被点击", Toast.LENGTH_SHORT).show();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LayoutTestActivity.this, "2被点击", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

}
