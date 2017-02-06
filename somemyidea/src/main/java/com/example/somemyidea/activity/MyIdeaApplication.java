package com.example.somemyidea.activity;

import android.app.Application;

import org.xutils.x;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-02-06 16:03 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的目的，意义。
 */
public class MyIdeaApplication extends Application 
{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
