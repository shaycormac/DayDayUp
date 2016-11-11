package com.example.studybehavior;

import android.os.Bundle;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.studybehavior.adapter.RecycleViewAdapter;
import com.example.studybehavior.utils.AnimatorUtil;

import java.util.ArrayList;
import java.util.List;

public class ScaleUpShowBehaviorActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView fab;
    private Toolbar toolbar;
    //设置字符串
    private List<String> stringList = new ArrayList<>();

    private boolean isInitializeFAB=false;

    private LinearLayoutManager linearLayoutManager;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!isInitializeFAB)
        {
            isInitializeFAB = true;
            hideFAB();
        }
    }

    private void hideFAB() 
    {
        fab.postDelayed(new Runnable() {
            @Override
            public void run() {
                AnimatorUtil.scaleHide(fab, new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                        
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        fab.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                });
            }
        }, 500);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_up_show_behavior);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fab = (ImageView) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        initData();
        RecycleViewAdapter adapter = new RecycleViewAdapter(this, stringList);
        recyclerView.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutManager.scrollToPosition(0);
                fab.setVisibility(View.GONE);
            }
        });
    }

    private void initData() {
        for (int i = 0; i <50 ; i++)
        {
            stringList.add(String.valueOf("测试BottomSheetDialog" + i));
        }
    }
}
