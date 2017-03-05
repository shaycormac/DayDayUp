package com.example.somemyidea.utils;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.somemyidea.widget.recycleviewitemtouch.ItemTouchHelperAdapter;
import com.example.somemyidea.widget.recycleviewitemtouch.ItemTouchHelperCallback;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2016-12-16 10:44 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：RecycleView的初始化设置,单例？？
 */
public class RecycleViewSetting 
{
    private RecycleViewSetting() 
    {
    }
    public static class RecycleViewHolder
    {
        private static RecycleViewSetting recyclerViewSetting = new RecycleViewSetting();
    }
    public static RecycleViewSetting getInstance()
    {
        return RecycleViewHolder.recyclerViewSetting;
    }

    public  void initRecycleView(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter)
    {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        // 创建ItemTouchHelper对象，然后调用attachToRecyclerView(RecyclerView) 方法
        ItemTouchHelperCallback itemTouchHelperCallback = new ItemTouchHelperCallback((ItemTouchHelperAdapter) adapter);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }
}
