package com.example.designpatterstudy.simpleFactoryPattern;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-01-13 09:50 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：ConcreteProduct（具体产品角色）

简单工厂模式的创建目标，所有被创建的对象都充当这个角色的某个具体类的实例。
每一个具体产品角色都继承了抽象产品角色，需要实现在抽象产品中声明的抽象方法。
 */
public class Apple implements Fruit 
{
    @Override
    public void getFruitName(int type) {
        System.out.println("创造了水果");
    }
}
