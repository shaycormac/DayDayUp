package com.example.designpatterstudy.simpleFactoryPattern;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-01-13 09:47 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：简单工厂模式（Simple Factory Pattern）又叫静态工厂方法模式（Static FactoryMethod Pattern），
 * 是通过专门定义一个类来负责创建其他类的实例，被创建的实例通常都具有共同的父类。当你需要什么，只需要传入一个正确的参数，
 * 就可以获取你所需要的对象，而无须知道其创建细节。
 * 不属于设计模式的一种模式–简单工厂模式。它不属于设计模式，但在软件开发中应用也较为频繁，通常将它作为学习其他工厂模式的入门。
 * 其实工厂模式分为了最弱的简单工厂模式，工厂方法模式，牛逼的抽象工厂模式。
 * 简单工厂模式不属于设计模式，是一种编码的习惯，但他又和工厂模式有些关系，
 */
public class StaticFactory 
{
    public static final int APPLE = 1;
    public static final int BANANA = 2;

    /**
     * 静态方法，得到需要的实体，再进行方法调用即可，满足基本需求
     * 而每次修改type值，违背开闭原则，
     * 使用配置文件，在FactoryCreater中传入配置文件的值即可解决这种开闭原则
     * 
     * 便得我们可以按照我们的需要更换水果，但我们所更换的水果必须是实现类中有的，
     * 如果我们想要增加一种水果的时候，我们还是得更改工厂，所以最优方法通过反射
     * @param type
     * @return
     */
    public static Fruit tellFruitName(int type)
    {
        Fruit fruit = null;
        if (type==APPLE)
            fruit = new Apple();
        else if (type==BANANA)
            fruit = new Banana();
        return fruit;
    }
    /**
     * 最优方法
     * 实例三利用反射机制，得到水果类型，这样当我们需要增加一种新的水果时，就无需要再修改工厂，
     * 而只需要增加要实现的类即可。也就是说要增加什么样的水果直接增加这个水果的类即可，而无需改变工厂。
     * 从而达到了工厂分离的效果。 
     */
    public static Fruit tellFruitNameByReflect(int type)
    {
        Fruit fruit = null;
        String className = null;
        if (type==APPLE)
            className = "Apple";
        else if (type==BANANA)
            className = "Banana";
        try {
            //利用反射得到具体水果的类型
            fruit= (Fruit) Class.forName("com.example.designpatterstudy.simpleFactoryPattern."+className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return fruit;
    }
}
