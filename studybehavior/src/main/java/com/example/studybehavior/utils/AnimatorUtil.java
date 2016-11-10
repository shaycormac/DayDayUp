package com.example.studybehavior.utils;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;

/**
 * @author 作者： Android2(范方方)
 * @datetime 创建时间：2016-11-10 14:46 GMT+8
 * @email 邮箱： 574583006@qq.com
 */
public class AnimatorUtil
{
   // private static Interpolator fastOutSlowInInterpolator = new FastOutSlowInInterpolator();
    public static final LinearOutSlowInInterpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new LinearOutSlowInInterpolator();

    //显示动画
    public static void scaleShow(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener)
    {
        view.setVisibility(View.VISIBLE);
        //渐显动画
        ViewCompat.animate(view).scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(1000)
                .setListener(viewPropertyAnimatorListener)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .start();
    }

    // 隐藏view
    public static void scaleHide(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        ViewCompat.animate(view)
                .scaleX(0.0f)
                .scaleY(0.0f)
                .alpha(0.0f)
                .setDuration(1000)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .setListener(viewPropertyAnimatorListener)
                .start();
    }
}
