package com.example.mvparchitecturestudy.model.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.mvparchitecturestudy.entity.Joke;
import com.example.mvparchitecturestudy.model.JokeModel;
import com.example.mvparchitecturestudy.presenter.OnJokeListener;
import com.example.mvparchitecturestudy.utils.VolleyRequest;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/2/15 22:52
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的意义，目的。
 */
public class JokeModelImpl implements JokeModel {
    public static final String REQUEST_SERVER_URL="http://api.jisuapi.com/xiaohua/text?";

    public static final String APPKEY="&appkey=9814b57c706d0a23";
    /**
     * 从网络获取值，得到后传给Presenter
     *
     * @param pNum
     * @param pSize
     * @param onJokeListener
     */
    @Override
    public void getJoke(String pNum, String pSize, final OnJokeListener onJokeListener)
    {
        if (onJokeListener==null)
            return;
        VolleyRequest.newInstance().newGsonRequest(REQUEST_SERVER_URL + "pagenum=" + pNum + "&" + "pagesize=" + pSize + "&sort=addtime" + APPKEY,
                Joke.class, new Response.Listener<Joke>() {
                    @Override
                    public void onResponse(Joke response) {
                       if (response!=null)
                           onJokeListener.onSuccess(response);
                        else 
                           onJokeListener.onFailure();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) 
                    {
                        onJokeListener.onFailure();
                    }
                });
        
    }
}
