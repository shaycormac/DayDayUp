package com.example.designpatterstudy.simpleFactoryPattern;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-01-13 09:49 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：工厂类所创建的所有对象的父类，封装了各种产品对象的公有方法，它的引入将提高系统的灵活性
 * ，使得在工厂类中只需定义一个通用的工厂方法，因为所有创建的具体产品对象都是其子类对象。。
 */
public interface Fruit 
{
    void getFruitName(int type);
}
