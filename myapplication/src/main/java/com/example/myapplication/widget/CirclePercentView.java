 package com.example.myapplication.widget;

 import android.content.Context;
 import android.content.res.TypedArray;
 import android.graphics.Canvas;
 import android.graphics.Color;
 import android.graphics.Paint;
 import android.graphics.Rect;
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

    //中心百分比文字大小
    private float mCenterTextSize;
     private Paint bigCirclePaint;
     private Paint sectorPaint;
     private Paint smallCirclePaint;
     private Paint textPaint;
     private RectF oval;
     //最下面的文字
     private Paint textUnderLine;
     //文字的长度的高度
     private int texHeight;
     private int texWidth;
     private Rect drawText;
     private String textShuo;

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
            mCenterTextSize = a.getDimensionPixelSize(R.styleable.CirclePercentView_centerTextSize, PxUtils.spToPx(20, context));
            mRadius = a.getDimensionPixelSize(R.styleable.CirclePercentView_radiusCircle, PxUtils.dpToPx(80, context));
            textShuo = a.getString(R.styleable.CirclePercentView_textShuo);
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
         sectorPaint = new Paint();
         sectorPaint.setColor(mSmallColor);
         sectorPaint.setAntiAlias(true);
         sectorPaint.setStyle(Paint.Style.STROKE);
         sectorPaint.setStrokeWidth(mStripeWidth);
         //弧度顶点为圆角
         sectorPaint.setStrokeCap(Paint.Cap.ROUND);
         //绘制小圆
         smallCirclePaint = new Paint();
         smallCirclePaint.setAntiAlias(true);
         smallCirclePaint.setColor(mBigColor);
         
         //绘制文本
         textPaint = new Paint();
         textPaint.setAntiAlias(true);
         textPaint.setStyle(Paint.Style.FILL);
         textPaint.setARGB(255, 255, 255, 255);

        /* Paint.FontMetrics fm =textPaint.getFontMetrics();
         mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);*/
         textUnderLine = new TextPaint();
         textUnderLine.setTextSize(60f);
         textUnderLine.setColor(Color.BLUE);
         drawText = new Rect();
         if (!TextUtils.isEmpty(textShuo))
         textUnderLine.getTextBounds(textShuo, 0, textShuo.length(), drawText);
        /* Paint.FontMetrics fontMetrics = textUnderLine.getFontMetrics();
         texHeight= (int) Math.ceil(fontMetrics.descent - fontMetrics.ascent);*/
         
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
        //确定大小模式（）
        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) 
        {
            mRadius = widthSize / 2;
            x = widthSize / 2;
            y = heightSize / 2;
            mWidth = widthSize;
            mHeight = heightSize;
        }
      //wrap_content和match_parent(必须设置半径大小！！)
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
        //画弧度
        canvas.drawArc(oval, 120, mEndAngle, false, sectorPaint);
        //画小圆
        canvas.drawCircle(x, y, mRadius - mStripeWidth, smallCirclePaint);
       
        String text = mCurPercent + "%";

        textPaint.setTextSize(mCenterTextSize);
        float textLength = textPaint.measureText(text);

        textPaint.setColor(Color.WHITE);
        canvas.drawText(text, x - textLength/2, y, textPaint);
        //画一个矩形，再画文字
      //  canvas.drawRoundRect(x-mRadius/2, y+9*mRadius/14,x+mRadius/2,y+15*mRadius/13,8,8,textPaint);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(x-mRadius/2, y+9*mRadius/14,x+mRadius/2,y+15*mRadius/13,8f,8f,textPaint);
        }else
            canvas.drawRect(x-mRadius/2, y+9*mRadius/14,x+mRadius/2,y+15*mRadius/13,textPaint);
        //考虑文字长度大于矩形长度
        if (!TextUtils.isEmpty(textShuo)) {
            textUnderLine.getTextBounds(textShuo, 0, textShuo.length(), drawText);
            if (drawText.width() > mRadius) {
                TextPaint mPaint = new TextPaint(textUnderLine);
                String msg = TextUtils.ellipsize(textShuo, mPaint, mRadius, TextUtils.TruncateAt.END).toString();
                canvas.drawText(msg, x - drawText.width() / 2, y - drawText.height() / 2 + mRadius, mPaint);
            } else
                canvas.drawText(textShuo, x - drawText.width() / 2, y - drawText.height() / 2 + mRadius, textUnderLine);
        }


    }

    //外部设置百分比数
    public void setPercent(int percent) {
        if (percent > 100) {
            throw new IllegalArgumentException("percent must less than 100!");
        }

        setCurPercent(percent);


    }

    //内部设置百分比 用于动画效果
    private void setCurPercent(int percent) {

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
     
     public void setTextSize(int floatSize)
     {
        /* if (textPaint!=null)
         {
             textPaint.setTextSize(floatSize);
             
         }*/
         mCenterTextSize = floatSize;
        postInvalidate();
     }
    
   public void setText(String text)
   {
       textShuo = text;
       postInvalidate();
   }

}
