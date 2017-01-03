package com.example.designpatterstudy.abstractFactoryDesign;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.designpatterstudy.application.FactoryApplication;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/1/1 22:18
 * @email 邮箱： 574583006@qq.com
 * @content 说明：Vollery框架请求。
 */
public class VolleyRequestManager implements InternetRequestManager
{
    //单例
    public static VolleyRequestManager getInstance()
    {
        return VolleyHolder.instance;
    }

    private VolleyRequestManager() {
    }

    private static class VolleyHolder
    {
        private static final VolleyRequestManager instance = new VolleyRequestManager();
    }
    @Override
    public void get(String url, final InternetRequestCallback callback)
    {
        StringRequest request = new StringRequest(Request.Method.GET, url, 
                new Response.Listener<String>()
                {
            @Override
            public void onResponse(String response) 
            {
                //成功的回调(对获得的数据进一步的封装，处理)
                //譬如带code,msg，content的实体，进行解析JsonObject解析，处理（code如果为1，强制
                // 退出App,如果为其他的情况，就把code,msg传递出去）
                callback.onSuccess(response);
            }
        },new Response.ErrorListener()
        {

        @Override
        public void onErrorResponse(VolleyError error)
        {
            callback.onFailture(error);
          
        }
    });
        FactoryApplication.queue.add(request);
        
    }

    @Override
    public void post(String url, String requestBodyJson, final InternetRequestCallback callback)
    {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(requestBodyJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
              callback.onSuccess(response==null?null:response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) 
            {
                callback.onFailture(error);
            }
        });
        FactoryApplication.queue.add(jsonRequest);
    }
}
