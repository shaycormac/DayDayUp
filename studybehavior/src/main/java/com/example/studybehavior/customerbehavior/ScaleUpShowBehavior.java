package com.example.studybehavior.customerbehavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.View;

import com.example.studybehavior.utils.AnimatorUtil;

/**自定义Behavior之上滑显示返回顶部按钮
 * @author 作者： Android2(范方方)
 * @datetime 创建时间：2016-11-10 14:00 GMT+8
 * @email 邮箱： 574583006@qq.com
 */
public class ScaleUpShowBehavior extends FloatingActionButton.Behavior 
{
    //viewPropertyAnimatorListener和isAnimatingOut用来监听隐藏动画的执行，
    // 当动画执行完毕后才view.setVisibility(View.GONE);
    private boolean isAnimatingOut=false;

    private ViewPropertyAnimatorListener viewPropertyAnimatorListener = new ViewPropertyAnimatorListener() {
        @Override
        public void onAnimationStart(View view) {
            isAnimatingOut = true;
        }

        @Override
        public void onAnimationEnd(View view) {
            //动画结束后，隐藏
            isAnimatingOut = false;
            view.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationCancel(View view) {
            isAnimatingOut = false;
        }
    };
    public ScaleUpShowBehavior(Context context, AttributeSet attributeSet)
    {
        super();
    }

    //页面开始滑动
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        //onStartNestedScroll望文生义啊，开始嵌套滚动的时候被调用，那么这个方法有一个boolean的返回值，
        // 是需要我们告诉CoordinatorLayout我这个Behavior要监听的滑动方向，因为我们是上下滑动时显示/隐藏FAB，
        // 所以这里我们返回return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
        return nestedScrollAxes== ViewCompat.SCROLL_AXIS_VERTICAL;
    }
    //页面正在滑动
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0 && dyUnconsumed == 0) {
            System.out.println("上滑中。。。");
        }
        if (dyConsumed == 0 && dyUnconsumed > 0) {
            System.out.println("到边界了还在上滑。。。");
        }
        if (dyConsumed < 0 && dyUnconsumed == 0) {
            System.out.println("下滑中。。。");
        }
        if (dyConsumed == 0 && dyUnconsumed < 0) {
            System.out.println("到边界了，还在下滑。。。");
        }
        //我们在的时候上滑，也就是用户需要看页面的下部分的时候显示FAB：
        if (((dyConsumed > 0 && dyUnconsumed == 0) ||(dyConsumed == 0 && dyUnconsumed > 0))&& child.getVisibility()!=View.VISIBLE)
        {
            //显示动画
            AnimatorUtil.scaleShow(child, null);
            
        }
        //在用户手指下滑，显示页面上半部分的时候隐藏FAB：
        if (((dyConsumed < 0 && dyUnconsumed == 0) ||(dyConsumed == 0 && dyUnconsumed < 0))&& child.getVisibility()!=View.GONE && !isAnimatingOut)
        {
            AnimatorUtil.scaleHide(child, viewPropertyAnimatorListener);
        }
    }
    //页面停止滑动
    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }
}
