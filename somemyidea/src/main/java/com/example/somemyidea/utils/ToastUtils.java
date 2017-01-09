package com.example.somemyidea.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Shay-Patrick-Cormac on 2016/11/20 ${Time}.
 */

public class ToastUtils {
    //静态的Toast，单例
    private static Toast mToast;

    private static class ToastHolder {
        private static ToastUtils instance = new ToastUtils();
    }

    private ToastUtils() {
    }

    public static ToastUtils getInstance() {
        return ToastHolder.instance;
    }

    public void showToast(Context context, CharSequence charSequence) {
        if (mToast == null) {
            mToast = Toast.makeText(context, charSequence, Toast.LENGTH_LONG);
            mToast.show();
        } else {
            mToast.setText(charSequence);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.show();
        }
        Log.v("单例", mToast.toString());
    }

    public void cancelToast() {
        if (mToast == null)
            return;
        mToast.cancel();
    }
}
