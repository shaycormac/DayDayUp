package com.example.customviewstudy.activity.customView;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.AvoidXfermode;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.example.customviewstudy.R;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/1/23 15:41
 * @email 邮箱： 574583006@qq.com
 * @content 说明：自定义绘图的学习，吊炸天的Xfermode。
 * http://blog.csdn.net/harvic880925/article/details/51264653#comments
 */
public class AviodXfermode extends View {
    private Paint paint;
    private Bitmap bitmap;
    public AviodXfermode(Context context)
    {
        super(context);
        init(context,null);
    }

    public AviodXfermode(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public AviodXfermode(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AviodXfermode(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs)
    {
        paint = new Paint();
        paint.setColor(Color.RED);
        //获取图片
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_assassin_3);
        //使用Xfermode时，为了保险起见，我们需要做两件事： 
       // 1、禁用硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        
    }


    @Override
    protected void onDraw(Canvas canvas) 
    {
        super.onDraw(canvas);
        int width = 1080;
        //保持长宽比
        int height = width * bitmap.getHeight() / bitmap.getWidth();

        int layoutId = canvas.saveLayer(0, 0, width, height, paint, Canvas.ALL_SAVE_FLAG);
        //画图
        canvas.drawBitmap(bitmap,null,new Rect(0, 0, width, height),paint);
        paint.setXfermode(new AvoidXfermode(Color.WHITE, 0, AvoidXfermode.Mode.TARGET));
        paint.setColor(Color.TRANSPARENT);
        canvas.drawRect(0, 0, width, height,paint);
        canvas.restoreToCount(layoutId);
    }
}
