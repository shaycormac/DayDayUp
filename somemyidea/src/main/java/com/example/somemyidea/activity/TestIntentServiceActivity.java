package com.example.somemyidea.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.somemyidea.R;
import com.example.somemyidea.service.MyIntentService;

public class TestIntentServiceActivity extends AppCompatActivity {
    private Button btnTestService;
    private LinearLayout llInfoContainer;
    public static final String ACTION_RECIVER = "action_reciver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intent_service);
        btnTestService = (Button) findViewById(R.id.btnTestService);
        llInfoContainer = (LinearLayout) findViewById(R.id.llInfoContainer);
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
        btnTestService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });
        
        registerMyBrocastReciver();
    }

    private void registerMyBrocastReciver() 
    {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_RECIVER);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, intentFilter);
    }

    int i = 0;
    private void addTask()
    {
        //模拟操作路径
        String path = "/sdcard/imgs/" + (++i) + ".png";
        MyIntentService.startActionBaz(TestIntentServiceActivity.this,path);
        TextView textView = new TextView(TestIntentServiceActivity.this);
        textView.setText(path);
        textView.setTag(path);
        llInfoContainer.addView(textView);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) 
        {
            Toast.makeText(TestIntentServiceActivity.this,intent.getAction().toString(),Toast.LENGTH_SHORT).show();
            if (ACTION_RECIVER==(intent.getAction()))
            {
                if (!TextUtils.isEmpty(intent.getStringExtra("path"))) 
                {
                    TextView textView = (TextView) llInfoContainer.findViewWithTag(intent.getStringExtra("path"));
                    textView.setText(intent.getStringExtra("haha"));
                }
                
            }


        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }
}
