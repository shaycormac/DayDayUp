package com.example.somemyidea.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.somemyidea.R;
import com.example.somemyidea.utils.StringBufferUtils;
import com.example.somemyidea.utils.ToastUtils;
import com.example.somemyidea.widget.recycleviewitemtouch.ItemTouchHelperAdapter;
import com.example.somemyidea.widget.recycleviewitemtouch.ItemTouchHelperCallback;
import com.example.somemyidea.widget.recycleviewitemtouch.ItemTouchHelperViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemTouchHelperActivity extends AppCompatActivity {
    private RecyclerView recycleItemTouch;
    //滑动，拖动
    private ItemTouchHelper itemTouchHelper;
    //准备数据
    private List<String> mItems = new ArrayList<>();
    private TouchHelperAdapter touchHelperAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_touch_helper);
        recycleItemTouch = (RecyclerView) findViewById(R.id.recycleItemTouch);
        initArrayList();
        //设置recycleview一些东西
        touchHelperAdapter = new TouchHelperAdapter();
        recycleItemTouch.setAdapter(touchHelperAdapter);
        recycleItemTouch.setLayoutManager(new LinearLayoutManager(this));
        //设置touchHelper
       // 创建ItemTouchHelper对象，然后调用attachToRecyclerView(RecyclerView) 方法
        ItemTouchHelperCallback itemTouchHelperCallback = new ItemTouchHelperCallback(touchHelperAdapter);
        itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recycleItemTouch);
        
        
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
    }

    private void initArrayList() {
        for (int i = 0; i <50 ; i++) 
        {
            mItems.add(StringBufferUtils.contactObjectToString("炳华儿", i));    
        }
    }


    //设置adapter
    class TouchHelperAdapter extends RecyclerView.Adapter<TouchHelperAdapter.TouchViewHolder> implements ItemTouchHelperAdapter
    {


        @Override
        public TouchViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_touch_helper_layout, parent, false);
            return new TouchViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final TouchViewHolder holder, int position) 
        {
            holder.textView.setText(mItems.get(position));
            holder.handleView.setOnTouchListener(new View.OnTouchListener() 
            {
                @Override
                public boolean onTouch(View v, MotionEvent event)
                {
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) 
                    {
                        itemTouchHelper.startDrag(holder);
                    }
                    return false;
                }
            });
        }
        @Override
        public int getItemCount() {
            return mItems.size();
        }

        //两个实现方法
        @Override
        public boolean onItemMove(int fromPosition, int toPosition) 
        {
            //互换列表种指定位置的数据（之前需要改变list的数据位置，这个不需要么？？）
            Collections.swap(mItems, fromPosition, toPosition);
            notifyItemMoved(fromPosition,toPosition);
            return true;
        }

        @Override
        public void onItemDismiss(int position)
        {
         //先把list数据清除，再清除列表的位置（网络数据请求之后再删除吧？？？）
            mItems.remove(position);
            notifyItemRemoved(position);
        }

        class TouchViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder
        {
            public TextView textView;

            public ImageView handleView;

            public TouchViewHolder(View itemView)
            {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.text);
                handleView = (ImageView) itemView.findViewById(R.id.handle);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.getInstance().showToast(getApplicationContext(), TextUtils.concat(""+getLayoutPosition()," =text= ",""+mItems.get(getLayoutPosition())));
                    }
                });
            }
            //一下两个位置为选择以及不选择的颜色改变，可以自己改变。
            @Override
            public void onItemSelected() 
            {
                itemView.setBackgroundColor(Color.LTGRAY);
            }

            @Override
            public void onItemClear() 
            {
                itemView.setBackgroundColor(0);
            }
        }
    }
}
