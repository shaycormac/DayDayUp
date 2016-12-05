package com.example.somemyidea.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.somemyidea.R;
import com.example.somemyidea.entity.City;
import com.example.somemyidea.utils.ToastUtils;
import com.example.somemyidea.widget.CustomTablayout;

import java.util.ArrayList;
import java.util.List;

public class ToastActivity extends AppCompatActivity {
    private TextView cancel;
    private TextView show;
    FloatingActionButton fab;
    private CustomTablayout customTabLayout;
    private List<City> cityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


      /* fab= (FloatingActionButton) findViewById(fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               *//* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*//*
                ToastUtils.getInstance(ToastActivity.this).showToast("我就是来试试的");
            }
        });
*/
        cancel = (TextView) findViewById(R.id.cancel);
        TextViewCompat.setTextAppearance(cancel,R.style.BlueButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.getInstance().cancelToast();
            }
        });

        show = (TextView) findViewById(R.id.show);
        TextViewCompat.setTextAppearance(show,R.style.BlueButton);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.getInstance().showToast("试一试",ToastActivity.this);
            }
        });

        customTabLayout = (CustomTablayout) findViewById(R.id.customTabLayout);
        initData();
        customTabLayout.setTabLayoutData(cityList, new CustomTablayout.OnTabChooseListener<City>() {
            @Override
            public void onTabChoosed(int position, City city) 
            {
                if (position==0)
                    Toast.makeText(ToastActivity.this, "获取的值为：position:" + position + "  city" + city.toString(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ToastActivity.this, "获取的值为：position:" + position , Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void initData() 
    {
        City city = new City("11", "苏州");
        cityList.add(city);
        city = new City("22", "上海");
        cityList.add(city);
        city = new City("22", "常州");
        cityList.add(city);
        city = new City("22", "徐州");
        cityList.add(city);
        city = new City("22", "南通");
        cityList.add(city);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toast, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ToastUtils.getInstance().cancelToast();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode==KeyEvent.KEYCODE_BACK)
            ToastUtils.getInstance().cancelToast();

        return super.onKeyDown(keyCode, event);
    }
}
