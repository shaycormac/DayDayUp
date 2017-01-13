package com.example.designpatterstudy.factoryMethodPattern;

import com.example.designpatterstudy.simpleFactoryPattern.Apple;
import com.example.designpatterstudy.simpleFactoryPattern.Fruit;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-01-13 10:21 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的目的，意义。
 */
public class AppleFactory implements InterfaceFactory 
{
    @Override
    public Fruit createFruitFactory() 
    {
        return new Apple();
    }
}
