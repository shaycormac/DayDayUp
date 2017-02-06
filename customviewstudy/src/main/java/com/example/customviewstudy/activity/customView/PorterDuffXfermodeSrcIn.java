package com.example.customviewstudy.activity.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.customviewstudy.activity.utils.BitMapCreateUtils;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/1/23 17:07
 * @email 邮箱： 574583006@qq.com
 * @content 说明：吊炸天的PorterDuffXpermode模式。
 * srcIn模式（往下看，第四部分）
 * http://blog.csdn.net/harvic880925/article/details/51264653#comments
 */
public class PorterDuffXfermodeSrcIn extends View
{
    private int width ;
    private int height;
    //绘图笔
    private Paint paint;
   
    public PorterDuffXfermodeSrcIn(Context context) 
    {
        super(context);
        init(context,null);
    }

    
    public PorterDuffXfermodeSrcIn(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context,attrs);
    }

    
    public PorterDuffXfermodeSrcIn(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) 
    {
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        width = 800;
        height = 800;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
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
        int layID = canvas.saveLayer(new RectF(0, 0, width, height), paint, Canvas.ALL_SAVE_FLAG);
        //先绘制目标图像dest 先画目标图像：圆形bitmap
        canvas.drawBitmap(BitMapCreateUtils.destBitmap(width, height), 0, 0, paint);
        //吊炸天来了，设置画笔 然后设置图像混合模式：
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.ADD));
        //最后在源图像上生成结果图并更新到目标图像上(这句话信息量很大，仔细品味)
        //它会在源图像所在区域与目标图像运算，在得到结果以后，将结果覆盖到目标图像上
        //脏数据的更新
        //，在得到结果以后，把结果对应区域的图像先清空，然后把结果覆盖上去。（还记得我们在讲解canvas脏数据
        // 更新的时候提到，如果没有xfermode就直接覆盖上去，如果有xfermode则先清空对应的区域，然后再覆盖上去）； 
        //这里还需要强调一点，源图像在运算时，只是在源图像所在区域与对应区域的目标图像做运算。所以目标图像
       // 与源图像不相交的地方是不会参与运算的！这一点非常重要！不相交的地方不会参与运算，所以不相交的地方的图像
        // 也不会是脏数据，也不会被更新
        canvas.drawBitmap(BitMapCreateUtils.srcBitmap(width, height), width / 2, height / 2, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layID);
        
        //书桌变亮效果
    }

   
    
}
