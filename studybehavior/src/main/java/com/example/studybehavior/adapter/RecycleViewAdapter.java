package com.example.studybehavior.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studybehavior.R;

import java.util.List;

/**
 * @author 作者： Android2(范方方)
 * @datetime 创建时间：2016-11-10 11:26 GMT+8
 * @email 邮箱： 574583006@qq.com
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.BottomSheetDialogViewHolder>
{
    private Context context;
    private List<String> stringList;

    public RecycleViewAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public BottomSheetDialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BottomSheetDialogViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_textview_layout,null,false));
    }

    @Override
    public void onBindViewHolder(BottomSheetDialogViewHolder holder, int position)
    {
        holder.tvBottomSheetDialog.setText(stringList.get(position));
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,150);
        holder.tvBottomSheetDialog.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class BottomSheetDialogViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvBottomSheetDialog;

        public BottomSheetDialogViewHolder(View itemView)
        {
            super(itemView);
            tvBottomSheetDialog = (TextView) itemView.findViewById(R.id.tvBottomSheetDialog);
        }
    }
}
