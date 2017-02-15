package com.example.mvparchitecturestudy;

import android.app.Application;

import com.example.mvparchitecturestudy.utils.VolleyRequest;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/2/15 23:29
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的意义，目的。
 */
public class JokeApplication extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyRequest.buildRequestQueue(this);
    }
}
