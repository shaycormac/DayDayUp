package com.example.somemyidea.widget.recycleviewItemDecoration;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.somemyidea.R;
import com.example.somemyidea.entity.MovieBean;

import java.util.List;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-02-06 16:45 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的目的，意义。
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter  
{
    private final List<MovieBean> comingslist;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;


    public MyRecyclerAdapter(Context mContext, List<MovieBean> comingslist) {
        this.mContext = mContext;
        this.comingslist = comingslist;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.date_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myholder = (MyViewHolder) holder;
        myholder.setData(position);
    }

    @Override
    public int getItemCount() {
        return comingslist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mv_name;
        private TextView mv_dec;
        private TextView mv_date;
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mv_name = (TextView) itemView.findViewById(R.id.mv_name);
            mv_dec = (TextView) itemView.findViewById(R.id.mv_dec);
            mv_date = (TextView) itemView.findViewById(R.id.mv_date);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }

        public void setData(int position) {
            MovieBean coming = comingslist.get(position);

            String name = coming.getNm();
            mv_name.setText(name);

            String date = coming.getShowInfo();
            mv_date.setText(date);

            String dec = coming.getScm();
            mv_dec.setText(dec);

            //注：当你发下图片无法打开是，做个字符串替换即可
            //String imagUrl = coming.getImg();
            //String newImagUrl = imagUrl.replaceAll("w.h", "50.80");

            //使用Glide加载图片
           // Glide.with(mContext).load(newImagUrl).into(imageView);
        }
    }

}
