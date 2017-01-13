package com.example.designpatterstudy.factoryMethodPattern;

import com.example.designpatterstudy.simpleFactoryPattern.Fruit;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-01-13 10:11 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：工厂也抽象出来。
 */
public interface InterfaceFactory 
{
    Fruit createFruitFactory();
}
