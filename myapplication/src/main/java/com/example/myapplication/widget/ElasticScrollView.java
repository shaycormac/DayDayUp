package com.example.myapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-01-10 15:21 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：可以回弹的ScrollView，解决子控件左右滑动被拦截的问题
 * http://blog.csdn.net/mennoa/article/details/43852861
 */
public class ElasticScrollView extends ScrollView
{
    /**
     * 手指抖动误差
     */
    public static final int SHAKE_MOVE_VALUE = 18;
    /**
     * ScrollView的内部view
     */
    private View innerView;
    /**
     * 记录innerView的最初位置
     */
    private float startY;
    /**
     * 记录ScrollView的innerView的原始位置
     */
    private Rect outRect = new Rect();

    /**
     * 动画是否结束
     */
    private boolean animationFinish = true;
    public ElasticScrollView(Context context) {
        super(context);
    }

    public ElasticScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ElasticScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ElasticScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 继承View,该方法表示xml在所有布局加载之后执行
     * 给内部的view第一个找到初始位置
     */
    @Override
    protected void onFinishInflate() 
    {
        //super.onFinishInflate();
        if (getChildCount()>0)
            innerView = getChildAt(0);
    }
    
    /**
     * 只有ViewGroup才有拦截方法，返回true，当前消费，否则分发
        解决子控件 截取滑动监听的代码在onInterceptTouchEvent() ,通过监听Y的变化，来判断是点击子控件还是上拉下拉
     * 继承自ViewGroup
     * 返回true, 截取触摸事件
     * 返回false, 将事件传递给onTouchEvent()和子控件的dispatchTouchEvent()
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) 
    {
        // 判断 点击子控件 or 按住子控件滑动
        // 如果点击子控件，则返回 false, 子控件响应点击事件
        // 如果按住子控件滑动，则返回 true, 滑动布局
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float currentY = ev.getY();
                /**
                 * 滑动的垂直距离
                 */
                float scrollY = currentY - startY;
                //关键点：是否返回True,绝对值大于手动距离，则认为是上下滑动了，消费事件，否则将事件下发
                //给子控件
                return Math.abs(scrollY) > SHAKE_MOVE_VALUE;
        }
        //默认返回父级值
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) 
    {
        if (innerView==null)
            return super.onTouchEvent(ev);
        else 
        {
            myTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    private void myTouchEvent(MotionEvent ev)
    {
        //动画结束的时候才能接触手指的反馈
        if (animationFinish)
        {
            //对这个滑动控件的事件处理（不是它的子控件）
            switch (ev.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    startY = ev.getY();
                    //事件继续分发下去
                    super.onTouchEvent(ev);
                    break;
                case MotionEvent.ACTION_MOVE:
                    //之前的位置要是为0，则为ev.getY()
                    float preY = startY == 0 ? ev.getY() : startY;
                    float nowY = ev.getY();
                    //移动位置
                    int deltaY = (int) (preY - nowY);
                    startY = nowY;
                    //当滚动到最上或者最下时就不会再滚动，这时移动布局
                    if (isNeedMove())
                    {
                        if (outRect.isEmpty())
                        {
                            //保存初始innerView的位置
                            outRect.set(innerView.getLeft(), innerView.getTop(), innerView.getRight(), innerView.getBottom());
                        }
                        //跟随手指移动布局
                        // 这里 deltaY/2 为了操作体验更好
                        innerView.layout(innerView.getLeft(), innerView.getTop() - deltaY / 2,
                                innerView.getRight(), innerView.getBottom() - deltaY / 2);
                        
                    }else
                        super.onTouchEvent(ev);
                   break;
                case MotionEvent.ACTION_UP:
                    //重新设置为0，变为初始状态
                    startY = 0;
                    //整个事件结束，判断是否需要执行动画
                    if (isNeedStartAnimation())
                        animation();
                    super.onTouchEvent(ev);
                    break;
                default:
                    break;
            }
            
        }
    }

    /**
     * 移动动画
     */
    public void animation()
    {
        //垂直方向上位移
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, outRect.top - innerView.getTop());
        animation.setDuration(500);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        //设置监听
        animation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation) 
            {
                animationFinish = false;
            }

            @Override
            public void onAnimationEnd(Animation animation) 
            {
                innerView.clearAnimation();
                //动画结束，需要回归位置
                innerView.layout(outRect.left, outRect.top, outRect.right, outRect.bottom);
                //这句代码执行之后，rect的四个位置全部变为0
                outRect.setEmpty();
                animationFinish = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        innerView.startAnimation(animation);
    }
    /**
     * 是否开启动画
     */
    public boolean isNeedStartAnimation()
    {
        return !outRect.isEmpty();
    }
    /**
     * 是否移动布局
     */
    public boolean isNeedMove()
    {
       /* getMeasuredHeight()返回的是原始测量高度，与屏幕无关， 
        getHeight()返回的是在屏幕上显示的高度。
        实际上在当屏幕可以包裹内容的时候，他们的值是相等的，只有当view超出屏幕后，才能看出他们的区别。
        当超出屏幕后，getMeasuredHeight()等于getHeight()加上屏幕之外没有显示的高度。*/
        int offset = innerView.getMeasuredHeight() - getHeight();
        offset = (offset < 0) ? 0 : offset;
        //返回的是滑动View显示部分的顶部
        int scrollY = getScrollY();
        return (offset==0 || scrollY==offset);
    }
}
