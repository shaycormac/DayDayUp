package com.example.studybehavior;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.example.studybehavior.adapter.RecycleViewAdapter;
import com.example.studybehavior.customerbehavior.ScaleDownShowBehavior;

import java.util.ArrayList;
import java.util.List;

public class ScaleDownShowBehaviorActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private Toolbar toolbar;

    private LinearLayout linearLayout;
    //设置字符串
    private List<String> stringList = new ArrayList<>();

    private boolean isInitializeFAB=false;

    private LinearLayoutManager linearLayoutManager;

    //添加隐藏的
    private BottomSheetBehavior bottomSheetBehavior;
    private boolean initialize = false;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!initialize) {
            initialize = true;
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_down_show_behavior);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewDown);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        linearLayout = (LinearLayout) findViewById(R.id.tab_layout);
        //获取behavior对象
        ScaleDownShowBehavior behavior = ScaleDownShowBehavior.from(fab);
        behavior.setOnStateChangedListener(onStateChangeListener);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        initData();
        RecycleViewAdapter adapter = new RecycleViewAdapter(this, stringList);
        recyclerView.setAdapter(adapter);
    }

    ScaleDownShowBehavior.OnStateChangeListener onStateChangeListener = new ScaleDownShowBehavior.OnStateChangeListener() {
        @Override
        public void onChange(boolean isShow) 
        {
            if (!isShow)
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            else
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        }
    };

    private void initData() {
        for (int i = 0; i <50 ; i++)
        {
            stringList.add(String.valueOf("测试BottomSheetDialog" + i));
        }
    }
}
