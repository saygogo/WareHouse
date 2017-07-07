package com.example.dontworry.warehouse.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.bean.HomeInfo;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Don't worry on 2017/7/7.
 */

public class HomeAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final HomeInfo.DataBean.ItemsBean items;

    private static final int HOME_TYPE1 = 0;
    private static final int HOME_TYPE2 = 1;
    private static final int HOME_TYPE4 = 2;


    public HomeAdapter(Context context, HomeInfo.DataBean.ItemsBean items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HOME_TYPE1) {
            View view = View.inflate(context, R.layout.home_type1, null);
            return new HomeType1ViewHolder(view);
        } else if (viewType == HOME_TYPE2) {
            View view = View.inflate(context, R.layout.home_type2, null);
            return new HomeType2ViewHolder(view);
        } else if (viewType == HOME_TYPE4) {
            View view = View.inflate(context, R.layout.home_type4, null);
            return new HomeType4ViewHolder(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == HOME_TYPE1) {
            HomeType1ViewHolder viewHolder = (HomeType1ViewHolder) holder;
            viewHolder.setDate(items.getList().get(position));
        } else if (getItemViewType(position) == HOME_TYPE2) {
            HomeType2ViewHolder viewHolder = (HomeType2ViewHolder) holder;
            viewHolder.setDate(items.getList().get(position));
        } else if (getItemViewType(position) == HOME_TYPE4) {
            HomeType4ViewHolder viewHolder = (HomeType4ViewHolder) holder;
            viewHolder.setData(items.getList().get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = -1;
        HomeInfo.DataBean.ItemsBean.ListBean listBean = items.getList().get(position);
        String home_type = listBean.getHome_type();

        if ("1".equals(home_type)) {
            itemViewType = HOME_TYPE1;
        } else if ("2".equals(home_type)) {
            itemViewType = HOME_TYPE2;
        } else if ("4".equals(home_type)) {
            itemViewType = HOME_TYPE4;
        }
        return itemViewType;
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.getList().size();
    }

    class HomeType1ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.home_type1)
        ImageView homeType1;

        public HomeType1ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setDate(HomeInfo.DataBean.ItemsBean.ListBean listBean) {
            Glide.with(context)
                    .load(listBean.getOne().getPic_url())
                    .placeholder(R.drawable.good_big_bg)
                    .error(R.drawable.good_big_bg)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(homeType1);
        }
    }

    class HomeType2ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_type2_1)
        ImageView homeType21;
        @BindView(R.id.home_type2_2)
        ImageView homeType22;


        public HomeType2ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setDate(HomeInfo.DataBean.ItemsBean.ListBean listBean) {
            Glide.with(context)
                    .load(listBean.getOne().getPic_url())
                    .placeholder(R.drawable.good_big_bg)
                    .error(R.drawable.good_big_bg)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(homeType21);
            Glide.with(context)
                    .load(listBean.getTwo().getPic_url())
                    .placeholder(R.drawable.good_big_bg)
                    .error(R.drawable.good_big_bg)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(homeType22);

        }
    }

    class HomeType4ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.home_type4_1)
        ImageView homeType41;
        @BindView(R.id.home_type4_2)
        ImageView homeType42;
        @BindView(R.id.home_type4_3)
        ImageView homeType43;
        @BindView(R.id.home_type4_4)
        ImageView homeType44;

        public HomeType4ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(HomeInfo.DataBean.ItemsBean.ListBean listBean) {
            Glide.with(context)
                    .load(listBean.getOne().getPic_url())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(homeType41);
            Glide.with(context)
                    .load(listBean.getTwo().getPic_url())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(homeType42);
            Glide.with(context)
                    .load(listBean.getThree().getPic_url())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(homeType43);
            Glide.with(context)
                    .load(listBean.getFour().getPic_url())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(homeType44);

        }
    }
}