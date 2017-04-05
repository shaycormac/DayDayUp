package com.shay.wangyi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.shay.wangyi.activity.AsynTaskStudy;
import com.shay.wangyi.fragment.BookFragment;
import com.shay.wangyi.fragment.GankFragment;
import com.shay.wangyi.fragment.OneFragment;
import com.shay.wangyi.pageAdapter.MyFragmentAdapter;

import java.util.ArrayList;

/**
 * 模拟网易云音乐的首页布局。
 * （实现功能，不停滑动进入不同的页面，而美个页面可能还包含viewPage）
 */
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,View.OnClickListener
{
    private ImageView llTitleGank;
    private ImageView llTitleOne;
    private ImageView llTitleDou;
    private ViewPager vpContent;
    private Toolbar toolbar;
    private ImageView iv_title_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //设置显示图标
        initToobar();
//去除默认Title显示
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null)
            actionBar.setDisplayShowTitleEnabled(false);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initId();
        initViewPagerFragment();
    }

    private void initToobar() 
    {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) 
            {
                switch (item.getItemId()) {
                    case R.id.toolbar_r_img:
                        Log.e("Test---->","点击了右边图标");
                        break;
                    case R.id.toolbar_r_1:
                        Log.e("Test---->","点击了弹出菜单1");
                        break;
                    case R.id.toolbar_r_2:
                        Log.e("Test---->","点击了弹出菜单2");
                        break;
                    case R.id.toolbar_r_3:
                        startActivity(new Intent(MainActivity.this, AsynTaskStudy.class));
                        break;
                }
                return true;    //返回为true
            }
        });
    }

    private void initViewPagerFragment()
    {
        ArrayList<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new GankFragment());
        mFragmentList.add(OneFragment.newInstance("主页的Fragment",R.color.colorTheme));
        mFragmentList.add(new BookFragment());
        // 注意使用的是：getSupportFragmentManager
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), mFragmentList);
        vpContent.setAdapter(adapter);
        // 设置ViewPager最大缓存的页面个数(cpu消耗少)
        vpContent.setOffscreenPageLimit(2);
        vpContent.addOnPageChangeListener(this);
        //第一个被选择
        llTitleGank.setSelected(true);
        vpContent.setCurrentItem(0);
    }

    private void initId()
    {
        llTitleGank = (ImageView) findViewById(R.id.iv_title_gank);
        llTitleOne = (ImageView) findViewById(R.id.iv_title_one);
        llTitleDou = (ImageView) findViewById(R.id.iv_title_dou);
        iv_title_menu = (ImageView) findViewById(R.id.iv_title_menu);
        vpContent = (ViewPager) findViewById(R.id.vp_content);
        llTitleGank.setOnClickListener(this);
        llTitleOne.setOnClickListener(this);
        llTitleDou.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_search) 
        {
            Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        
    }

    @Override
    public void onPageSelected(int position) 
    {
        //通过ViewPage的页面改动，改变上面图片的选择
        switch(position)
        {
            case 0:
                llTitleGank.setSelected(true);
                llTitleOne.setSelected(false);
                llTitleDou.setSelected(false);
                break;
            case 1:
                llTitleOne.setSelected(true);
                llTitleGank.setSelected(false);
                llTitleDou.setSelected(false);
                break;
            case 2:
                llTitleDou.setSelected(true);
                llTitleOne.setSelected(false);
                llTitleGank.setSelected(false);
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.iv_title_gank:// 干货栏
                if (vpContent.getCurrentItem() != 0) 
                {//不然cpu会有损耗
                    llTitleGank.setSelected(true);
                    llTitleOne.setSelected(false);
                    llTitleDou.setSelected(false);
                    vpContent.setCurrentItem(0);
                }
                break;
            case R.id.iv_title_one:// 电影栏
                if (vpContent.getCurrentItem() != 1) {
                    llTitleOne.setSelected(true);
                    llTitleGank.setSelected(false);
                    llTitleDou.setSelected(false);
                    vpContent.setCurrentItem(1);
                }
                break;
            case R.id.iv_title_dou:// 书籍栏
                if (vpContent.getCurrentItem() != 2) 
                {
                    llTitleDou.setSelected(true);
                    llTitleOne.setSelected(false);
                    llTitleGank.setSelected(false);
                    vpContent.setCurrentItem(2);
                }
                break;
        }
        
    }
}
