package com.example.designpatterstudy.application;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.xutils.x;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/1/1 22:57
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的意义，目的。
 */
public class FactoryApplication extends Application 
{
    public static RequestQueue queue;
    @Override
    public void onCreate() 
    {
        super.onCreate();
        queue = Volley.newRequestQueue(this);
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
