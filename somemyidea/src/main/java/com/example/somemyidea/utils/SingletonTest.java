package com.example.somemyidea.utils;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2016-12-15 09:21 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：单例模式的编写（系统默认给的是饿汉式，app运行的时候就直接初始化）
 * 使用这个的缺点在于：就是预先声明Singleton对象，：如果构造的单例很大，构造完又迟迟不使用，会导致资源浪费。
 * 优化：加上一个私有的静态内部类：懒汉式（避免上面的资源浪费）、线程安全、代码简单。因为java机制规定，
 * 内部类SingletonHolder只有在getInstance()方法第一次调用的时候才会被加载（实现了lazy），而且其加载过程是线程安全的（实现线程安全）。
 * 内部类加载的时候实例化一次instance。
 */
public class SingletonTest 
{
    //最优方案：使用私有静态内部类。
    private static class SingletonHolder
    {
        private static SingletonTest ourInstance = new SingletonTest();
    }

    public static SingletonTest getInstance() {
        return SingletonHolder.ourInstance;
    }

    private SingletonTest() {
    }
    //生成唯一对象后，使用这个唯一对象做一些事情
    public void doSomething()
    {
        //......
    }
}
