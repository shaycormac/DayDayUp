package com.example.myapplication.widget.conflictWidget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-02-17 14:05 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：内部拦截法。
 * 父容器不拦截任何事件，将所有事件传递给子元素，如果子元素需要则消耗掉，如果不需要则通过
 * requestDisallowInterceptTouchEvent方法交给父容器处理，称为内部拦截法，使用起来稍显麻烦
 */
public class ConflictSonListView extends ListView {
    private int nowY;
    private ConflictSonScrollView sonScrollView;

    public ConflictSonListView(Context context) {
        super(context);
    }

    public ConflictSonListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ConflictSonListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ConflictSonListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //内部拦截法
    // 另一种是父容器不拦截任何事件，将所有事件传递给子元素，如果子元素需要则消耗掉，如果不需要则通过
    // requestDisallowInterceptTouchEvent方法交给父容器处理，称为内部拦截法，使用起来稍显麻烦
    //事件传递过程是由外向内的，也就是事件会先传给父元素在向下传递给子元素。但是子元素可以通过
    // requestDisallowInterceptTouchEvent来干预父元素的分发过程，但是down事件除外（因为down事件方法里，
    // 会清除所有的标志位）
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                nowY = y;
                if (sonScrollView!=null)
                    //默认不干预父元素，让父布局去处理
                    sonScrollView.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                if (sonScrollView!=null)
                {
                    if ( isTop() && y>nowY)
                    {
                        //干预父布局，自己来处理事件
                        sonScrollView.requestDisallowInterceptTouchEvent(false);
                        break;
                    }else if (isBottom()&& y<nowY)
                    {
                        sonScrollView.requestDisallowInterceptTouchEvent(false);
                        break;
                    }
                    sonScrollView.requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    
    public void setConflictSonView(ConflictSonScrollView sonScrollView)
    {
        this.sonScrollView = sonScrollView;
    }

    //先判断是否显示出第一个或者最后一个，再判断他们距离父容器的距离是否为0
    //由于这个方法的就是listView,所以可以直接用
    public boolean isBottom()
    {
        boolean result = false;
        if (getLastVisiblePosition()==(getCount()-1))
        {
            //先弄清一个概念。getTop，getLeft等是相对于它的父布局来说的，这里就是listView
            //最后一个view是这么找到的，当前屏幕的最后一个可见位置减去第一个可见位置
            View bottomChildView = getChildAt(getLastVisiblePosition() - getFirstVisiblePosition());
            //是否到底部是当前listView的高度是否大于最后一个位置的高度
            result = (getHeight() >= bottomChildView.getBottom());
        }
        return result;
    }

    public boolean isTop()
    {
        boolean result = false;
        if (getFirstVisiblePosition()==0)
        {
            View topChildView = getChildAt(0);
            result = topChildView.getTop() == 0;
        }
        return result;
    }
}
