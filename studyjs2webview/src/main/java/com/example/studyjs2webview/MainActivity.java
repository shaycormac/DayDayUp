package com.example.studyjs2webview;

import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.studyjs2webview.activity.GestureStudyActivity;
import com.example.studyjs2webview.activity.Js2JavaActivity;
import com.example.studyjs2webview.activity.SharePreferenceActivity;

import java.util.Arrays;
import com.example.studyjs2webview.activity.CoordinatorLayoutActivity;

/**
 * User: 炳华儿(574583006@qq.com)
 * Date: ${YEAR}-${MONTH}-${DAY}
 * Time: ${HOUR}:${MINUTE}
 * FIXME 学习简单的js和原生方法交互。
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "图片压缩";
    TextView textViewHahah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewHahah = (TextView) findViewById(R.id.textViewHahah);
        textViewHahah.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

    }


    //在activity的onContentChange方法也是可以findById的，见源码可知

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        findViewById(R.id.btnStudyJs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewTreeObserverActivity.class));
                startActivity(new Intent(MainActivity.this, Js2JavaActivity.class));
            }
        });
        int[] array = new int[]{1, 9, 4, 23, 55};
        findViewById(R.id.btnGestureStudy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GestureStudyActivity.class));
            }
        });
        ImageView imgTest = (ImageView) findViewById(R.id.imgTest);
        //   imgTest.setImageResource(R.mipmap.test);

        testBitMap();

        findViewById(R.id.btnSharePreferencesStudy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SharePreferenceActivity.class));
            }
        });
        sortArray(array);
        Arrays.sort(array);  
    }



    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void testBitMap() {
        // 不做处理，默认缩放。
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test, options);
        Log.i(TAG, "bitmap：ByteCount = " + bitmap.getByteCount() + ":::bitmap：AllocationByteCount = " + bitmap.getAllocationByteCount());
        Log.i(TAG, "width:" + bitmap.getWidth() + ":::height:" + bitmap.getHeight());
        Log.i(TAG, "inDensity:" + options.inDensity + ":::inTargetDensity:" + options.inTargetDensity);

        Log.i(TAG,"===========================================================================");

        // 手动设置inDensity与inTargetDensity，影响缩放比例。
        BitmapFactory.Options options_setParams = new BitmapFactory.Options();
        options_setParams.inDensity = 320;
        options_setParams.inTargetDensity = 320;
        Bitmap bitmap_setParams = BitmapFactory.decodeResource(getResources(), R.mipmap.test, options_setParams);
        Log.i(TAG, "bitmap_setParams：ByteCount = " + bitmap_setParams.getByteCount() + ":::bitmap_setParams：AllocationByteCount = " + bitmap_setParams.getAllocationByteCount());
        Log.i(TAG, "width:" + bitmap_setParams.getWidth() + ":::height:" + bitmap_setParams.getHeight());
        Log.i(TAG, "inDensity:" + options_setParams.inDensity + ":::inTargetDensity:" + options_setParams.inTargetDensity);
    }

    //排序
    public void sortArray(int[] array) {
        int temp;
        for (int i = 0; i < array.length-1; i++)
        {
            for (int j = i+1; j <array.length ; j++)
            {
                if (array[i] < array[j])
                {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }

            }

        }
        for (int i = 0; i <array.length ; i++) {
            System.out.println(array[i]);
        }

    }
}
