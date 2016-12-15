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
import android.view.View;

import com.example.somemyidea.R;
import com.example.somemyidea.entity.ChatMessage;
import com.example.somemyidea.utils.StringBufferUtils;
import com.example.somemyidea.widget.recycleviewadapter.MultiItemTypeSupport;
import com.example.somemyidea.widget.recycleviewadapter.SectionAdapter;
import com.example.somemyidea.widget.recycleviewadapter.SectionSupport;
import com.example.somemyidea.widget.recycleviewadapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SingleItemRecycleViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> strings = new ArrayList<>();
    private List<ChatMessage> chatMessageList = new ArrayList<>();

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
        recyclerView.setLayoutManager(gridLayoutManager);
        initArrays();
       /* recyclerView.setAdapter(new CommonAdapter<String>(this,R.layout.item_touch_helper_layout,strings) 
        {

            @Override
            public void convert(ViewHolder holder, String s) 
            {
                holder.setText(R.id.text, s);
                
            }
        });*/
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
        recyclerView.setAdapter(new SectionAdapter<ChatMessage>(this,chatMessageList,sectionSupport,R.layout.main_chat_from_msg) 
        {

            @Override
            public void convert(ViewHolder holder, ChatMessage chatMessage) 
            {
                holder.setText(R.id.chat_from_content, chatMessage.content);
                holder.setText(R.id.chat_from_name, chatMessage.name);
                holder.setImageResource(R.id.chat_from_icon, chatMessage.icon);
                
            }
        });
        
    }

    private void initArrays() {
        for (int i = 0; i < 50; i++)
        {
            strings.add(StringBufferUtils.contactObjectToString("炳华儿" + i));  
        }
    }

}
