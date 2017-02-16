package com.example.mvparchitecturestudy.presenter;

import com.example.mvparchitecturestudy.entity.Joke;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/2/15 22:48
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的意义，目的。
 */
public interface OnJokeListener 
{
    void onSuccess(Joke joke);

    void onFailure();
}
