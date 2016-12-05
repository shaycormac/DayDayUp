package com.example.studybehavior.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
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

import java.util.List;
import java.util.Map;

/**
 * @author 作者： Android2(范方方)
 * @datetime 创建时间：2016-11-23 16:35 GMT+8
 * @email 邮箱： 574583006@qq.com
 * 自定义Spinner控件。
 */
public class CustomTabSelectorView extends HorizontalScrollView 
{
    private LinearLayout llContainer;
    private Context context;
    private SpinnerOnItemSelectListener itemListener;
    //控制是否有间隔线
    private boolean isDividerSpace;
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
    
    
    //数据传过来
    public <E,T>void setSpinnerData(Map<E,List<T>> spinnerMap, SpinnerOnItemSelectListener<E,T> itemSelectListener)
    {
        if (spinnerMap==null || spinnerMap.isEmpty())
            return;
        if (llContainer!=null)
            llContainer.removeAllViews();
        this.itemListener = itemSelectListener;
        //设置数据
        E fatherItem;
        List<T> sunList;
     //   Spinner spinner;
        Button button;
        ListPopupWindow listPopupWindow;
        int screenWidth = DensityUtil.getDisplayMetrics(context).widthPixels;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
      //  params.setMargins(DensityUtil.dip2px(context,10f),0,0,0);
        ArrayAdapter<T> buttonAdpter;
        
        //中间的间隔线
        View dividerView;
        LinearLayout.LayoutParams dividerLayoutParams;
        
        for (Map.Entry<E,List<T>> entry: spinnerMap.entrySet())
        {
            fatherItem= entry.getKey();
            sunList = entry.getValue();

            //校验数据
            if (fatherItem==null||TextUtils.isEmpty(fatherItem.toString()) || sunList==null || sunList.isEmpty())
                return;
            listPopupWindow = new ListPopupWindow(context);
            listPopupWindow.setModal(true);
            listPopupWindow.setWidth(screenWidth);
            listPopupWindow.setHeight((int) (1.2*screenWidth));
          //  spinner = new Spinner(context);
         //   spinner.setLayoutParams(params);
            button = new Button(context);
            button.setLayoutParams(params);
            button.setGravity(Gravity.CENTER);
            button.setText(fatherItem.toString());
            button.setTag(listPopupWindow);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
                button.setBackgroundDrawable(getRippleDrawable());
            else
                button.setBackground(getRippleDrawable());
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) 
                {
                    ListPopupWindow listPopupWindow1 = (ListPopupWindow) v.getTag();
                    if (listPopupWindow1!=null)
                        listPopupWindow1.show();
                }
            });
           
            listPopupWindow.setAnchorView(button);
            //设置一些属性
           /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) 
            {
                spinner.setDropDownVerticalOffset(DensityUtil.dip2px(context, 44f));
                spinner.setDropDownWidth(DensityUtil.getDisplayMetrics(context).widthPixels);
             //   spinner.setPopupBackgroundResource(R.color.colorPrimaryDark);
            }
            spinner.setGravity(Gravity.CENTER);
            

            spinner.setOnItemSelectedListener(null);*/
            buttonAdpter = new ArrayAdapter<T>(context, R.layout.list_item_group_filter, sunList);
          //  buttonAdpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            listPopupWindow.setAdapter(buttonAdpter);
            final E finalFatherItem = fatherItem;
            final List<T> finalSunList = sunList;
            final ListPopupWindow finalListPopupWindow = listPopupWindow;
            listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    finalListPopupWindow.dismiss();
                    if (itemListener!=null)
                        itemListener.onItemSelected(view,position,finalFatherItem, finalSunList);
                    
                }
            });
           /* spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) 
                {
                    if (itemListener!=null)
                        itemListener.onItemSelected(view,position,finalFatherItem, finalSunList);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent)
                {

                }
            });*/
            
            
            
            
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
    
    public interface SpinnerOnItemSelectListener<E,T>
    {
        //将所要的值传出去
        void onItemSelected(View view, int position, E e, List<T> t);
    }


    private void setViewRippleBackground(View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
            view.setBackgroundDrawable(getRippleDrawable());
        else
            view.setBackground(getRippleDrawable());
    }

    private Drawable getRippleDrawable() {
        int[] attrs = new int[]{android.R.attr.selectableItemBackground};
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs);
        // Drawable held by attribute 'selectableItemBackground' is at index '0'
        Drawable d = a.getDrawable(0);
        a.recycle();
        return d;
    }
    
}
