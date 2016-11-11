package com.example.studybehavior;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetBehaviorActivity extends AppCompatActivity {
    private Button btn_bottom_sheet_control;
    private BottomSheetBehavior mBottomSheetBehavior;
    private Toolbar toolbar;
    private LinearLayout tab_layout;
    //bottomSheetDialog
    private BottomSheetDialog bottomSheetDialog;
    //设置字符串
    private List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_behavior);
        btn_bottom_sheet_control = (Button) findViewById(R.id.btn_bottom_sheet_control);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tab_layout = (LinearLayout) findViewById(R.id.tab_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //获取这个behavior
        mBottomSheetBehavior = BottomSheetBehavior.from(tab_layout);
        //点击按钮
        btn_bottom_sheet_control.setOnClickListener(new View.OnClickListener() 
        {
            @Override
            public void onClick(View v)
            {
                //设置behavior的隐藏状态和显示状态
               /* if (mBottomSheetBehavior.getState()==BottomSheetBehavior.STATE_COLLAPSED)
                {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else if (mBottomSheetBehavior.getState()==BottomSheetBehavior.STATE_EXPANDED)
                {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }*/
                
                if (bottomSheetDialog!=null)
                {
                    if (bottomSheetDialog.isShowing())
                        bottomSheetDialog.dismiss();
                    else
                        bottomSheetDialog.show();
                    
                }
            }
        });
        
        //设置底部BottomSheetDialog
        createBottomSheetDialog();
        
    }

    private void createBottomSheetDialog()
    {
        if (bottomSheetDialog==null)
        bottomSheetDialog = new BottomSheetDialog(this);
      //  setBahaviorCallback();
        View view = LayoutInflater.from(this).inflate(R.layout.diaglog_bottom_sheet_layout, null, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleBottomSheetDialog);
        bottomSheetDialog.setContentView(view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
        BottomSheetDialogAdapter adapter = new BottomSheetDialogAdapter();
        recyclerView.setAdapter(adapter);
        //解决bottomSheetDialog活动到底部消失后，再显示，返回半个页面
        setBahaviorCallback();
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
            return new BottomSheetDialogViewHolder(LayoutInflater.from(BottomSheetBehaviorActivity.this).inflate(R.layout.list_item_textview_layout,null,false));
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
    
    
    //解决bug
    private void setBahaviorCallback()
    {
        View view = bottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(view);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState==BottomSheetBehavior.STATE_HIDDEN)
                {
                    bottomSheetDialog.dismiss();
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }
}
