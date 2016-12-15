package com.example.somemyidea.widget.recycleviewadapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2016-12-15 11:19 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：分类header是多种ItemViewType的一种，那么直接继承MultiItemCommonAdapter实现。
 */
public abstract class SectionAdapter<T> extends MultiItemCommonAdapter<T> {
    private SectionSupport sectionSupport;
    private static final int TYPE_SECTION = 0;
    private LinkedHashMap<String, Integer> sections;

    private MultiItemTypeSupport<T> headItemTypeSupport = new MultiItemTypeSupport<T>() {
        @Override
        public int getLayoutId(int itemType) {
            return itemType == TYPE_SECTION ? sectionSupport.sectionHeaderLayoutId() : layOutId;
        }

        @Override
        public int getItemViewType(int position, T t) {
            return sections.values().contains(position) ? TYPE_SECTION : 1;
        }
    };

    @Override
    public int getItemViewType(int position) {
        return multiItemTypeSupport.getItemViewType(position,null);
    }

    //监测数据变化
    RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged()
        {
            super.onChanged();
            findSections();
        }
    };
    
    private void findSections()
    {
        int count = listItems.size();
        int nSection = 0;
    sections.clear();
        for (int i = 0; i < count; i++)
        {
            String sectionName = sectionSupport.getTitle(listItems.get(i));
            if (!sections.containsKey(sectionName))
            {
                sections.put(sectionName, i + nSection);
                nSection++;
            }
        }
    }

   /* @Override
    protected boolean isEnabled(int viewType)
    {
        if (viewType == TYPE_SECTION)
            return false;
        return super.isEnabled(viewType);
    }*/

    public SectionAdapter(Context context, List<T> listItems, SectionSupport sectionSupport,int layoutId) {
        super(context, listItems, null);
        this.layOutId = layoutId;
        multiItemTypeSupport = headItemTypeSupport;
        this.sectionSupport = sectionSupport;
        sections = new LinkedHashMap<>();
        findSections();
        //todo 数据绑定
        registerAdapterDataObserver(observer);
    }

    
    //数据的解除绑定
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        unregisterAdapterDataObserver(observer);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount()+sections.size();
    }
    //重新赋值
    private int getIndexForPostion(int position)
    {
        int nSection = 0;
        Set<Map.Entry<String,Integer>> entrySet=sections.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            if (entry.getValue()<position)
            {
                nSection++;
            }
        }
        return position - nSection;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) 
    {
        position = getIndexForPostion(position);
        if (holder.getItemViewType()==TYPE_SECTION)
        {
            holder.setText(sectionSupport.sectionTitleTextViewId(), sectionSupport.getTitle(listItems.get(position)));
            return;
        }
        super.onBindViewHolder(holder, position);
    }

    //保证分类header在gridView中位置占据一行
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) 
    {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
            {

                @Override
                public int getSpanSize(int position)
                {
                    return getItemViewType(position) == TYPE_SECTION ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }
}
