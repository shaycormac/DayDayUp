package com.example.designpatterstudy.singtonDesign;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-03-22 16:03 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：
 * 上面的类Resource是我们要应用单例模式的资源，具体可以表现为网络连接，数据库连接，线程池等等。 
获取资源的方式很简单，只要 SomeThing.INSTANCE.getInstance() 即可获得所要实例。下面我们来看看单例是如何被保证的： 
首先，在枚举中我们明确了构造方法限制为私有，在我们访问枚举实例时会执行构造方法，同时每个枚举实例都是static final类型的，也就表明只能被实例化一次。在调用构造方法时，我们的单例被实例化。 
也就是说，因为enum中的实例被保证只会被实例化一次，所以我们的INSTANCE也被保证实例化一次。 
可以看到，枚举实现单例还是比较简单的，除此之外我们再来看一下Enum这个类的声明：
 */
public enum EnumSington 
{
    INSTANCE;
    private SingleClass singleClass;

    EnumSington() 
    {
        //初始化你想要单例的对象
        singleClass = new SingleClass();
    }
    public SingleClass getInstance()
    {
        return singleClass;
    }
    
}
