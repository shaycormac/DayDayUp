package com.example.mvparchitecturestudy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.VolleyLog;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/2/15 22:08
 * @email 邮箱： 574583006@qq.com
 * @content 说明：基类Activity,项目中所有都继承于它，承载很多方法和内容。
 */
public abstract class BaseActivity extends AppCompatActivity 
{
    protected Activity activity;

    /**
     * 显示进度
     */
    private ProgressDialog mDialog;
    private int mDialogCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        init();
        linkUi();
        loadData();
    }

    public abstract void init();

    public abstract void linkUi();

    /**
     * 加载数据
     */
    public abstract void loadData();


    public void showProgress(String title, String message) {

        mDialogCount++;
        VolleyLog.v("------------------» showProgress[%d]", mDialogCount);

        if (null == activity || activity.isFinishing() || mDialogCount > 1 || mDialog != null)
            return;

        mDialog = ProgressDialog.show(activity, title, message, true, true );

    }


    public void showProgress() {
        showProgress("正在获取内容", "请稍候...");
    }

    public void dismissProgress() {

        mDialogCount--;
        VolleyLog.v("------------------» dismissProgress[%d]", mDialogCount);

        if (dialogShowing() || mDialog == null) {
            return;
        }

        try {
            mDialog.dismiss();
            mDialog = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean dialogShowing() {
        return mDialogCount > 0;
    }
}
