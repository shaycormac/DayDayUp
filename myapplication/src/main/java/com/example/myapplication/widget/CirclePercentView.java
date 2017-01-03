 package com.example.myapplication.widget;

 import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.utils.PxUtils;


 /**一个圆形百分比进度 View
 * 用于展示简易的图标
 * Created by Administrator on 2015/12/16.
 */
public class CirclePercentView extends View {

    //圆的半径
    private float mRadius;

    //色带的宽度
    private float mStripeWidth;
    //总体大小
    private int mHeight;
    private int mWidth;

    //动画位置百分比进度
    private int mCurPercent;

    //实际百分比进度
    private int mPercent;
    //圆心坐标
    private float x;
    private float y;

     //小圆的颜色
    private int mSmallColor;
    //大圆颜色
    private int mBigColor;
     //弧度颜色
     private int arcColor;
     private Paint bigCirclePaint;
     private Paint arcPaint;
     private Paint smallCirclePaint;
     //下部矩形框画笔
     private Paint rectPaint;
     private RectF oval;
     //最下面的文字
     private Paint textTipsPaint;
     private String textTips;
     //文字长度大于矩形框的,切割文字
     private TextPaint mPaint;
     //切割后的字符串
     private String msg;
     //下部字体内容的高宽
     private int tipsHeight;
     private float tipsWidth;
     //测量下部字体的长宽
     private Paint.FontMetricsInt fontMetricsInt;
     

     public CirclePercentView(Context context) {
        this(context, null);
    }

    public CirclePercentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirclePercentView(Context context, AttributeSet attrs, int defStyleAttr) 
    {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode() && attrs!=null) 
        {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CirclePercentView, defStyleAttr, 0);
            mStripeWidth = a.getDimension(R.styleable.CirclePercentView_stripeWidth, PxUtils.dpToPx(20, context));
            mCurPercent = a.getInteger(R.styleable.CirclePercentView_percent, 0);
            mSmallColor = a.getColor(R.styleable.CirclePercentView_smallColor, 0xffafb4db);
            mBigColor = a.getColor(R.styleable.CirclePercentView_bigColor, 0xff6950a1);
            mRadius = a.getDimensionPixelSize(R.styleable.CirclePercentView_radiusCircle, PxUtils.dpToPx(80, context));
            arcColor = a.getColor(R.styleable.CirclePercentView_arcColor, 0xff6950a1);
            a.recycle();
        }
        initVariable();

        //划弧度的正切矩形
        oval = new RectF();
        oval.left = mStripeWidth / 2;
        oval.top = mStripeWidth / 2;
    }

     private void initVariable() 
     {
         
         //绘制大圆
         bigCirclePaint = new Paint();
         //抗锯齿
         bigCirclePaint.setAntiAlias(true);
         bigCirclePaint.setColor(mBigColor);
         bigCirclePaint.setStyle(Paint.Style.FILL);

         //弧度
         arcPaint = new Paint();
         arcPaint.setColor(arcColor);
         arcPaint.setAntiAlias(true);
         arcPaint.setStyle(Paint.Style.STROKE);
         arcPaint.setStrokeWidth(mStripeWidth);
         //弧度顶点为圆角
         arcPaint.setStrokeCap(Paint.Cap.ROUND);
         //绘制小圆
         smallCirclePaint = new Paint();
         smallCirclePaint.setAntiAlias(true);
         smallCirclePaint.setColor(mSmallColor);
         
         //绘制文本
         rectPaint = new Paint();
         rectPaint.setAntiAlias(true);
         rectPaint.setStyle(Paint.Style.FILL);
         rectPaint.setARGB(255, 255, 255, 255);
         rectPaint.setColor(arcColor);

        /* Paint.FontMetrics fm =rectPaint.getFontMetrics();
         mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);*/
         textTipsPaint = new TextPaint();
         textTipsPaint.setTextSize(60f);
         textTipsPaint.setColor(Color.WHITE);
        /* Paint.FontMetrics fontMetrics = textTipsPaint.getFontMetrics();
         texHeight= (int) Math.ceil(fontMetrics.descent - fontMetrics.ascent);*/
         mPaint = new TextPaint(rectPaint);
     }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
    {
        //获取测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //获取测量大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //确定大小模式（）MatchParent和精确的值
        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) 
        {
            mRadius = widthSize / 2;
            x = widthSize / 2;
            y = heightSize / 2;
            mWidth = widthSize;
            mHeight = heightSize;
        }
      //wrap_content(必须设置半径大小！！)
        if(widthMode == MeasureSpec.AT_MOST&&heightMode ==MeasureSpec.AT_MOST)
        {
            mWidth = (int) (mRadius*2);
            mHeight = (int) (mRadius*2);
            x = mRadius;
            y = mRadius;
        }
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int mEndAngle = mCurPercent * 3;
        //画大圆
        canvas.drawCircle(x, y, mRadius, bigCirclePaint);
         //划弧度的正切矩形(在重新测量之后使用)
        oval.right = mRadius* 2- oval.left;
        oval.bottom = mRadius*2- oval.top;
        //画弧度(判断弧度为0的情况,下部矩形框颜色和大圆背景颜色一样)
        if (mEndAngle==0)
        {
            arcPaint.setColor(mBigColor);
            rectPaint.setColor(mBigColor);
        }else 
        {
            arcPaint.setColor(arcColor);
            rectPaint.setColor(arcColor); 
        }
        canvas.drawArc(oval, 120, mEndAngle, false, arcPaint);
        //画小圆
        canvas.drawCircle(x, y, mRadius - mStripeWidth, smallCirclePaint);
     
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) 
        {
            canvas.drawRoundRect(x-mRadius/2, y+9*mRadius/14,x+mRadius/2,y+15*mRadius/13,8f,8f, rectPaint);
        }else
            canvas.drawRect(x-mRadius/2, y+9*mRadius/14,x+mRadius/2,y+15*mRadius/13, rectPaint);
        //考虑文字长度大于矩形长度
        if (!TextUtils.isEmpty(textTips)) 
        {
            //测量圆心字体的长宽
            fontMetricsInt = textTipsPaint.getFontMetricsInt();
            tipsHeight = fontMetricsInt.ascent + fontMetricsInt.descent;
            tipsWidth = textTipsPaint.measureText(textTips);
            if (tipsWidth > mRadius)
            {
                msg = TextUtils.ellipsize(textTips, mPaint, mRadius, TextUtils.TruncateAt.END).toString();
                canvas.drawText(msg, x - tipsWidth / 2, y + tipsHeight + mRadius, mPaint);
            } else
                canvas.drawText(textTips, x - tipsWidth / 2, y + tipsHeight + mRadius, textTipsPaint);
        }


    }

    //外部设置百分比数
    public void setPercent(int percent) 
    {
        if (percent > 100) {
            throw new IllegalArgumentException("percent must less than 100!");
        }
        setCurPercent(percent);
    }

    //内部设置百分比 用于动画效果
    private void setCurPercent(int percent)
    {
        mPercent = percent;
        new Thread(new Runnable() {
            @Override
            public void run() {
                int sleepTime = 1;
                for(int i =0;i<mPercent;i++){
                    if(i%20 == 0){
                        sleepTime+=2;
                    }
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mCurPercent = i;
                    CirclePercentView.this.postInvalidate();
                }
                }

        }).start();

    }
     
   public void setTextTips(String text,int textColor,int paintColor,int textSize)
   {
       textTips = text;
       textTipsPaint.setColor(textColor);
       textTipsPaint.setTextSize(textSize);
       arcColor = paintColor;
       postInvalidate();
   }
     
     
}
