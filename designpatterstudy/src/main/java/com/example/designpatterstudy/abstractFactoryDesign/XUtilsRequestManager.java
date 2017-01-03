package com.example.designpatterstudy.abstractFactoryDesign;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/1/2 00:16
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的意义，目的。
 */
public class XUtilsRequestManager implements InternetRequestManager
{
    //单例
    public static XUtilsRequestManager getInstance()
    {
        return XUtilsHolder.instance;
    }

    private XUtilsRequestManager() {
    }
    private static class XUtilsHolder
    {
        private static final XUtilsRequestManager instance = new XUtilsRequestManager();
    }

    @Override
    public void get(String url, final InternetRequestCallback callback)
    {
        RequestParams requestParams = new RequestParams(url);
       // requestParams.addQueryStringParameter("name", "name");
        x.http().get(requestParams, new Callback.CommonCallback<String>() 
        {
            @Override
            public void onSuccess(String result)
            {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
             callback.onFailture(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void post(String url, String requestBodyJson, final InternetRequestCallback callback) 
    {
        RequestParams params = new RequestParams(url);
        //处理requestBodyJson字符串
       // params.addBodyParameter();
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onFailture(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}
