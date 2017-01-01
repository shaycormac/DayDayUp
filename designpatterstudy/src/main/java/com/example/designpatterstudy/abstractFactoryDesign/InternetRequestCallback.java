package com.example.designpatterstudy.abstractFactoryDesign;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/1/1 22:13
 * @email 邮箱： 574583006@qq.com
 * @content 说明：回调接口。
 */
public interface InternetRequestCallback
{
    void onSuccess(String response);

    void onFailture(Throwable throwable);

    /**
     * 请求成功，存数据，失败，从数据库取数据，存入这里面
     *
     * @param response
     * @param throwable
     */
    void onResponse(String response, Throwable throwable);
}
