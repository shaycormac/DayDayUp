package com.example.designpatterstudy.abstractFactoryDesign;

import android.os.Handler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/1/1 23:12
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的意义，目的。
 */
public class OkhttpRequestManager implements InternetRequestManager 
{
    //请求实体参数类型
    public static final MediaType TYPE_JSON = MediaType.parse("application/json,charset=utf-8");
    private OkHttpClient okHttpClient;
    private Handler handler;
    public static OkhttpRequestManager getInstance()
    {
        return OkhttpHolder.instance;
    }

    private OkhttpRequestManager() 
    {
        okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build();
        handler = new Handler();
    }

    private static class OkhttpHolder
    {
        private final static OkhttpRequestManager instance = new OkhttpRequestManager();
    }
    @Override
    public void get(String url, InternetRequestCallback callback)
    {
        Request request = new Request.Builder().url(url).get().build();
        addCallback(request,callback);
    }

    @Override
    public void post(String url, String requestBodyJson, InternetRequestCallback callback)
    {
        RequestBody requestBody = RequestBody.create(TYPE_JSON, requestBodyJson);
        Request request = new Request.Builder().url(url).post(requestBody).get().build();
        addCallback(request, callback);
    }
    
    private void addCallback(Request request, final InternetRequestCallback callback)
    {
        okHttpClient.newCall(request).enqueue(new Callback() 
        {
            @Override
            public void onFailure(Call call, final IOException e)
            {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailture(e);
                    }
                });
                
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException 
            {
                if (response.isSuccessful())
                {
                    final String responseJson = response.body().toString();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                         callback.onSuccess(responseJson);
                        }
                    });
                    
                }else 
                {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                      callback.onFailture(new IOException(response.message() + ",url=" + call.request().url().toString()));
                        }
                    });
                }

            }
        });
    }
}
