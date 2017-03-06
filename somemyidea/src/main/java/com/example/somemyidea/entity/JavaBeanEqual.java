package com.example.somemyidea.entity;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-02-22 09:31 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：重写equal和hashcode。
 */
public class JavaBeanEqual
{
    public String name;
    public int age;

    public JavaBeanEqual(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "JavaBeanEqual{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JavaBeanEqual that = (JavaBeanEqual) o;

        if (age != that.age) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        return result;
    }
}
