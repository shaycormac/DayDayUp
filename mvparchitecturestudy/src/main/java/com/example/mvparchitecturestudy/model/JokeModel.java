package com.example.mvparchitecturestudy.model;

import com.example.mvparchitecturestudy.presenter.OnJokeListener;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/2/15 22:50
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的意义，目的。
 */
public interface JokeModel 
{
    /**
     * 从网络获取值，得到后传给Presenter
     * @param pNum
     * @param pSize
     * @param onJokeListener
     */
    void getJoke(String pNum, String pSize, OnJokeListener onJokeListener);
}
