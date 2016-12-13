package com.example.studybehavior.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;

import com.example.studybehavior.R;
import com.example.studybehavior.utils.DensityUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 作者： Android2(范方方)
 * @datetime 创建时间：2016-11-23 16:35 GMT+8
 * @email 邮箱： 574583006@qq.com
 * 
 */
public class CustomTabSelectorView extends HorizontalScrollView 
{
    private LinearLayout llContainer;
    private Context context;
    private tabOnItemSelectListener itemListener;
    //控制是否有间隔线
    private boolean isDividerSpace;
    //记住各个tab位置上点击的item位置
    private  Map mapPosition;
    //接收传过来的数据
    private  Map mDataMap;
    public CustomTabSelectorView(Context context) {
        super(context);
        init(context, null);
    }

    public CustomTabSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

   

    public CustomTabSelectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomTabSelectorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) 
    {
        this.context = context;
        this.setSmoothScrollingEnabled(true);
        this.setFillViewport(true);
        llContainer = new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        llContainer.setLayoutParams(layoutParams);
        llContainer.setGravity(Gravity.CENTER_VERTICAL);
        llContainer.setOrientation(LinearLayout.HORIZONTAL);
        this.addView(llContainer);
    }


    /**
     * 设置数据
     * @param dataMap 传入的参数，要求key和value实体重写toString方法
     * @param itemSelectListener 回调返回的数据
     * @param <E>
     * @param <T>
     */
    public <E,T>void setData(Map<E,List<T>> dataMap, tabOnItemSelectListener<E,T> itemSelectListener)
    {
        if (dataMap==null || dataMap.isEmpty())
            return;
        this.mDataMap = dataMap;
        if (llContainer!=null)
            llContainer.removeAllViews();
        this.itemListener = itemSelectListener;
        //设置数据
        E fatherItem;
        List<T> sunList;
        Button button;
        ListPopupWindow listPopupWindow;
        int screenWidth = DensityUtil.getDisplayMetrics(context).widthPixels;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        ArrayAdapter<T> buttonAdapter;
        
        //中间的间隔线
        View dividerView;
        LinearLayout.LayoutParams dividerLayoutParams;
        
        //添加位置
        mapPosition = new LinkedHashMap<E,Integer>();
        for (Map.Entry<E,List<T>> entry: dataMap.entrySet())
        {
            fatherItem= entry.getKey();
            sunList = entry.getValue();

            //校验数据(考虑有的list可能为null,取key作为list的值)
            if (fatherItem==null||TextUtils.isEmpty(fatherItem.toString()))
                continue;
            if (sunList==null || sunList.isEmpty()) 
            {
                sunList = new ArrayList<>();
            }
            final E finalFatherItem = fatherItem;
            final List<T> finalSunList = sunList;
            listPopupWindow = new ListPopupWindow(context);
            final ListPopupWindow finalListPopupWindow = listPopupWindow;
            listPopupWindow.setModal(true);
            listPopupWindow.setWidth(screenWidth);
            listPopupWindow.setHeight(screenWidth);
            button = new Button(context);
            button.setLayoutParams(params);
            button.setGravity(Gravity.CENTER);
            button.setText(fatherItem.toString());
            button.setTag(R.id.tag_list_popwindow,listPopupWindow);
            button.setTag(fatherItem);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
                button.setBackgroundDrawable(getRippleDrawable());
            else
                button.setBackground(getRippleDrawable());
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) 
                {
                    //传过来的集合为空，不显示popWindow。
                    if (finalSunList==null || finalSunList.isEmpty())
                        return;
                    ListPopupWindow listPopupWindow1 = (ListPopupWindow) v.getTag(R.id.tag_list_popwindow);
                    if (listPopupWindow1!=null)
                        listPopupWindow1.show();
                }
            });
           
            listPopupWindow.setAnchorView(button);
            buttonAdapter = new ArrayAdapter<T>(context, R.layout.list_item_group_filter, sunList)
            {
                @NonNull
                @Override
                public View getView(int position, View convertView, ViewGroup parent) 
                {
                    convertView = super.getView(position, convertView, parent);
                    Integer getPosition = (Integer) mapPosition.get(finalFatherItem);
                    if (position == (getPosition==null?0:getPosition)) 
                    {
                        convertView.setEnabled(false);
                    } else {
                        convertView.setEnabled(true);
                    }
                    return convertView;
                }
            };
            listPopupWindow.setAdapter(buttonAdapter);
            button.setTag(R.id.tag_array_adapter,buttonAdapter);
            listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
                {
                    finalListPopupWindow.dismiss();
                    mapPosition.put(finalFatherItem, position);
                    if (itemListener!=null)
                        itemListener.onItemSelected(view,position,finalFatherItem, finalSunList.get(position));
                }
            });
            //最后添加数据
            llContainer.addView(button);
            //添加分割线
                dividerView = new View(context);
                dividerLayoutParams = new LinearLayout.LayoutParams(DensityUtil.dip2px(context, 0.5f), ViewGroup.LayoutParams.MATCH_PARENT);
                dividerLayoutParams.setMargins(0, DensityUtil.dip2px(context, 10f), 0, DensityUtil.dip2px(context, 10f));
                dividerView.setBackgroundColor(context.getResources().getColor(R.color.divider_grey));
                dividerView.setLayoutParams(dividerLayoutParams);
                llContainer.addView(dividerView);
        }
        
    }
    
    public interface tabOnItemSelectListener<E,T>
    {
        /**
         * 回调的方法
         * @param view 当前点击popwindow上对应的View
         * @param position 当前点击popwindow上对应的位置
         * @param e 当前点击popwindow对应tab上的实体
         * @param t 当前点击popwindow对应位置上的实体
         */
        void onItemSelected(View view, int position, E e, T t);
    }
    
    /**
     * 更新数据（map里面的value值更新，key不更新，需要传一个map过来）
     * @param key
     * @param tList
     * @param <E>
     * @param <T>
     */
    public <E,T>void updateView(E key,List<T> tList)
    {
        if (this.mDataMap==null || this.mDataMap.isEmpty()||key==null)
            return;
          if (mDataMap.containsKey(key) && llContainer!=null)
          {
                  Button button = (Button) llContainer.findViewWithTag(key);
                  if (button!=null)
                  {
                      ArrayAdapter<T> adapter = (ArrayAdapter<T>) button.getTag(R.id.tag_array_adapter);
                      adapter.clear();
                      if (tList!=null)
                      adapter.addAll(tList);
                     adapter.notifyDataSetChanged();
                  }
          }
    }
    /**
     * 更新数据源，传过来一个map集合。
     * @param updateMap
     * @param <E>
     * @param <T>
     */
    public<E,T> void updateData(Map<E,List<T>> updateMap)
    {
        if (updateMap==null || updateMap.isEmpty()||this.mDataMap==null || this.mDataMap.isEmpty())
            return;
        E e;
        List<T> tList;
        for (Map.Entry<E,List<T>> entry: updateMap.entrySet())
        {
             e = entry.getKey();
            tList = entry.getValue();
            if (e==null||TextUtils.isEmpty(e.toString()))
                continue;
            if (mDataMap.containsKey(e) && llContainer!=null)
            {
                Button button = (Button) llContainer.findViewWithTag(e);
                if (button!=null)
                {
                    ArrayAdapter<T> adapter = (ArrayAdapter<T>) button.getTag(R.id.tag_array_adapter);
                    adapter.clear();
                    if (tList!=null)
                    adapter.addAll(tList);
                    adapter.notifyDataSetChanged();
                }
            }
        }
        
    }
    
    private Drawable getRippleDrawable() {
        int[] attrs = new int[]{android.R.attr.selectableItemBackground};
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs);
        Drawable d = a.getDrawable(0);
        a.recycle();
        return d;
    }
    
}
