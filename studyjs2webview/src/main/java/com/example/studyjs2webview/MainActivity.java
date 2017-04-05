package com.example.studyjs2webview;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.studyjs2webview.activity.GestureStudyActivity;
import com.example.studyjs2webview.activity.Js2JavaActivity;
import com.example.studyjs2webview.activity.SharePreferenceActivity;

import java.util.Arrays;

/**
 * User: 炳华儿(574583006@qq.com)
 * Date: ${YEAR}-${MONTH}-${DAY}
 * Time: ${HOUR}:${MINUTE}
 * FIXME 学习简单的js和原生方法交互。
 */
public class MainActivity extends AppCompatActivity {

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
        findViewById(R.id.btnSharePreferencesStudy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SharePreferenceActivity.class));
            }
        });
        sortArray(array);
        Arrays.sort(array);
        
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
