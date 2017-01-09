package com.example.myapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/1/7 18:58
 * @email 邮箱： 574583006@qq.com
 * @content 说明：学习手势探测。
 */
public class MyGestureView extends View implements GestureDetector.OnGestureListener {
    public MyGestureView(Context context) {
        super(context);
    }

    public MyGestureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGestureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyGestureView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //当手指按下时，调用
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    //当手指快速滑动时，会调用
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
