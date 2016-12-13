package com.example.studybehavior.testactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.studybehavior.R;
import com.example.studybehavior.entity.City;
import com.example.studybehavior.widget.CustomRadionGroup;
import com.example.studybehavior.widget.CustomSpinnerView;
import com.example.studybehavior.widget.CustomTabSelectorView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomSpinnerViewActivity extends AppCompatActivity {
    private CustomSpinnerView customSpinnerView;
    private CustomRadionGroup customRadioButton;
    private CustomTabSelectorView customTabView;
    private Map<Object, List<Object>> spinnerMap;
    private ArrayList<City> cities;

    private Map<City, List<City>> tabMap;
    private City cityFather3;
    private City cityFather4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_spinner_view);
        customSpinnerView = (CustomSpinnerView) findViewById(R.id.customSpinnerView);
        customRadioButton = (CustomRadionGroup) findViewById(R.id.customRadioButton);
        customTabView = (CustomTabSelectorView) findViewById(R.id.customTabView);
        //准备数据
       // initRadioButtonData();
        initData();
        initTabData();
        //设置到自定义的控件中
       /* customSpinnerView.setSpinnerData(spinnerMap, new CustomSpinnerView.SpinnerOnItemSelectListener() {
            @Override
            public void onItemSelected(Object fatherItem, List<Object> sunList, View view, int position) 
            {
                City city= (City) sunList.get(position);
                String value = "当前的值为：fatherItem：" + fatherItem + "  sunList:" + sunList.toString() 
                        + "位置：" + position+"  获取到的值为："+city.name+"  城市id"+city.id;
                Toast.makeText(CustomSpinnerViewActivity.this,value,Toast.LENGTH_LONG).show();    
            }
        });
        
        customRadioButton.setData(cities, new CustomRadionGroup.OnCheckedChangeListener<City>() {
            @Override
            public void onCheckedChanged(View view, int position, City city) {
                
            }
        });*/
        
       /* customTabView.setData(spinnerMap, new CustomTabSelectorView.tabOnItemSelectListener<Object, Object>()
        {
            @Override
            public void onItemSelected(View view, int position, Object o, List<Object> t)
            {
                City city= (City) t.get(position);
                String value = "当前的值为：fatherItem：" + o + "  选中的值为:" + t.get(position).toString()
                        + "位置：" + position+"  获取到的值为："+city.name+"  城市id"+city.id;
                Toast.makeText(CustomSpinnerViewActivity.this,value,Toast.LENGTH_LONG).show();

            }
        });*/
        customTabView.setData(tabMap, new CustomTabSelectorView.tabOnItemSelectListener<City, City>() 
        {
            @Override
            public void onItemSelected(View view, int position, City city, City t) 
            {
                String value = "当前位置为：position：" + position +"  获取到的值为："+t.name+"  城市id"+t.id;
                Toast.makeText(CustomSpinnerViewActivity.this,value,Toast.LENGTH_LONG).show();
                //测试更新数据
                if (cityFather3!=null)
                {
                    List<City> cityList3=new ArrayList<>();
                    cityList3.add(0, new City("0","全部"));
                    for (int i = 0; i <5 ; i++)
                    {
                        city = new City(String.valueOf(i+1), String.valueOf("上海" + i));
                        cityList3.add(city);
                    }
                    customTabView.updateView(cityFather3,cityList3);
                }
                if (position==2)
                    customTabView.updateView(cityFather4,null); 
                
            }
        });
    }

    private void initTabData() 
    {
        tabMap = new LinkedHashMap<>();
        City city;
        City cityFather = new City("0", "州市");
        List<City> cityList = new ArrayList<>();
        cityList.add(0,new City("0","全部"));
        for (int i = 0; i <20 ; i++)
        {
            city = new City(String.valueOf(i+1), String.valueOf("苏州" + i));
            cityList.add(city);
        }

        tabMap.put(cityFather, cityList);
        List<City> cityList2=new ArrayList<>();
        City cityFather2 = new City("0", "北京");
        cityList2.add(0,new City("0","全部"));
        for (int i = 0; i <5 ; i++)
        {
            city = new City(String.valueOf(i+1), String.valueOf("北京" + i));
            cityList2.add(city);
        }

        tabMap.put(cityFather2, cityList2);
        List<City> cityList3=new ArrayList<>();
        cityFather3 = new City("0", "江南");
        cityList3.add(0,new City("0","全部"));
        for (int i = 0; i <5 ; i++)
        {
            city = new City(String.valueOf(i+1), String.valueOf("上海" + i));
            cityList3.add(city);
        }
        tabMap.put(cityFather3, null);

        List<City> cityList4=new ArrayList<>();
        cityFather4 = new City("0", "广州");
        cityList4.add(0,new City("0", "全部"));
        for (int i = 0; i <7 ; i++)
        {
            city = new City(String.valueOf(i+1), String.valueOf("广州啊，哈哈" + i));
            cityList4.add(city);
        }

        tabMap.put(cityFather4, cityList4);
    }

    private void initRadioButtonData() {
        cities = new ArrayList<>();
        City city = new City("swdsw", "蘇州蘇州蘇州蘇州蘇州蘇州蘇州蘇州");
        cities.add(city);
        city = new City("sdfew", "常州蘇州蘇州蘇州蘇州蘇州蘇州蘇州");
        cities.add(city);
        city = new City("sdfewq", "徐州蘇州蘇州蘇州蘇州蘇州");
        cities.add(city);
        
    }

    private void initData() 
    {
        spinnerMap = new LinkedHashMap<>();
        City city;
        City cityFather = new City("0", "州");
        List<Object> cityList = new ArrayList<>();
        cityList.add(0,cityFather);
        for (int i = 0; i <20 ; i++)
        {
            city = new City(String.valueOf(i+1), String.valueOf("苏州" + i));
            cityList.add(city);
        }
        
        spinnerMap.put(cityFather, cityList);
        List<Object> cityList2=new ArrayList<>();
        City cityFather2 = new City("0", "北京");
        cityList2.add(0,cityFather2);
        for (int i = 0; i <5 ; i++)
        {
            city = new City(String.valueOf(i+1), String.valueOf("北京" + i));
            cityList2.add(city);
        }
       
        spinnerMap.put(cityFather2, cityList2);
        List<Object> cityList3=new ArrayList<>();
        City cityFather3 = new City("0", "江南の上海");
        cityList3.add(0, cityFather3);
        for (int i = 0; i <5 ; i++)
        {
            city = new City(String.valueOf(i+1), String.valueOf("上海" + i));
            cityList3.add(city);
        }
        spinnerMap.put(cityFather3, cityList3);
       
        
        List<Object> cityList4=new ArrayList<>();
        City cityFather4 = new City("0", "广州啊");
        cityList4.add(0,cityFather4);
        for (int i = 0; i <7 ; i++)
        {
            city = new City(String.valueOf(i+1), String.valueOf("广州啊，哈哈" + i));
            cityList4.add(city);
        }
      
        spinnerMap.put(cityFather4, cityList4);
        List<Object> cityList5=new ArrayList<>();
        City cityFather5 = new City("0", "新疆的哈");
        cityList5.add(0,cityFather5);
        for (int i = 0; i <9 ; i++)
        {
            city = new City(String.valueOf(i+1), String.valueOf("新疆的哈密瓜" + i));
            cityList5.add(city);
        }
       
        spinnerMap.put(cityFather5, cityList5);
        List<Object> cityList6=new ArrayList<>();
        City cityFather6 = new City("0", "漠北的回民");
        cityList6.add(0,cityFather6);
        for (int i = 0; i <40 ; i++)
        {
            city = new City(String.valueOf(i+1), String.valueOf("漠北的回民" + i));
            cityList6.add(city);
        }
     
        spinnerMap.put(cityFather6, cityList6);
    }
}
