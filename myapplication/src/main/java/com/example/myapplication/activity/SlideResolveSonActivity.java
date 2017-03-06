package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.example.myapplication.R;
import com.example.myapplication.widget.conflictWidget.ConflictSonListView;
import com.example.myapplication.widget.conflictWidget.ConflictSonScrollView;

import java.util.ArrayList;
import java.util.List;

public class SlideResolveSonActivity extends AppCompatActivity {
    private List<String> stringList = new ArrayList<>();
    //
    private ConflictSonListView lvOneConflict;
    private ConflictSonScrollView slideConflictOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_resolve_son);
        lvOneConflict = (ConflictSonListView) findViewById(R.id.lvOneConflict);
        //将父容器传进去
        slideConflictOne = (ConflictSonScrollView) findViewById(R.id.slideConflictOne);
        lvOneConflict.setConflictSonView(slideConflictOne);

        initData();
        lvOneConflict.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, stringList));
    }
    
    private void initData() 
    {
        for (int i = 0; i <50 ; i++)
        {
            stringList.add(String.valueOf("测试BottomSheetDialog" + i));
        }
    }
}
