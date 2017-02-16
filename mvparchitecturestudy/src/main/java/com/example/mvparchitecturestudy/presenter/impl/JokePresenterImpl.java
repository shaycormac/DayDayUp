package com.example.mvparchitecturestudy.presenter.impl;

import com.example.mvparchitecturestudy.entity.Joke;
import com.example.mvparchitecturestudy.model.JokeModel;
import com.example.mvparchitecturestudy.model.impl.JokeModelImpl;
import com.example.mvparchitecturestudy.presenter.JokePresenter;
import com.example.mvparchitecturestudy.presenter.OnJokeListener;
import com.example.mvparchitecturestudy.ui.JokeView;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/2/15 23:11
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的意义，目的。
 */
public class JokePresenterImpl implements JokePresenter,OnJokeListener 
{
    //协调作用，持有model和view层的引用，和view层是双向的
    private JokeView jokeView;
    private JokeModel jokeModel = new JokeModelImpl();

    public JokePresenterImpl(JokeView jokeView) {
        this.jokeView = jokeView;
    }

    /**
     * 该方法作为相当于通信
     * @param pNum
     * @param pSize
     */
    @Override
    public void getJoke(String pNum, String pSize) 
    {
        //开始请求页数
        jokeView.showLoading();
        //让model层去做这件事(this很重要，就是这个函数本身，实现了listener的方法)
        jokeModel.getJoke(pNum,pSize,this);
        
    }

    /**
     * 从model层回调的数据
     * @param joke
     */
    @Override
    public void onSuccess(Joke joke) 
    {
        jokeView.hideLoading();
        //数据传出去
        jokeView.setJoke(joke);
        
    }

    @Override
    public void onFailure() {
        jokeView.hideLoading();
        //显示错误压面
        jokeView.showError();

    }
}
