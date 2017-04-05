package com.shay.wangyi.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.shay.wangyi.R;
import com.shay.wangyi.pageAdapter.MyFragmentAdapter;

import java.util.ArrayList;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-04-05 14:42 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的目的，意义。
 */
public class BookFragment extends LazyFragment {
    private ArrayList<String> mTitleList = new ArrayList<>(3);
    private ArrayList<Fragment> mFragments = new ArrayList<>(3);
    @Override
    protected int setContentView() 
    {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initialize() 
    {
        TabLayout tabLayout = getView(frameLayout, R.id.tab_gank);
        ViewPager viewPager = getView(frameLayout, R.id.vp_gank);
        initFragmentList();
        /**
         * 注意使用的是：getChildFragmentManager，
         * 这样setOffscreenPageLimit()就可以添加上，保留相邻3个实例，切换时不会卡
         * 但会内存溢出，在显示时加载数据
         */
        MyFragmentAdapter adapter = new MyFragmentAdapter(getChildFragmentManager(), mFragments, mTitleList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initFragmentList()
    {
        mTitleList.add("总和");
        mTitleList.add("文学");
        mTitleList.add("修养");
        mFragments.add(OneFragment.newInstance(mTitleList.get(0),R.color.colorItemBackground));
        mFragments.add(OneFragment.newInstance(mTitleList.get(1),R.color.colorBtDialog));
        mFragments.add(OneFragment.newInstance(mTitleList.get(2),R.color.colorAccent));
//        mFragments.add(AndroidFragment.newInstance("iOS"));
    }
}
