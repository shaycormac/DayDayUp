package com.example.studypermission.activity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.studypermission.R;
import com.example.studypermission.fragment.HomeFragment;
import com.example.studypermission.fragment.PersonalFragment;
import com.example.studypermission.fragment.PublishFragment;
import com.example.studypermission.fragment.TaskFragment;
import com.example.studypermission.fragment.dummy.DummyContent;

public class StartActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener
,TaskFragment.OnListFragmentInteractionListener{
    //创建5个项目
    BottomNavigationItem msgItem;
    BottomNavigationItem taskItem;
    BottomNavigationItem noticeItem;
    BottomNavigationItem myItem;
    //角标(先设置一个试试)
    BadgeItem badgeItem;
    //4个fragment
    private HomeFragment homeFragment;
    private TaskFragment taskFragment;
    private PublishFragment publishFragment;
    private PersonalFragment personalFragment;
    FrameLayout frameContainer;
   
 
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        BottomNavigationBar bottomBar = (BottomNavigationBar) findViewById(R.id.bottomBar);
        frameContainer = (FrameLayout) findViewById(R.id.frameContainer);
        //自动隐藏
        bottomBar.setAutoHideEnabled(true);
        //mode
        //BottomNavigationBar.MODE_SHIFTING;
        //BottomNavigationBar.MODE_FIXED;
        //BottomNavigationBar.MODE_DEFAULT;
        bottomBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        //背景样式
        // BottomNavigationBar.BACKGROUND_STYLE_DEFAULT;
        // BottomNavigationBar.BACKGROUND_STYLE_RIPPLE
        // BottomNavigationBar.BACKGROUND_STYLE_STATIC
        bottomBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        //背景颜色
        bottomBar.setBarBackgroundColor(R.color.colorAccent);
        //未选中的颜色
        bottomBar.setInActiveColor(R.color.colorPrimary);
        //选中颜色
        bottomBar.setActiveColor(android.R.color.holo_blue_dark);
        badgeItem = new BadgeItem().setBackgroundColor(Color.RED).setText("99+").setHideOnSelect(true);
        //初始化item
        initBottomItem();
        //进来默认选择第一个
        bottomBar.setTabSelectedListener(tabSlectListener);
        //容器加进去
        bottomBar.addItem(msgItem).addItem(taskItem).addItem(noticeItem).addItem(myItem);
        //初始化
        bottomBar.initialise();
        //默认加载第一个
        Fragment fragment = (Fragment) fragmentStatePagerAdapter.instantiateItem(frameContainer,0);
        fragmentStatePagerAdapter.setPrimaryItem(frameContainer, 0, fragment);
        fragmentStatePagerAdapter.finishUpdate(frameContainer);
       
        //设置fragment
       // setDefaultFragment();
    }

    private void setDefaultFragment() 
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        //先隐藏所有
        hideFragments(fragmentTransaction);
        homeFragment = HomeFragment.newInstance("参数1", "参数2");
        fragmentTransaction.add(R.id.frameContainer, homeFragment);
        fragmentTransaction.commit();
    }

    private void hideFragments(FragmentTransaction fragmentTransaction)
    {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (taskFragment != null) {
            fragmentTransaction.hide(taskFragment);
        }
        if (publishFragment != null) {
            fragmentTransaction.hide(publishFragment);
        }
        if (personalFragment != null) {
            fragmentTransaction.hide(personalFragment);
        }
        
    }

    private void initBottomItem() 
    {
        //设置角标
        
        msgItem = new BottomNavigationItem(R.mipmap.ic_launcher, "消息");
        msgItem.setBadgeItem(badgeItem);
        taskItem = new BottomNavigationItem(R.mipmap.ic_launcher, "任务");
        noticeItem = new BottomNavigationItem(R.mipmap.ic_launcher, "公告");
        myItem = new BottomNavigationItem(R.mipmap.ic_launcher, "我的");
    }

    BottomNavigationBar.OnTabSelectedListener tabSlectListener = new BottomNavigationBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) 
        {
            Log.d("选择的位置：", position + "");
            Fragment fragment = (Fragment) fragmentStatePagerAdapter.instantiateItem(frameContainer, position);
            fragmentStatePagerAdapter.setPrimaryItem(frameContainer, 0, fragment);
            fragmentStatePagerAdapter.finishUpdate(frameContainer);
            /*FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            //先隐藏所有
            hideFragments(fragmentTransaction);
            switch (position)
            {
                case 0:
                    if (homeFragment==null)
                    {
                        homeFragment = HomeFragment.newInstance("参数1", "参数2");
                        fragmentTransaction.add(frameContainer, homeFragment);
                    }else
                        fragmentTransaction.show(homeFragment);
                    break;
                case 1:
                    if (taskFragment==null)
                    {
                        taskFragment = TaskFragment.newInstance(1);
                        fragmentTransaction.add(frameContainer, taskFragment);
                    }else
                        fragmentTransaction.show(taskFragment);
                    break;
                case 2:
                    if (publishFragment==null)
                    {
                        publishFragment = PublishFragment.newInstance("参数1", "参数2");
                        fragmentTransaction.add(frameContainer, publishFragment);
                    }else
                        fragmentTransaction.show(publishFragment);
                    break;
                case 3:
                    if (personalFragment==null)
                    {
                        personalFragment = PersonalFragment.newInstance("参数1", "参数2");
                        fragmentTransaction.add(frameContainer, personalFragment);
                    }else
                        fragmentTransaction.show(personalFragment);
                    break;
                default:
                    if (homeFragment==null)
                    {
                        homeFragment = HomeFragment.newInstance("参数1", "参数2");
                        fragmentTransaction.add(frameContainer, homeFragment);
                    }else
                        fragmentTransaction.show(homeFragment);
                    break;
            }
            fragmentTransaction.commit();*/
        }

        @Override
        public void onTabUnselected(int position) {

        }

        @Override
        public void onTabReselected(int position) {

        }
    };

    @Override
    public void onFragmentInteraction(Uri uri) {
        
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        
    }

    //稍微屌一点
    FragmentPagerAdapter fragmentStatePagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) 
        {
            switch(position)
            {
              case 0:
                  return HomeFragment.newInstance("参数1", "参数2");
              case 1:
                 return TaskFragment.newInstance(1);
              case 2:
                  return PublishFragment.newInstance("参数1", "参数2");
              case 3:
                  return PersonalFragment.newInstance("hehe", "hjaha"); 
              default:
                  return HomeFragment.newInstance("参数1", "参数2");
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };
}
