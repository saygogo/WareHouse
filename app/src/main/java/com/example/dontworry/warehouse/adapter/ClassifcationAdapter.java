package com.example.dontworry.warehouse.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.bean.ClassifctionInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Don't worry on 2017/7/6.
 * 分类适配器-MyViewHolder
 */

public class ClassifcationAdapter extends RecyclerView.Adapter<ClassifcationAdapter.MyViewHolder> {

    private final Context context;
    private final List<ClassifctionInfo.DataBean.ItemsBean> items;


    public ClassifcationAdapter(Context context, List<ClassifctionInfo.DataBean.ItemsBean> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.classifvation_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ClassifctionInfo.DataBean.ItemsBean itemsBean = items.get(position);


        Glide.with(context)
                .load(itemsBean.getCover_new_img())
                .placeholder(R.drawable.good_big_bg)
                .error(R.drawable.good_big_bg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivClassifcation);
    }

    @Override
    public long getItemId(int position) {
        return 1;
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_classifcation)
        ImageView ivClassifcation;

        public MyViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.OnItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

//接口回传下标位置
    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OnItemClickListener onItemClickListener;

}
