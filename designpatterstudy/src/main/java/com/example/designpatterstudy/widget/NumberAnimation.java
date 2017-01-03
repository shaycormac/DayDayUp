package com.example.designpatterstudy.widget;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/1/3 22:14
 * @email 邮箱： 574583006@qq.com
 * @content 说明：用一个textview显示数字，让数字从一个值渐变到另外一个值 。
 */
public class NumberAnimation extends Animation
{
    private TextView textView;
    private int from, to;
    private int cha;
    public NumberAnimation(TextView textView)
    {
        this.textView = textView;
       
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) 
    {
        super.applyTransformation(interpolatedTime, t);
        if (interpolatedTime<1.0f)
        {
            if (from!=to)
            {
                if (cha>0)
                {
                    from++;
                    this.textView.setText(String.valueOf(from));
                }else 
                {
                    from--;
                    this.textView.setText(String.valueOf(from));
                }
            }
        }
    }
    
    public void setNum(int from,int to)
    {
        this.from = from;
        this.to = to;
        this.cha = to - from;
        setDuration(2000 * 2000);
        textView.startAnimation(this);
    }
}
