package com.example.somemyidea.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.somemyidea.R;
import com.example.somemyidea.entity.MovieBean;
import com.example.somemyidea.entity.NameBean;
import com.example.somemyidea.widget.recycleviewItemDecoration.MyRecyclerAdapter;
import com.example.somemyidea.widget.recycleviewItemDecoration.SectionDecoration;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * User: 炳华儿(574583006@qq.com)
 * Date: ${YEAR}-${MONTH}-${DAY}
 * Time: ${HOUR}:${MINUTE}
 * 使用 ItemDecoration 来完成 可推动的悬浮导航栏的效果
 */
public class ItemDecorationActivity extends AppCompatActivity {
    private RecyclerView rcvItemDecoration;
    private final static String URL = "http://api.meituan.com/mmdb/movie/v2/list/rt/order/coming.json?ci=1&limit=12&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=xiaomi&utm_medium=android&utm_term=6.8.0&utm_content=868030022327462&net=255&dModel=MI%205&uuid=0894DE03C76F6045D55977B6D4E32B7F3C6AAB02F9CEA042987B380EC5687C43&lat=40.100673&lng=116.378619&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463704714271&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=1a0b4a9b-44ec-42fc-b110-ead68bcc2824&__skcy=sXcDKbGi20CGXQPPZvhCU3%2FkzdE%3D";
    //用于标记的关键字
    private List<NameBean> dataList;

    /**
     * 联网请求的数据
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_decoration);
        rcvItemDecoration = (RecyclerView) findViewById(R.id.rcvItemDecoration);
        //获取数据
        getDataFromInternetByOkHttp();
    }

    private void getDataFromInternetByOkHttp() {
        RequestParams params = new RequestParams(URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "联网成功==" + result);

                //联网成功后使用fastjson解析  
                processData(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "联网失败" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void processData(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            String data = jsonObject.getString("data");
            JSONObject dataJson = new JSONObject(data);
            String coming = dataJson.getString("coming");
            List<MovieBean> movieBeenList = JSON.parseArray(coming, MovieBean.class);
            //测试是否解析数据成功  
            String strTest = movieBeenList.get(0).getCat();
            Log.e("TAG", strTest + "222");
            //解析成功，设置适配器
            setDataList(movieBeenList);
            //添加悬浮Item
            rcvItemDecoration.addItemDecoration(new SectionDecoration(dataList, this, new SectionDecoration.DecorationCallback() {
                //返回标记id (即每一项对应的标志性的字符串)  
                @Override
                public String getGroupId(int position) 
                {
                    if(dataList.get(position).getName()!=null)
                        return dataList.get(position).getName();
                    return "-1";
                }
                //获取同组中的第一个内容

                @Override
                public String getGroupFirstLine(int position) 
                {
                    if(dataList.get(position).getName()!=null)
                        return dataList.get(position).getName();
                    return "";
                }
            }));
            MyRecyclerAdapter adapter = new MyRecyclerAdapter(this, movieBeenList);
            rcvItemDecoration.setAdapter(adapter);
            GridLayoutManager manager = new GridLayoutManager(this, 1);
            rcvItemDecoration.setLayoutManager(manager);
            
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void setDataList(List<MovieBean> movieBeenList) 
    {
        dataList = new ArrayList<>();
        NameBean nameBean;
        int length = movieBeenList.size();
        for (int i = 0; i < length; i++) 
        {
            nameBean = new NameBean();
            nameBean.setName(movieBeenList.get(i).getComingTitle());
            dataList.add(nameBean);
        }
    }
}
