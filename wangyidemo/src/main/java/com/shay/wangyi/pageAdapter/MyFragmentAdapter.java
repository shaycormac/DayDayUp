package com.shay.wangyi.pageAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-04-05 14:27 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的目的，意义。
 */
public class MyFragmentAdapter extends FragmentPagerAdapter 
{
    private List<?> mFragment;
    private List<String> mTitleList;
    public MyFragmentAdapter(FragmentManager fm,List<?> mFragment,List<String> mTitleList) 
    {
        super(fm);
        this.mFragment = mFragment;
        this.mTitleList = mTitleList;
    }


    public MyFragmentAdapter(FragmentManager fm,List<?> mFragment)
    {
        super(fm);
        this.mFragment = mFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) mFragment.get(position);
    }

    @Override
    public int getCount() 
    {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        if (mTitleList != null) {
            return mTitleList.get(position);
        } else {
            return "";
        }
    }
}
