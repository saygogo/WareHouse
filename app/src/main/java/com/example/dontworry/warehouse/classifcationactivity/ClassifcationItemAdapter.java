package com.example.dontworry.warehouse.classifcationactivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dontworry.warehouse.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Don't worry on 2017/7/7.
 * 家居适配器
 */

public class ClassifcationItemAdapter extends RecyclerView.Adapter<ClassifcationItemAdapter.MyViewHolder> {
    private final Context context;
    private final List<CalssIfcationHomeFurnishingItemInfo.DataBean.ItemsBean> items;


    private CalssIfcationHomeFurnishingItemInfo.DataBean.ItemsBean itemsBean;

    public ClassifcationItemAdapter(Context context, List<CalssIfcationHomeFurnishingItemInfo.DataBean.ItemsBean> items) {

        this.context = context;
        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.classifvationitem_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        itemsBean = items.get(position);
        holder.classGoodsName.setText(itemsBean.getGoods_name());
        holder.classBrandName.setText(itemsBean.getBrand_info().getBrand_name());
        holder.classLikeCount.setText(itemsBean.getLike_count());
        holder.classPrice.setText(itemsBean.getPrice());
        if (holder.classDiscountPrice.length() > 0) {
            holder.classView.setVisibility(View.VISIBLE);
            holder.classDiscountPrice.setText(itemsBean.getDiscount_price());
        }

        Glide.with(context)
                .load(itemsBean.getGoods_image())
                .placeholder(R.drawable.good_big_bg)
                .error(R.drawable.good_big_bg)
                .into(holder.classImage);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.class_image)
        ImageView classImage;
        @BindView(R.id.class_goods_name)
        TextView classGoodsName;
        @BindView(R.id.class_brand_name)
        TextView classBrandName;
        @BindView(R.id.class_like_count)
        TextView classLikeCount;
        @BindView(R.id.class_price)
        TextView classPrice;
        @BindView(R.id.class_discount_price)
        TextView classDiscountPrice;
        @BindView(R.id.class_view)
        View classView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

