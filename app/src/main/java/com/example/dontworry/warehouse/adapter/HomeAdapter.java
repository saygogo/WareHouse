package com.example.dontworry.warehouse.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.bean.HomeInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Don't worry on 2017/7/7.
 * 首页适配器-分类型的recycleView
 */

public class HomeAdapter extends RecyclerView.Adapter {
    private final Context context;

    private static final int HOME_TYPE1 = 0;
    private static final int HOME_TYPE2 = 1;
    private static final int HOME_TYPE4 = 2;
    private static final int HOME_TYPE6 = 3;
    private final List<HomeInfo.DataBean.ItemsBean.ListBeanX> items;


    public HomeAdapter(Context context, List<HomeInfo.DataBean.ItemsBean.ListBeanX> items) {
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
        } else if (viewType == HOME_TYPE6) {
            View view = View.inflate(context, R.layout.home_type6, null);
            return new HomeType6ViewHolder(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == HOME_TYPE1) {
            HomeType1ViewHolder viewHolder = (HomeType1ViewHolder) holder;
            viewHolder.setDate(items.get(position));
        } else if (getItemViewType(position) == HOME_TYPE2) {
            HomeType2ViewHolder viewHolder = (HomeType2ViewHolder) holder;
            viewHolder.setDate(items.get(position));
        } else if (getItemViewType(position) == HOME_TYPE4) {
            HomeType4ViewHolder viewHolder = (HomeType4ViewHolder) holder;
            viewHolder.setData(items.get(position));
        } else if (getItemViewType(position) == HOME_TYPE6) {
            HomeType6ViewHolder viewHolder = (HomeType6ViewHolder) holder;
            viewHolder.setData(items.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = -1;
        HomeInfo.DataBean.ItemsBean.ListBeanX listBeanX = items.get(position);
        int home_type = listBeanX.getHome_type();

        if ("1".equals(home_type + "")) {
            itemViewType = HOME_TYPE1;
        } else if ("2".equals(home_type + "")) {
            itemViewType = HOME_TYPE2;
        } else if ("4".equals(home_type + "")) {
            itemViewType = HOME_TYPE4;
        } else if ("6".equals(home_type + "")) {
            itemViewType = HOME_TYPE6;
        }
        return itemViewType;
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class HomeType1ViewHolder extends ViewHolder {

        @BindView(R.id.home_type1)
        ImageView homeType1;

        public HomeType1ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setDate(final HomeInfo.DataBean.ItemsBean.ListBeanX listBean) {
            Glide.with(context)
                    .load(listBean.getOne().getPic_url())
                    .placeholder(R.drawable.good_big_bg)
                    .error(R.drawable.good_big_bg)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(homeType1);
            homeType1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String topic_url = listBean.getOne().getTopic_url();
                    Intent intent = new Intent(context, HomeItemActivity.class);
                    intent.putExtra("topic_url", topic_url);
                    context.startActivity(intent);
                }
            });
        }

    }

    class HomeType2ViewHolder extends ViewHolder {
        @BindView(R.id.home_type2_1)
        ImageView homeType21;
        @BindView(R.id.home_type2_2)
        ImageView homeType22;


        public HomeType2ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setDate(final HomeInfo.DataBean.ItemsBean.ListBeanX listBean) {
            Glide.with(context)
                    .load(listBean.getOne().getPic_url())
                    .placeholder(R.drawable.good_big_bg)
                    .error(R.drawable.good_big_bg)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(homeType21);
            homeType21.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String topic_url = listBean.getOne().getTopic_url();
                    Intent intent = new Intent(context, HomeItemActivity.class);
                    intent.putExtra("topic_url", topic_url);
                    context.startActivity(intent);
                }
            });
            Glide.with(context)
                    .load(listBean.getTwo().getPic_url())
                    .placeholder(R.drawable.good_big_bg)
                    .error(R.drawable.good_big_bg)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(homeType22);
            homeType22.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String topic_url = listBean.getTwo().getTopic_url();
                    Intent intent = new Intent(context, HomeItemActivity.class);
                    intent.putExtra("topic_url", topic_url);
                    context.startActivity(intent);
                }
            });


        }
    }

    class HomeType4ViewHolder extends ViewHolder {

        @BindView(R.id.home_type4_1)
        ImageView homeType41;
        @BindView(R.id.home_type4_2)
        ImageView homeType42;
        @BindView(R.id.home_type4_3)
        ImageView homeType43;
        @BindView(R.id.home_type4_4)
        ImageView homeType44;

        public HomeType4ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(final HomeInfo.DataBean.ItemsBean.ListBeanX listBean) {

            Glide.with(context)
                    .load(listBean.getOne().getPic_url())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(homeType41);
            homeType41.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String topic_url = listBean.getOne().getTopic_url();
                    Intent intent = new Intent(context, HomeItemActivity.class);
                    intent.putExtra("topic_url", topic_url);
                    context.startActivity(intent);
                }
            });
            Glide.with(context)
                    .load(listBean.getTwo().getPic_url())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(homeType42);
            homeType42.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String topic_url = listBean.getTwo().getTopic_url();
                    Intent intent = new Intent(context, HomeItemActivity.class);
                    intent.putExtra("topic_url", topic_url);
                    context.startActivity(intent);
                }
            });
            Glide.with(context)
                    .load(listBean.getThree().getPic_url())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(homeType43);
            homeType43.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String topic_url = listBean.getThree().getTopic_url();
                    Intent intent = new Intent(context, HomeItemActivity.class);
                    intent.putExtra("topic_url", topic_url);
                    context.startActivity(intent);
                }
            });
            Glide.with(context)
                    .load(listBean.getFour().getPic_url())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(homeType44);
            homeType44.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String topic_url = listBean.getFour().getTopic_url();
                    Intent intent = new Intent(context, HomeItemActivity.class);
                    intent.putExtra("topic_url", topic_url);
                    context.startActivity(intent);
                }
            });

        }
    }

    class HomeType6ViewHolder extends ViewHolder {

        @BindView(R.id.home_type_6_1)
        ImageView homeType61;

        public HomeType6ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(HomeInfo.DataBean.ItemsBean.ListBeanX listBeanX) {
            Glide.with(context)
                    .load(listBeanX.getPic_url())
                    .into(homeType61);
        }
    }
}
