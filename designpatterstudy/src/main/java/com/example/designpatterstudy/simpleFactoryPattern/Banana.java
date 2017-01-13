package com.example.designpatterstudy.simpleFactoryPattern;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-01-13 09:51 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的目的，意义。
 */
public class Banana implements Fruit {
    @Override
    public void getFruitName(int type) {
        System.out.println("香蕉");
    }
}
