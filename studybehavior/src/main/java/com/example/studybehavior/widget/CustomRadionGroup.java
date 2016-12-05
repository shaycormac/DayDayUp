package com.example.studybehavior.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.studybehavior.R;

import java.util.ArrayList;


/**自定义RadioButton
 * @author 作者： Android2(范方方)
 * @datetime 创建时间：2016-11-30 11:11 GMT+8
 * @email 邮箱： 574583006@qq.com
 */
public class CustomRadionGroup extends HorizontalScrollView {

    private RadioGroup radioGroup;
    private Context context;
    private OnCheckedChangeListener changeListener;

    public CustomRadionGroup(Context context) {
        super(context);
        init(context, null);
    }

    public CustomRadionGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomRadionGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomRadionGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) 
    {
        this.context = context;
        this.setSmoothScrollingEnabled(true);
        this.setHorizontalScrollBarEnabled(false);
        //保证数量不够占满全屏的时候，填充全屏。
        this.setFillViewport(true);
        radioGroup = new RadioGroup(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        
        radioGroup.setLayoutParams(layoutParams);
        radioGroup.setGravity(Gravity.CENTER_VERTICAL);
        radioGroup.setOrientation(LinearLayout.HORIZONTAL);
        this.addView(radioGroup);
    }
    
    public <E> void  setData(final ArrayList<E> list, final OnCheckedChangeListener<E> changeListener)
    {
        if (list==null || list.isEmpty())
            return;
        this.changeListener = changeListener;
        if (radioGroup!=null)
            radioGroup.removeAllViews();
        RadioButton radioButton = null;
        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        E e;
        for (int i = 0; i < list.size(); i++) 
        {
             e = list.get(i);
            if (TextUtils.isEmpty(e.toString()))
                continue;
            if (context instanceof Activity)
            {
                radioButton = (RadioButton) ((Activity) context).getLayoutInflater().inflate(R.layout.custom_radio_button_layout, null);
                radioButton.setText(e.toString());
                radioButton.setId(i);
                radioButton.setLayoutParams(layoutParams);
                radioButton.setGravity(Gravity.CENTER);
                radioGroup.addView(radioButton);
            }
            if (i==0) {
                radioButton.setChecked(true);
                radioButton.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.mipmap.icon_tab_selected);
            }
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton radioButton1 = null;
                for (int i=0;i<group.getChildCount();++i)
                {
                    radioButton1 = (RadioButton) group.getChildAt(i);
                    if (i==checkedId)
                    radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.mipmap.icon_tab_selected);
                    else 
                    radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                }
                //checkId正好是list的postion
                if (CustomRadionGroup.this.changeListener!=null)
                {
                    CustomRadionGroup.this.changeListener.onCheckedChanged(group.getChildAt(checkedId),checkedId,list.get(checkedId));
                }
            }
            
        });
    }
    public interface OnCheckedChangeListener <E>
    {
        void onCheckedChanged(View view, int position,E e );
    }
}

