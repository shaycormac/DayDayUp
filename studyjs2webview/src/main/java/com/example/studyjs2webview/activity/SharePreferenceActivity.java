package com.example.studyjs2webview.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.studyjs2webview.R;

import java.util.HashSet;
import java.util.Set;

//sharePreference源码浅析
public class SharePreferenceActivity extends AppCompatActivity 
{
    //两种引入方式。(一种以当前的activity为基准，另一种以Context为基准，其实一样的)
    //一种是Activity的getPreferences方法，一种是Context的getSharedPreferences方法
    //第一种最后会在data/data/包名/shared_prefs文件夹下生成一个activity.当前activity名字的xml文件
    //第二种会在这个文件下生成你命名的文件名+xml文件
    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferencesContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_perfence);
        initTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (sharedPreferences==null)
                    sharedPreferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editorActivity = sharedPreferences.edit();
                editorActivity.putString("name", "lisi");
                editorActivity.commit();
                
            }
        }).start();
        
    }

    private void initTest() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        //第二中引入方式
        sharedPreferencesContext = getSharedPreferences("shayCormac", MODE_PRIVATE);

        SharedPreferences.Editor editorActivity = sharedPreferences.edit();
        editorActivity.putString("name", "binghua");
        editorActivity.commit();

        SharedPreferences.Editor editorContext = sharedPreferencesContext.edit();
        editorActivity.putBoolean("saved", true);
        Set<String> set = new HashSet<>();
        set.add("hahhaha");
        set.add("hehhehe");
        editorContext.putStringSet("content", set);
        editorContext.commit();
    }
}
