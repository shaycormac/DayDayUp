package com.example.studybehavior.customerbehavior;

import android.content.Context;
import android.support.design.widget.BasicBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author 作者： Android2(范方方)
 * @datetime 创建时间：2016-11-11 10:16 GMT+8
 * @email 邮箱： 574583006@qq.com
 */
public class SelfDefineBehavior extends BasicBehavior<View>
{
    private ListenerAnimatorOverBuild animatorOverBuild;
    public SelfDefineBehavior(Context context, AttributeSet attrs) 
    {
        super(context, attrs);
        animatorOverBuild = new ListenerAnimatorOverBuild();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes== ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

        //        if (dyConsumed > 0 && dyUnconsumed == 0) {
//            System.out.println("上滑中。。。");
//        }
//        if (dyConsumed == 0 && dyUnconsumed > 0) {
//            System.out.println("到边界了还在上滑。。。");
//        }
//        if (dyConsumed < 0 && dyUnconsumed == 0) {
//            System.out.println("下滑中。。。");
//        }
//        if (dyConsumed == 0 && dyUnconsumed < 0) {
//            System.out.println("到边界了，还在下滑。。。");
//        }

        // 这里可以写你的其他逻辑动画，这里只是举例子写了个缩放动画。
        if ((dyConsumed > 0 || dyUnconsumed > 0) && animatorOverBuild.isFininsh() && child.getVisibility() == View.VISIBLE) {//往下滑
            scaleHide(child, animatorOverBuild.build());
        } else if ((dyConsumed < 0 || dyUnconsumed < 0) && child.getVisibility() != View.VISIBLE) {
            scaleShow(child, null);
        }
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }
}
