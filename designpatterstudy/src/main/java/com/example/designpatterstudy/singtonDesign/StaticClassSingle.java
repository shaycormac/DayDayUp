package com.example.designpatterstudy.singtonDesign;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-03-22 16:08 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：静态内部类提供单例。
 */
public class StaticClassSingle 
{
    private SingleClass aClass;
     static class Holder
    {
       private static StaticClassSingle classSingle = new StaticClassSingle();
    }
    public static StaticClassSingle getInstanceA()
    {
        return Holder.classSingle;
    }

    private StaticClassSingle() 
    {
        aClass = new SingleClass();
    }
    public SingleClass getInstance()
    {
        return aClass;
    }
}
