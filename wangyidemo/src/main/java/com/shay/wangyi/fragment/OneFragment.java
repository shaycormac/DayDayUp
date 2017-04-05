package com.shay.wangyi.fragment;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shay.wangyi.R;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-04-05 13:56 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的目的，意义。
 */
public class OneFragment extends LazyFragment
{
    public static final String TITLE_NAME = "title_name";
    public static final String TITLE_BACKGROUND = "title_background";
    
    
    public static OneFragment newInstance(String name,int backgroundColor)
    {
        //Bundle先不写
        Bundle bundle = new Bundle();
        bundle.putString(TITLE_NAME,name);
        bundle.putInt(TITLE_BACKGROUND, backgroundColor);
        OneFragment oneFragment = new OneFragment();
        oneFragment.setArguments(bundle);
        return oneFragment;
    }
    @Override
    protected int setContentView() 
    {
        return R.layout.fragment_one_layout;
    }

    @Override
    protected void initialize() 
    {
        Bundle bundle = getArguments();
        String name = bundle.getString(TITLE_NAME, "默认值");
        int backGround = bundle.getInt(TITLE_BACKGROUND);
        //具体相应的逻辑操作。
        FrameLayout flContainer = getView(frameLayout, R.id.flContainer);
        TextView tvName = getView(frameLayout, R.id.tvName);
        tvName.setText(name);
        flContainer.setBackgroundColor(getResources().getColor(backGround));
    }
}
