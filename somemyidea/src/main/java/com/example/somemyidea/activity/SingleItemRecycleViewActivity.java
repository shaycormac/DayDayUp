package com.example.somemyidea.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.View;

import com.example.somemyidea.R;
import com.example.somemyidea.entity.ChatMessage;
import com.example.somemyidea.utils.StringBufferUtils;
import com.example.somemyidea.widget.CustomDialog;
import com.example.somemyidea.widget.recycleviewadapter.CommonAdapter;
import com.example.somemyidea.widget.recycleviewadapter.MultiItemTypeSupport;
import com.example.somemyidea.widget.recycleviewadapter.SectionSupport;
import com.example.somemyidea.widget.recycleviewadapter.ViewHolder;
import com.example.somemyidea.widget.recycleviewitemtouch.ItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SingleItemRecycleViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> strings = new ArrayList<>();
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    //滑动，拖动
    private ItemTouchHelper itemTouchHelper;
    private CommonAdapter<String> commonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_recycle_view);
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
        MultiItemTypeSupport<ChatMessage> multiItemTypeSupport = new MultiItemTypeSupport<ChatMessage>() {
            @Override
            public int getLayoutId(int itemType) 
            {
                if (itemType==ChatMessage.RECIEVE_MSG)
                    return R.layout.main_chat_from_msg;
                else if (itemType==ChatMessage.SEND_MSG)
                    return R.layout.main_chat_send_msg;
                return 0;
            }

            @Override
            public int getItemViewType(int position, ChatMessage chatMessage) 
            {
                if (chatMessage.isComMeg)
                    return ChatMessage.RECIEVE_MSG;
                else 
                return ChatMessage.SEND_MSG;
            }
        };

        SectionSupport<ChatMessage> sectionSupport = new SectionSupport<ChatMessage>() {
            @Override
            public int sectionHeaderLayoutId() {
                return R.layout.item_section_title_layout;
            }

            @Override
            public int sectionTitleTextViewId() {
                return R.id.textTitle;
            }

            @Override
            public String getTitle(ChatMessage chatMessage) {
                return chatMessage.createDate;
            }
        };
        recyclerView = (RecyclerView) findViewById(R.id.singleRecycleView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        initArrays();
        commonAdapter = new CommonAdapter<String>(this, R.layout.item_touch_helper_layout, strings) {

            @Override
            public boolean onItemMove(int fromPosition, int toPosition) {

                //互换列表种指定位置的数据（之前需要改变list的数据位置，这个不需要么？？）
                Collections.swap(strings, fromPosition, toPosition);
                notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onItemDismiss(final int position)
            {
                new CustomDialog.Builder(SingleItemRecycleViewActivity.this).setMessage("确定要删除么？")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                strings.remove(position);
                                notifyItemRemoved(position);
                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) 
                    {
                        //todo bug已经删除鸟~
                        notifyItemChanged(position);
                    }
                }).createDialog().show();
                //伪代码
              /*  if ("需要删除")
                {
                    notifyItemRemoved(position);
                }else 
                {
                    notifyItemChanged(position);
                }*/
               
            }

            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setText(R.id.text, s);

            }
        };
        recyclerView.setAdapter(commonAdapter);
        // 创建ItemTouchHelper对象，然后调用attachToRecyclerView(RecyclerView) 方法
        ItemTouchHelperCallback itemTouchHelperCallback = new ItemTouchHelperCallback(commonAdapter);
        itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        chatMessageList.addAll(ChatMessage.MOCK_DATAS);
       /* recyclerView.setAdapter(new MultiItemCommonAdapter<ChatMessage>(this,chatMessageList,multiItemTypeSupport) 
        {

            @Override
            public void convert(ViewHolder holder, ChatMessage chatMessage) 
            {
                int UIShow = holder.getItemViewType();
                if (UIShow==ChatMessage.RECIEVE_MSG)
                {
                    holder.setText(R.id.chat_from_content, chatMessage.content);
                    holder.setText(R.id.chat_from_name, chatMessage.name);
                    holder.setImageResource(R.id.chat_from_icon, chatMessage.icon);
                    
                }else if (UIShow==ChatMessage.SEND_MSG)
                {
                    holder.setText(R.id.chat_send_content, chatMessage.content);
                    holder.setText(R.id.chat_send_name, chatMessage.name);
                    holder.setImageResource(R.id.chat_send_icon, chatMessage.icon);
                }
                
            }
        });*/
      /*  recyclerView.setAdapter(new SectionAdapter<ChatMessage>(this,chatMessageList,sectionSupport,R.layout.main_chat_from_msg) 
        {

            @Override
            public boolean onItemMove(int fromPosition, int toPosition) {
                return false;
            }

            @Override
            public void onItemDismiss(int position) {

            }

            @Override
            public void convert(ViewHolder holder, ChatMessage chatMessage) 
            {
                holder.setText(R.id.chat_from_content, chatMessage.content);
                holder.setText(R.id.chat_from_name, chatMessage.name);
                holder.setImageResource(R.id.chat_from_icon, chatMessage.icon);
                
            }
        });*/
        
    }

    private void initArrays() {
        for (int i = 0; i < 50; i++)
        {
            strings.add(StringBufferUtils.contactObjectToString("炳华儿" + i));  
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) 
    {
        if (keyCode==KeyEvent.KEYCODE_BACK)
        {
            //todo 阻止取消dialog
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
