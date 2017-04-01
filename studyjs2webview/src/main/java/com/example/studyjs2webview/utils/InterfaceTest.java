package com.example.studyjs2webview.utils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-03-22 13:51 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的目的，意义。
 */
public interface InterfaceTest
{
    static final int HAHA = 0x00000001;
    static final int HAHB = 0x00000002;
    static final int HAHC = 0x00000003;
    
    @IntDef({HAHA,HAHB,HAHC})
    @Retention(RetentionPolicy.SOURCE)
    @interface WaHaHa{}
    
    void haha();

     void hehe();
}
