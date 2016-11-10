package com.example.studybehavior.customerbehavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.studybehavior.utils.AnimatorUtil;

/**
 * @author 作者： Android2(范方方)
 * @datetime 创建时间：2016-11-10 20:13 GMT+8
 * @email 邮箱： 574583006@qq.com
 */
public class ScaleDownShowBehavior extends FloatingActionButton.Behavior {
    //动画是否正在执行
    private boolean isAnimatingOn;
    private OnStateChangeListener onStateChangeListener;
    //外部监听显示和隐藏
    public interface  OnStateChangeListener
    {
        void onChange(boolean isShow);
    }
    
    public void setOnStateChangedListener(OnStateChangeListener onStateChangedListener)
    {
        this.onStateChangeListener = onStateChangedListener;
    }

    public ScaleDownShowBehavior(Context context, AttributeSet attributeSet) 
    {
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes== ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        //手指上滑，隐藏view
        if ((dyConsumed>0||dyUnconsumed>0)&& !isAnimatingOn && child.getVisibility()==View.VISIBLE)
        {
            AnimatorUtil.scaleHide(child,animatorListener);
            if (onStateChangeListener!=null)
                onStateChangeListener.onChange(false);
        }
        //手指下滑，出现view
      else  if ((dyConsumed<0||dyUnconsumed<0)&& child.getVisibility()!=View.VISIBLE)
        {
            AnimatorUtil.scaleShow(child,null);
            if (onStateChangeListener!=null)
                onStateChangeListener.onChange(true);
        }
    }

    private ViewPropertyAnimatorListener animatorListener = new ViewPropertyAnimatorListener() {
        @Override
        public void onAnimationStart(View view)
        {
            isAnimatingOn = true;
        }

        @Override
        public void onAnimationEnd(View view) {
            isAnimatingOn = false;
            view.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationCancel(View view) {
            isAnimatingOn = false;
        }
    };
    
    //类似于bottomBehavior，添加静态方法，拿到Behavior对象
    public static <V extends View> ScaleDownShowBehavior from(V view)
    {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof CoordinatorLayout.LayoutParams))
        {
            throw new IllegalArgumentException("这个View不是CoordinatorLayout的子View");
        }

        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params).getBehavior();
        if (! (behavior instanceof ScaleDownShowBehavior))
        {
            throw new IllegalArgumentException("这个View的Behaviro不是ScaleDownShowBehavior");
        }
        return (ScaleDownShowBehavior) behavior;
    }
    
}
