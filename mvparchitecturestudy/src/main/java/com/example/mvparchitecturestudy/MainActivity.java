package com.example.mvparchitecturestudy;

import android.util.Log;
import android.widget.Toast;

import com.example.mvparchitecturestudy.entity.Joke;
import com.example.mvparchitecturestudy.entity.JokeInfo;
import com.example.mvparchitecturestudy.presenter.JokePresenter;
import com.example.mvparchitecturestudy.presenter.impl.JokePresenterImpl;
import com.example.mvparchitecturestudy.ui.JokeView;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements JokeView {

    // 不做分页加载的操作，所以这两个参数写死
    public static final String PAGE_NUM = "1";

    public static final String PAGE_SIZE = "20";
    //持有Presenter的引用，和presenter互相引用
    private JokePresenter presenter;
    //获取的数据
    private ArrayList<JokeInfo> mJokeInfoArrayList;

  

    @Override
    public void init() {
        //得到主持人
        presenter = new JokePresenterImpl(this);
        mJokeInfoArrayList = new ArrayList<>();
    }

    @Override
    public void linkUi() {
        setContentView(R.layout.activity_main);
    }

    /**
     * 加载数据
     */
    @Override
    public void loadData() {

        //通知present加载数据
        presenter.getJoke(PAGE_NUM,PAGE_SIZE);
    }


    //以下方法通过JokeView调用
    @Override
    public void showLoading() 
    {
     showProgress();
    }

    @Override
    public void hideLoading() {
   dismissProgress();
    }

    //得到数据
    @Override
    public void setJoke(Joke joke)
    {
        if (joke != null) {
            Joke.Result result = joke.mResult;
            if (result != null) {
                ArrayList<JokeInfo> jokeInfoArrayList = result.mJokeInfoArrayList;
                mJokeInfoArrayList.addAll(jokeInfoArrayList);
              //  mJokeAdapter.notifyDataSetChanged();
                Log.i("从网络上获取的职位：：", mJokeInfoArrayList.toString());
            }
        }

    }

    @Override
    public void showError()
    {
        Toast.makeText(activity, "错误了", Toast.LENGTH_SHORT).show();
    }


   
}
