package com.example.somemyidea.entity;

/**
 * @author 作者： Android2(范方方)
 * @datetime 创建时间：2016-11-24 10:12 GMT+8
 * @email 邮箱： 574583006@qq.com
 */
public class City
{
    public String id;
    public String name;

    public City(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
