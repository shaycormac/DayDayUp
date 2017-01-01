package com.example.designpatterstudy.abstractFactoryDesign;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/1/1 22:12
 * @email 邮箱： 574583006@qq.com
 * @content 说明：请求接口。
 */
public interface InternetRequestManager
{
    void get(String url,InternetRequestCallback callback);

    void post(String url,String requestBodyJson,InternetRequestCallback callback);
}
