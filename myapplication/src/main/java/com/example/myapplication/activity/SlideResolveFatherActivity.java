package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.widget.conflictWidget.ConflictFatherScrollView;

import java.util.ArrayList;
import java.util.List;

public class SlideResolveFatherActivity extends AppCompatActivity {
    private List<String> stringList = new ArrayList<>();
    //
    private ListView lvOneConflict;
    private ConflictFatherScrollView slideConflictOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_resolve_father);
        lvOneConflict = (ListView) findViewById(R.id.lvOneConflict);
        slideConflictOne = (ConflictFatherScrollView) findViewById(R.id.slideConflictOne);
        //将listView传进去
        slideConflictOne.setListView(lvOneConflict);
        
        initData();
        lvOneConflict.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,stringList));
    }
    private void initData() {
        for (int i = 0; i <50 ; i++)
        {
            stringList.add(String.valueOf("测试BottomSheetDialog" + i));
        }
    }
}
