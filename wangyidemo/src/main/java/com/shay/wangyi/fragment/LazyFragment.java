package com.shay.wangyi.fragment;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.shay.wangyi.R;


/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/4/2 21:46
 * @email 邮箱： 574583006@qq.com
 * @content 说明：Fragment懒加载。（用于viewpager+fragment中，譬如进入相应的activity，初始化3个fragment,只有当前可见的
 * fragment才走onCreateView,不可见的仅仅是创建了fragment对象，但是却不执行onCreateView（网络请求神马的都在里面执行））
 */
public abstract class LazyFragment extends Fragment 
{
    protected FrameLayout frameLayout;
    private boolean hasLoaded = false;  //标识是否已经加载过
    private boolean hasCreated = false; //标识onCreateView是否已调用
    private boolean needInit = false;   //标识是否需要在onCreateView中
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("lazy_load", "LazyFragment onCreateView. ");

        View rootView = inflater.inflate(R.layout.fragment_lazy, container, false);
        frameLayout = getView(rootView, R.id.fragment_lazy_content_wrapper);

        if(needInit) {
            initWrapper();
            needInit = false;
        }
        hasCreated = true;
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("lazy_load", "LazyFragment setUserVisibleHint "+isVisibleToUser);

        if (isVisibleToUser && !hasLoaded) {    //如果当前Fragment向用户展示且没有加载过，进行下一步判断
            if (hasCreated) {   //如果onCreateView已经被创建，此时进行加载
                initWrapper();
            } else {        //如果Fragment没有创建，那么设置标记，在onCreateView中加载
                needInit = true;
            }
        }
    }

    /**
     * 此函数开始数据加载的操作，且仅调用一次
     */
    private void initWrapper() {
        LayoutInflater.from(getContext()).inflate(setContentView(), frameLayout);
        initialize();
        hasLoaded = true;
    }
    /**
     * 子类fragment设置布局，返回fragment要设定的布局
     * @return 子类fragment要显示的布局
     */
    @LayoutRes
    protected abstract int setContentView();

    /**
     * 供子类使用，子类fragment初始化操作，此函数内部真正开始进行页面的一些列操作
     */
    protected abstract void initialize();
    
    @SuppressWarnings("unchecked")
    @CheckResult
    public <T extends View> T getView(@NonNull View view, @IdRes int id) 
    {
        return (T) view.findViewById(id);
    }


}
