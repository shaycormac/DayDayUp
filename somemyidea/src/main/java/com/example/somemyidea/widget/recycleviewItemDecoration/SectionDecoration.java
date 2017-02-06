package com.example.somemyidea.widget.recycleviewItemDecoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.somemyidea.R;
import com.example.somemyidea.entity.NameBean;
import com.example.somemyidea.utils.DensityUtil;

import java.util.List;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-02-06 17:02 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的目的，意义。
 */
public class SectionDecoration extends RecyclerView.ItemDecoration 
{
    private static final String TAG = "SectionDecoration";

    private List<NameBean> dataList;

    private DecorationCallback callback;
    private TextPaint textPaint;
    private Paint paint;
    private int topGap;
    private int alignBottom;
    private Paint.FontMetrics fontMetrics;

    public SectionDecoration(List<NameBean> dataList, Context context ,DecorationCallback callback) {
        Resources res = context.getResources();
        this.dataList = dataList;
        this.callback = callback;
        //设置悬浮栏的画笔--paint
        paint = new Paint();
        paint.setColor(res.getColor(android.R.color.holo_blue_dark));
        //设置悬浮栏中文本的画笔  
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(DensityUtil.dip2px(context, 14));
        textPaint.setColor(Color.DKGRAY);
        textPaint.setTextAlign(Paint.Align.LEFT);
        fontMetrics = new Paint.FontMetrics();
        //悬浮栏的高度(它是在某个View的上方)
        topGap = res.getDimensionPixelSize(R.dimen.sectioned_top);
        //文本显示的位置
        alignBottom = res.getDimensionPixelSize(R.dimen.section_alignBottom);
    }
    //间隔线


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //获取位置
        int pos = parent.getChildAdapterPosition(view);
        Log.i(TAG, "getItemOffsets：" + pos);
        //获取当前的groupId
        String groupId = callback.getGroupId(pos);
        if ("-1".equals(groupId))
            return;
        //只有是同一组的第一个才显示悬浮栏 
        // outRect.top表示当前View的上面位置距离
        if (pos==0|| isFirstInGroup(pos))
        {
            outRect.top = topGap;
            if(dataList.get(pos).getName()=="")
                outRect.top = 0;
        }else
            outRect.top = 0;
    }

    /**
     * 判断是不是组中的第一个位置
     * @param pos
     * @return
     */
    private boolean isFirstInGroup(int pos)
    {
        if (pos==0)
            return true;
        else 
        {
            // 因为是根据 字符串内容的相同与否 来判断是不是同意组的，所以此处的标记id 要是String类型  
            // 如果你只是做联系人列表，悬浮框里显示的只是一个字母，则标记id直接用 int 类型就行了  
            String prevGroupId = callback.getGroupId(pos - 1);
            String groupId = callback.getGroupId(pos);
            //判断前一个字符串 与 当前字符串 是否相同  
            if (prevGroupId.equals(groupId)) {
                return false;
            } else {
                return true;
            }

        }
        
    }
  //绘制矩形框
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) 
    {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        View view;
        int position;
        String groupId;
        String textLine;
        float top;
        float bottom;
        for (int i = 0; i < childCount; i++) 
        {
            //先获取当前的ziView
            view = parent.getChildAt(i);
            //根据子View获取所在的位置
            position = parent.getChildAdapterPosition(view);
            //再获取GroupId
            groupId = callback.getGroupId(position);
            if ("-1".equals(groupId))
                return;
            //获取字符串内容？？
            textLine = callback.getGroupFirstLine(position).toUpperCase();
            if (""==textLine) {
                top = view.getTop();
                bottom = view.getTop();
                c.drawRect(left, top, right, bottom, paint);
            }else 
            {
                //绘制于上方的矩形框
                if (position==0 || isFirstInGroup(position))
                {
                    //绘制上方，Y轴越往上越小。
                    top = view.getTop() - topGap;
                    bottom = view.getTop();
                    //绘制悬浮栏
                    c.drawRect(left,top-topGap,right,bottom,paint);
                    //绘制文字
                    c.drawText(textLine,left,bottom,textPaint);
                    
                }
            }
        }
    }

    /**
     * Draw any appropriate decorations into the Canvas supplied to the RecyclerView.
     * Any content drawn by this method will be drawn after the item views are drawn
     * and will thus appear over the views.
     *
     * @param c      Canvas to draw into
     * @param parent RecyclerView this ItemDecoration is drawing into
     * @param state  The current state of RecyclerView.
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int itemCount = state.getItemCount();
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        float lineHeight = textPaint.getTextSize() + fontMetrics.descent;

        String preGroupId ;
        String groupId = "-1";
        View view;
        int position;
        String textLine;
        float top;
        float bottom;
        for (int i = 0; i < childCount; i++) 
        {
            view = parent.getChildAt(i);
            position = parent.getChildAdapterPosition(view);
            preGroupId = groupId;
            groupId = callback.getGroupId(position);
            if (groupId.equals("-1") || groupId.equals(preGroupId)) continue;
            textLine = callback.getGroupFirstLine(position).toUpperCase();
            if (TextUtils.isEmpty(textLine)) continue;

            int viewBottom = view.getBottom();
            float textY = Math.max(topGap, view.getTop());
            //下一个和当前不一样移动当前
            if (position + 1 < itemCount)
            {
                String nextGroupId = callback.getGroupId(position + 1);
                //组内最后一个view进入了header
                if (nextGroupId != groupId && viewBottom < textY) {
                    textY = viewBottom;
                }
            }
            //textY - topGap决定了悬浮栏绘制的高度和位置
            c.drawRect(left, textY - topGap, right, textY, paint);
            //left+2*alignBottom 决定了文本往左偏移的多少（加-->向左移）
            //textY-alignBottom  决定了文本往右偏移的多少  (减-->向上移)
            c.drawText(textLine, left + 2 * alignBottom, textY - alignBottom, textPaint);
        }
    }


    //定义一个接口用来外界调用
    public interface DecorationCallback
    {
        //获取当前为止的Id
        String getGroupId(int position);
   //????
        String getGroupFirstLine(int position);
    }
}
