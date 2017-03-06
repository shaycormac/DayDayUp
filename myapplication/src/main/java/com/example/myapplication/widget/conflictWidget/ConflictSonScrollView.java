package com.example.myapplication.widget.conflictWidget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-02-17 15:04 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：第二种解决方案
 * 从子View下手
 * 同时父布局也需要处理
 * 重写ScrollView的onInterceptTouchEvent方法，让其拦截除了Down事件以外的其他方法：
 */
public class ConflictSonScrollView extends ScrollView {
    public ConflictSonScrollView(Context context) {
        super(context);
    }

    public ConflictSonScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ConflictSonScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ConflictSonScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        if (ev.getAction()==MotionEvent.ACTION_DOWN)
        return super.onInterceptTouchEvent(ev);
        else
            return true;
    }
    
}
