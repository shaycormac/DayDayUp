package com.example.myapplication.widget.conflictWidget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-02-17 10:38 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：首先用外部拦截法，我们需要重写ScrollView的onInterceptTouchEvent方法
 */
public class ConflictFatherScrollView extends ScrollView 
{
    private ListView listView;
    private int nowY;
    public ConflictFatherScrollView(Context context) {
        super(context);
    }

    public ConflictFatherScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ConflictFatherScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ConflictFatherScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) 
    {
        boolean intercepted = false;
        int y = (int) ev.getY();
        Log.d("y的坐标", String.valueOf(y));
        switch(ev.getAction())
        {
            //这里我们看到Down事件里我们并没有返回false而是返回了super.onInterceptTouchEvent(event),
            // 这是因为ScrollView在Down方法时需要初始化一些参数如果我们直接返回false,会导致滑动出现问题。
            // 并且前面说过ViewGroup的onInterceptTouchEvent方法是默认返回false的，所以我们这里直接返回super方法即可。
          case MotionEvent.ACTION_DOWN:
              nowY = y;
              intercepted = super.onInterceptTouchEvent(ev);
              break;
          case MotionEvent.ACTION_MOVE :
              if (listView!=null)
              {
                  //临界点
                  if ( isTop(listView) && y>nowY)
                  {
                      intercepted = true;
                      break;
                  }else if (isBottom(listView)&& y<nowY)
                  {
                      intercepted = true;
                      break;
                  }
                      
              }
              intercepted = false;
              break;
          case MotionEvent.ACTION_UP:
              intercepted = false;
              break;
            default:
                break;
        }
        return intercepted;
    }
    
    
    public void setListView(ListView listView)
    {
        this.listView = listView;
    }
    
    //先判断是否显示出第一个或者最后一个，再判断他们距离父容器的距离是否为0
    public boolean isBottom(ListView listView)
    {
        boolean result = false;
        //找到最后一个可见的item是list的最后一个元素
        if (listView.getLastVisiblePosition()==(listView.getCount()-1))
        {
            //先弄清一个概念。getTop，getLeft等是相对于它的父布局来说的，这里就是listView
            //最后一个view是这么找到的，当前屏幕的最后一个可见位置减去第一个可见位置
            View bottomChildView = listView.getChildAt(listView.getLastVisiblePosition() - listView.getFirstVisiblePosition());
            //是否到底部是当前listView的高度是否大于最后一个位置的高度
            //listView.getHeight() >= bottomChildView.getBottom()这个判断应该是强调最后一个item完整的显露出来，再让
            //父亲滑动
            result = (listView.getHeight() >= bottomChildView.getBottom());
        }
        return result;
    }
    
    public boolean isTop(ListView listView)
    {
        boolean result = false;
        if (listView.getFirstVisiblePosition()==0)
        {
            View topChildView = listView.getChildAt(0);
            //topChildView.getTop() == 0 这个判断是第一个元素完全可见，且最顶端完全显露出来，再滑动。
            result = topChildView.getTop() == 0;
        }
        return result;
    }
}
