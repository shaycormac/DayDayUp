package com.example.somemyidea.utils;

import android.support.v4.util.ArrayMap;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/2/12 15:38
 * @email 邮箱： 574583006@qq.com
 * @content 说明：学习谷歌推荐的一些适用于安卓的Api。
 */
public class ArrayMapUtils 
{
    private ArrayMap arrayMap;

    public ArrayMapUtils(ArrayMap arrayMap) 
    {
        this.arrayMap = arrayMap;
    }

    public  void studyArrayMap()
    {
        int length = arrayMap.size();
        for (int i = 0; i <length ; i++)
        {
            Object key = arrayMap.keyAt(i);
            Object value = arrayMap.valueAt(i);
        }
    }
}
