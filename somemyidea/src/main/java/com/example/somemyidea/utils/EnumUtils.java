package com.example.somemyidea.utils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/3/5 11:39
 * @email 邮箱： 574583006@qq.com
 * @content 说明：枚举是可以用的，不是因为内存开销大，使用注解。
 * 使用的正确姿势
 */
public class EnumUtils
{
    public static final int ONE = 0x00000001;
    public static final int TWO = 0x00000002;
    public static final int THREE =0x00000003;
    public static final int FOUR = 0x00000004;
    public static final int FIVE = 0x00000005;
    @IntDef({ONE,TWO,THREE,FOUR,FIVE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EnumTest
    {
        
    }
    
    public int chooseEnum(@EnumTest int type)
    {
        switch (type)
        {
            case ONE:
                return ONE;
            default:
                return TWO;
        }
    }
    
    
}
