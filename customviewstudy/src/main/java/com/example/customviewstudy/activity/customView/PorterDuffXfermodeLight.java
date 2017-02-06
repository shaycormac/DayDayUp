package com.example.customviewstudy.activity.customView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.example.customviewstudy.R;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/1/23 17:07
 * @email 邮箱： 574583006@qq.com
 * @content 说明：吊炸天的PorterDuffXpermode模式。
 * Light模式 图片叠加
 * http://blog.csdn.net/harvic880925/article/details/51264653#comments
 */
public class PorterDuffXfermodeLight extends View
{
    //绘图笔
    private Paint paint;
    private Bitmap destBitmap, srcBitmap;
   
    public PorterDuffXfermodeLight(Context context) 
    {
        super(context);
        init(context,null);
    }

    
    public PorterDuffXfermodeLight(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context,attrs);
    }

    
    public PorterDuffXfermodeLight(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) 
    {
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        destBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.book_bg, null);
        srcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.book_light, null);
    }

    /**
     * Implement this to do your drawing.
     *
     * @param canvas the canvas on which the background will be drawn
     */
    @Override
    protected void onDraw(Canvas canvas) 
    {
        super.onDraw(canvas);
        //隔离绘制
        int layID = canvas.saveLayer(0,0,getWidth(),getHeight(), null, Canvas.ALL_SAVE_FLAG);
        //先绘制目标图像dest 先画目标图像：圆形bitmap
        canvas.drawBitmap(destBitmap, 0, 0, paint);
        //吊炸天来了，设置画笔 然后设置图像混合模式：
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layID);
        
        //书桌变亮效果
    }

   
    
}
