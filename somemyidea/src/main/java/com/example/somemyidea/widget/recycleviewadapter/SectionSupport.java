package com.example.somemyidea.widget.recycleviewadapter;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2016-12-15 11:17 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：添加分类header，其实就是我们多种ItemViewType的一种。
 * header所对应的布局文件
  显示header的title对应的TextView
  显示的title是什么（一般肯定根据Bean生成）
  引入一个接口，用于提供上述3各参数
 */
public interface SectionSupport<T> {
   int sectionHeaderLayoutId();

    int sectionTitleTextViewId();

    String getTitle(T t);
}
