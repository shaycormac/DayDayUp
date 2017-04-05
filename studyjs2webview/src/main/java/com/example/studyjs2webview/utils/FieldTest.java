package com.example.studyjs2webview.utils;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-03-22 14:03 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的目的，意义。
 */
public class FieldTest
{
    public int publicA;
    protected int protectedB;
    int c;
    private int privateD;
    private String name;
    private SunFieldTest sunFieldTest;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldTest fieldTest = (FieldTest) o;

        if (publicA != fieldTest.publicA) return false;
        if (protectedB != fieldTest.protectedB) return false;
        if (c != fieldTest.c) return false;
        if (privateD != fieldTest.privateD) return false;
        if (!name.equals(fieldTest.name)) return false;
        return sunFieldTest.equals(fieldTest.sunFieldTest);

    }

    @Override
    public int hashCode() {
        int result = publicA;
        result = 31 * result + protectedB;
        result = 31 * result + c;
        result = 31 * result + privateD;
        result = 31 * result + name.hashCode();
        result = 31 * result + sunFieldTest.hashCode();
        return result;
    }

    public void testHAHa()
    {
        
    }
    
    public static void CaoniMa()
    {
        
    }
    
}

class SunFieldTest extends FieldTest
{
    public void test()
    {
        publicA = 5;
        protectedB = 6;
        c = 7;
    }

    @Override
    public void testHAHa() 
    {
        super.testHAHa();
    }
   
}
