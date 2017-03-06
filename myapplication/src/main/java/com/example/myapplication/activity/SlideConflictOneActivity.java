package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 解决同一个方向（垂直或者水平）的滑动冲突(RecycleView已经在内部解决好了和外套ScroolView的滑动冲突
 * listview就没有解决！！！这个例子就是明显的，往上滑动，ScroolView直接把listView的滑动给屏蔽了)
 * 同方向的滑动冲突,这里我们用一个竖直的ScrollView嵌套一个ListView做例子。首先看看没有解决滑动冲突的时候是咋样的
 * 我们看到只要ScrollView可以滑动，内部的ListView是不能滑动的。那我们现在来解决这个问题，
 * 同向滑动冲突和与不同向滑动冲突不一样，得根据实际的需求来确定拦截的规则。这里我们的需求是当ListView滑到顶部了，
 * 并且继续向下滑就让ScrollView拦截掉；当ListView滑到底部了，并且继续向下滑，就让ScrollView拦截掉，
 * 其余时候都交给ListView自身处理事件。
 */
public class SlideConflictOneActivity extends AppCompatActivity {
    //private RecyclerView recyclerView;
    //设置字符串
    private List<String> stringList = new ArrayList<>();
    //
    private ListView lvOneConflict;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_conflict_one);
        lvOneConflict = (ListView) findViewById(R.id.lvOneConflict);
        //recyclerView = (RecyclerView) findViewById(R.id.rcvOneConflict);
        //recyclerView.setHasFixedSize(true);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
        //BottomSheetDialogAdapter adapter = new BottomSheetDialogAdapter();
       // recyclerView.setAdapter(adapter);
        lvOneConflict.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,stringList));
    }


    private void initData() {
        for (int i = 0; i <50 ; i++)
        {
            stringList.add(String.valueOf("测试BottomSheetDialog" + i));
        }
    }


    class BottomSheetDialogAdapter extends RecyclerView.Adapter<BottomSheetDialogAdapter.BottomSheetDialogViewHolder>
    {


        @Override
        public BottomSheetDialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BottomSheetDialogViewHolder(LayoutInflater.from(SlideConflictOneActivity.this).inflate(R.layout.list_item_textview_layout,null,false));
        }

        @Override
        public void onBindViewHolder(BottomSheetDialogViewHolder holder, int position)
        {
            holder.tvBottomSheetDialog.setText(stringList.get(position));
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,150);
            holder.tvBottomSheetDialog.setLayoutParams(layoutParams);
        }

        @Override
        public int getItemCount() {
            return stringList.size();
        }

        class BottomSheetDialogViewHolder extends RecyclerView.ViewHolder
        {
            TextView tvBottomSheetDialog;

            public BottomSheetDialogViewHolder(View itemView)
            {
                super(itemView);
                tvBottomSheetDialog = (TextView) itemView.findViewById(R.id.tvBottomSheetDialog);
            }
        }
    }
    
}
