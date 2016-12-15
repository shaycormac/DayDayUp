package com.example.somemyidea.utils;

import android.text.TextUtils;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2016-12-14 10:10 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：需要拼接的字符串，那么可以优先考虑使用StringBuffer或者StringBuilder来进行拼接，
 * 而不是加号连接符，因为使用加号连接符会创建多余的对象，拼接的字符串越长，加号连接符的性能越低。。
 * StringBuffer线程安全，但是效率相对于StringBuilder低，后者单线程使用效率更高，但是两者都要比简单的链接字符串效率更高
 */
public class StringBufferUtils
{
    public static String contactString(String... params)
    {
        StringBuffer stringBuffer = new StringBuffer();
        for (String param : params) 
        {
            if (TextUtils.isEmpty(param))
                continue;
            stringBuffer.append(param);
        }
        return stringBuffer.toString();
    }
    
    public static String contactObjectToString(Object...objects)
    {
        StringBuffer stringBuffer = new StringBuffer();
        for (Object object : objects) 
        {
            if (object==null)
                continue;
            stringBuffer.append(object);
        }
        return stringBuffer.toString();
    }
}
