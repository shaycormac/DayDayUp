package com.example.studyjs2webview;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.studyjs2webview.activity.CoordinatorLayoutActivity;
import com.example.studyjs2webview.activity.ViewTreeObserverActivity;

/** 
 * User: 炳华儿(574583006@qq.com) 
 * Date: ${YEAR}-${MONTH}-${DAY} 
 * Time: ${HOUR}:${MINUTE} 
 * FIXME 学习简单的js和原生方法交互。 
 */  
public class MainActivity extends AppCompatActivity 
{
    private static final String TAG = "图片压缩";

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnStudyJs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewTreeObserverActivity.class));
            }
        });
        findViewById(R.id.btnCardView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) 
            {
                startActivity(new Intent(MainActivity.this, CoordinatorLayoutActivity.class));
            }
        });
        ImageView imgTest = (ImageView) findViewById(R.id.imgTest);
     //   imgTest.setImageResource(R.mipmap.test);
        
        testBitMap();
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
}
