package com.example.customviewstudy.activity.customView;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.customviewstudy.R;
import com.example.customviewstudy.activity.utils.BitMapCreateUtils;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/1/23 20:14
 * @email 邮箱： 574583006@qq.com
 * @content 说明：Mode.SRC_OUT简单来说，当目标图像有图像时结果显示空白像素，当目标图像没有图像时，结果显示源图像。
 * 实现橡皮擦效果
 * 当目标图像有图像时计算结果为空白像素，当目标图像没有图像时，显示源图像； 
   所以我们把手指轨迹做为目标图像，在与源图像计算时，有手指轨迹的地方就变为空白像素了，看起来的效果就是被擦除了。 
 
 延伸，加刮刮乐效果，在原来的基础上，添加一张图片即可
 */
public class PorterDuffXfermodeSrcOut extends View 
{
    private Bitmap destBitmap, srcBitmap,bottomBitmap;
    private Paint paint;
    private Path path;
    private float preX, preY;
    //将手指触摸划到目标图像上去
    private Canvas touchCanvas;
    //地步图片
    private int resourceId = R.mipmap.bg_assassin_3;
    //是否刮奖挂过了，淘宝的攻略是在上面覆盖一个按钮，点击按钮就默认是刮开了，下次再进来，直接显示刮奖的结果。
    private boolean hasOpenPrize = false;
    public PorterDuffXfermodeSrcOut(Context context) {
        super(context);
        initAttr(context,null);
    }

   
    public PorterDuffXfermodeSrcOut(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context,attrs);
    }

   
    public PorterDuffXfermodeSrcOut(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context,attrs);
    }

   
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PorterDuffXfermodeSrcOut(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttr(context,attrs);
    }
    
    public void initAttr(Context context, AttributeSet attrs)
    {
        paint = new Paint();
        //颜色无所谓，只要透明度为1即可
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(45);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        srcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.dog, null);
        //先生成一个和原图像一般大的画，在上面做文章
        destBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //1.开启关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        //
        path = new Path();
        touchCanvas = new Canvas(destBitmap);
       
    }

    /**
     * Implement this to do your drawing.
     *
     * @param canvas the canvas on which the background will be drawn
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //底层的挂挂乐图片
        bottomBitmap = BitMapCreateUtils.decodeSampledBitmapFromResource(getResources(), resourceId, srcBitmap.getWidth(),srcBitmap.getHeight());
        //先画最底层的图片
        canvas.drawBitmap(bottomBitmap,0,0,paint);
        if (!hasOpenPrize) 
        {
            //开启分层绘制，先记住id
            int layoutId = canvas.saveLayer(new RectF(0, 0, getWidth(), getHeight()), null, Canvas.ALL_SAVE_FLAG);
            touchCanvas.drawPath(path, paint);
            canvas.drawBitmap(destBitmap, 0, 0, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
            canvas.drawBitmap(srcBitmap, 0, 0, paint);
            paint.setXfermode(null);
            canvas.restoreToCount(layoutId);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) 
    {
        int actionMode = event.getAction();
        switch (actionMode) 
        {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                preX = event.getX();
                preY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                if (!hasOpenPrize) {
                    float endX = (preX + event.getX()) / 2;
                    float endY = (preY + event.getY()) / 2;
                    path.quadTo(preX, preY, endX, endY);
                    preX = event.getX();
                    preY = event.getY();
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        //异步回掉重新加载数据
        postInvalidate();
        return super.onTouchEvent(event);
    }
    
    //设置挂挂乐的加载底部图片
    public void setBottomBitMap(int resourceId)
    {
        this.resourceId = resourceId;
        postInvalidate();
        
    }
    //是否挂挂乐挂过了
    public void hasOpened(boolean hasOpenPrize)
    {
        this.hasOpenPrize = hasOpenPrize;
        postInvalidate();
    }
}
