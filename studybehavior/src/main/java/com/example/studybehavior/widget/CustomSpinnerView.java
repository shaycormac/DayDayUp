package com.example.studybehavior.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Spinner;

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
public class CustomSpinnerView extends HorizontalScrollView 
{
    private LinearLayout llContainer;
    private Context context;
    private SpinnerOnItemSelectListener itemSelectListener;
    //控制是否有间隔线
    private boolean isDividerSpace;
    public CustomSpinnerView(Context context) {
        super(context);
        init(context, null);
    }

    public CustomSpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

   

    public CustomSpinnerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomSpinnerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) 
    {
        this.context = context;
        this.setSmoothScrollingEnabled(true);
        this.setFillViewport(true);
        llContainer = new LinearLayout(context);
        HorizontalScrollView.LayoutParams layoutParams = new HorizontalScrollView.LayoutParams(LayoutParams.WRAP_CONTENT, HorizontalScrollView.LayoutParams.MATCH_PARENT);
        llContainer.setLayoutParams(layoutParams);
        llContainer.setGravity(Gravity.CENTER_VERTICAL);
        llContainer.setOrientation(LinearLayout.HORIZONTAL);
        this.addView(llContainer);
    }
    
    
    //数据传过来
    public void setSpinnerData(Map<Object,List<Object>> spinnerMap, SpinnerOnItemSelectListener itemSelectListener)
    {
        if (spinnerMap==null || spinnerMap.isEmpty())
            return;
        if (llContainer!=null)
            llContainer.removeAllViews();
        this.itemSelectListener = itemSelectListener;
        //设置数据
        Object fatherItem;
        List<Object> sunList;
        Spinner spinner;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        params.setMargins(DensityUtil.dip2px(context,10f),0,0,0);
        ArrayAdapter<Object> spinnerAdapter;
        
        //中间的间隔线
        View dividerView;
        LinearLayout.LayoutParams dividerLayoutParams;
        
        for (Map.Entry<Object,List<Object>> entry: spinnerMap.entrySet())
        {
            fatherItem= entry.getKey();
            sunList = entry.getValue();

            //校验数据
            if (fatherItem==null||TextUtils.isEmpty(fatherItem.toString()) || sunList==null || sunList.isEmpty())
                return;
            
            spinner = new Spinner(context);
            spinner.setLayoutParams(params);
            //设置一些属性
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) 
            {
                spinner.setDropDownVerticalOffset(DensityUtil.dip2px(context, 44f));
                spinner.setDropDownWidth(DensityUtil.getDisplayMetrics(context).widthPixels);
             //   spinner.setPopupBackgroundResource(R.color.colorPrimaryDark);
            }
            spinner.setGravity(Gravity.CENTER);
            

            spinner.setOnItemSelectedListener(null);
            spinnerAdapter = new ArrayAdapter<Object>(context, R.layout.spinner_item_layout , sunList);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);
            final Object finalFatherItem = fatherItem;
            final List<Object> finalSunList = sunList;
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) 
                {
                    if (CustomSpinnerView.this.itemSelectListener!=null)
                        CustomSpinnerView.this.itemSelectListener.onItemSelected(finalFatherItem, finalSunList,view,position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent)
                {

                }
            });
            
            
            
            
            //最后添加数据
            llContainer.addView(spinner);
            //添加分割线
            if (isDividerSpace) {
                dividerView = new View(context);
                dividerLayoutParams = new LinearLayout.LayoutParams(DensityUtil.dip2px(context, 0.5f), ViewGroup.LayoutParams.MATCH_PARENT);
                dividerLayoutParams.setMargins(0, DensityUtil.dip2px(context, 10f), 0, DensityUtil.dip2px(context, 10f));
                dividerView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                dividerView.setLayoutParams(dividerLayoutParams);
                llContainer.addView(dividerView);
            }
            
        }
        
    }

    private void addDividerSpace(LinearLayout llContainer, boolean isAddDividerSpace) 
    {
        if (llContainer==null)
            return;
        if (isAddDividerSpace)
        {
            
            
        }
    }


    public interface SpinnerOnItemSelectListener
    {
        //将所要的值传出去
        void onItemSelected(Object fatherItem, List<Object> sunList, View view, int position);
    }
    
}
