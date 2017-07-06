package com.example.dontworry.warehouse.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.bean.MasterInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Don't worry on 2017/7/6.
 */

public class MasterAdapter extends RecyclerView.Adapter<MasterAdapter.MyViewHolder> {

    private final Context context;
    private final List<MasterInfo.DataBean.ItemsBean> masterInfo;
    private String imageUrl = "http://imgs-qn.iliangcang.com/ware/userhead/orig/2/12/12596.jpg?t=1499319695";

    public MasterAdapter(Context context, List<MasterInfo.DataBean.ItemsBean> masterInfo) {
        this.context = context;
        this.masterInfo = masterInfo;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.master_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MasterInfo.DataBean.ItemsBean itemsBean = masterInfo.get(position);
        holder.tvName.setText(itemsBean.getUsername());
        holder.tvOccupation.setText(itemsBean.getDuty());
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.good_big_bg)
                .error(R.drawable.good_big_bg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivCharacter);
    }

    @Override
    public long getItemId(int position) {
        return 1;
    }

    @Override
    public int getItemCount() {
        return masterInfo == null ? 0 : masterInfo.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_character)
        ImageView ivCharacter;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_occupation)
        TextView tvOccupation;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}