package com.example.designpatterstudy.abstractFactoryDesign;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/1/1 22:35
 * @email 邮箱： 574583006@qq.com
 * @content 说明：工厂类，返回网络请求实体。
 */
public class RequestFactory 
{
    public static InternetRequestManager getInstance()
    {
     //   return VolleyRequestManager.getInstance();
       // return OkhttpRequestManager.getInstance();
        return XUtilsRequestManager.getInstance();
    }
    
}
