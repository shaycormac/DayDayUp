package com.example.somemyidea.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Shay-Patrick-Cormac on 2016/11/20 ${Time}.
 */

public class ToastUtils
{
    private static Toast mToast;
    private static ToastUtils sToastUtils;
    //间隔时间
    private long lastTime;
    private ToastUtils()
    {
    }
    public static  ToastUtils getInstance ()
    {
        //双重锁严查
        if (sToastUtils==null)
        {
            synchronized (ToastUtils.class)
            {
                if (sToastUtils==null)
                    sToastUtils = new ToastUtils();
            }
        }

        return sToastUtils;

    }

    public void showToast(CharSequence charSequence,Context context)
    {
        if (mToast==null) {
            mToast=Toast.makeText(context, charSequence, Toast.LENGTH_LONG);
            mToast.show();
        }
        else
        {
            mToast.setText(charSequence);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.show();
        }


    }

    public  void  cancelToast()
    {
        if (mToast == null)
            return;
        mToast.cancel();
    }
}
