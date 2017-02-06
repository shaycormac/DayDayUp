package com.example.somemyidea.widget.recycleviewItemDecoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
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
        //悬浮栏的高度
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
            if ("".equals(dataList.get(pos).getName()))
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

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
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
        for (int i = 0; i < childCount; i++) {
            //先获取当前的ziView
            view = parent.getChildAt(i);
            //根据子View获取所在的位置
            position = parent.getChildAdapterPosition(view);
            //再获取GroupId
            groupId = callback.getGroupId(position);
            if ("-1".equals(groupId))
                return;
            //获取字符串内容？？
            textLine = callback.getGroupFirstLine(position);
            if ("".equals(textLine)) {
                top = view.getTop();
                bottom = view.getBottom();
                c.drawRect(left, top, right, bottom, paint);
            }else 
            {
                if (position==0 || isFirstInGroup(position))
                {
                    top = view.getTop() - topGap;
                    bottom = view.getBottom();
                    //绘制悬浮栏
                   // c.drawRect();
                }
            }
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
