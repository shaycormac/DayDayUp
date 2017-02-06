package com.example.customviewstudy.activity.customView;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.example.customviewstudy.R;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/1/23 20:14
 * @email 邮箱： 574583006@qq.com
 * @content 说明：计算公式：[Sa * Da, Sc * Dc]，在计算alpha值时的公式是Sa * Da，是用源图像的alpha值
 * 乘以目标图像的alpha值；由于源图像的非相交区域所对应的目标图像像素的alpha是0，所以结果像素的alpha值
 * 仍是0，所以源图像的非相交区域在计算后是透明的。 
 * Mode.MULTIPLY(正片叠底)会在两个图像的一方透明时，结果像素就是透明的。所以这里使用的模式就是Mode.MULTIPLY 
 */
public class PorterDuffXfermodeMultipy extends View {
    private Bitmap destBitmap, srcBitmap;
    private Paint paint;
   
    public PorterDuffXfermodeMultipy(Context context) {
        super(context);
        initAttr(context,null);
    }

   
    public PorterDuffXfermodeMultipy(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context,attrs);
    }

   
    public PorterDuffXfermodeMultipy(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context,attrs);
    }

   
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PorterDuffXfermodeMultipy(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttr(context,attrs);
    }
    
    public void initAttr(Context context, AttributeSet attrs)
    {
        paint = new Paint();
        destBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.twiter_light, null);
        srcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.twiter_bg, null);
        //1.开启关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    /**
     * Implement this to do your drawing.
     *
     * @param canvas the canvas on which the background will be drawn
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //开启分层绘制，先记住id
        int layoutId = canvas.saveLayer(new RectF(0, 0, getWidth(), getHeight()), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(destBitmap, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layoutId);
    }
}
