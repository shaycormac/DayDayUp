package com.example.somemyidea.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.example.somemyidea.R;

import java.util.List;

/**
 * Created by ShayPatrickCormac on 2016/11/30.
 */

public class CustomTablayout extends LinearLayout 
{
    private TabLayout tabLayout;
    private OnTabChooseListener onTabChooseListener;
    public CustomTablayout(Context context) {
        super(context);
        init(context, null);
    }

    public CustomTablayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomTablayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomTablayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) 
    {
        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER_VERTICAL);
        if (getContext() instanceof Activity) {
            tabLayout = (TabLayout) ((Activity) getContext()).getLayoutInflater().inflate(R.layout.custom_tab_layout, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            tabLayout.setLayoutParams(params);
            this.addView(tabLayout);
        }
        else
            throw new ClassCastException("context必须继承Activity!");
    }
    
    //设置数据
    public <T>void setTabLayoutData(final List<T> objectList, OnTabChooseListener<T> chooseListener)
    {
        if (objectList==null || objectList.isEmpty() || tabLayout==null)
            return;
        this.onTabChooseListener = chooseListener;
            tabLayout.removeAllTabs();
        for (T t : objectList) 
        {
            if (TextUtils.isEmpty(t.toString()))
                objectList.remove(t);
        }
        TabLayout.Tab tab;
        for (int i = 0; i < objectList.size(); i++) 
        {
            tab = tabLayout.newTab().setText(objectList.get(i).toString());
            if (i!=0)
                tabLayout.addTab(tab);
            else 
                tabLayout.addTab(tab,0,true);
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() 
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab) 
            {
                if (onTabChooseListener!=null)
                onTabChooseListener.onTabChoosed(tab.getPosition(),objectList.get(tab.getPosition()));
                
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        
    }
    
    public interface OnTabChooseListener<T>
    {
        void onTabChoosed(int position,T t);
    }
}
