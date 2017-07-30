package com.example.dontworry.warehouse.fragment.LiveBroadcast;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.classifcationactivity.classIfcationHomeItem.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Don't worry on 2017/7/25.
 */

public class BilibiliAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final BilibiliInfo.DataBean data;
    private static final int BANNER = 0;
    private static final int ITEM = 1;
    private BilibiliItemAdapter adapter;


    public BilibiliAdapter(Context context, BilibiliInfo.DataBean data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = null;
        if (viewType == BANNER) {
            viewHolder = new BannerHolder(LayoutInflater.from(context).inflate(R.layout.item_banner, parent, false));
        } else{
            viewHolder = new ItemHolder(LayoutInflater.from(context).inflate(R.layout.item_gride, parent, false));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == BANNER) {
            BannerHolder bannerHolder = (BannerHolder) holder;
            bannerHolder.setData(data.getBanner());
        } else{
            ItemHolder itemHolder = (ItemHolder) holder;
            itemHolder.setData(data.getPartitions());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.getPartitions().size();
    }

    class BannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        Banner banner;
        @BindView(R.id.ll_core)
        LinearLayout llCore;
        @BindView(R.id.ll_video)
        LinearLayout llVideo;
        @BindView(R.id.ll_search)
        LinearLayout llSearch;
        @BindView(R.id.ll_classification)
        LinearLayout llClassification;
        @BindView(R.id.ll_follow)
        LinearLayout llFollow;

        public BannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<BilibiliInfo.DataBean.BannerBean> bannerbean) {
            List<String> images = new ArrayList<>();
            for (int i = 0; i < bannerbean.size(); i++) {
                images.add(bannerbean.get(i).getImg());
            }
            this.banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.live_broadcast_top_image)
        ImageView liveBroadcastTopImage;
        @BindView(R.id.live_broadcast_top_name)
        TextView liveBroadcastTopName;
        @BindView(R.id.live_broadcast_top_number)
        TextView liveBroadcastTopNumber;
        @BindView(R.id.live_broadcast_top_join)
        ImageView liveBroadcastTopJoin;
        @BindView(R.id.itemrecyclerview)
        RecyclerView itemrecyclerview;
        @BindView(R.id.base_livebroadcast_bottom_lookmore)
        Button baseLivebroadcastBottomLookmore;
        @BindView(R.id.base_livebroadcast_bottom_refresh)
        TextView baseLivebroadcastBottomRefresh;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(List<BilibiliInfo.DataBean.PartitionsBean> partitions) {
            BilibiliInfo.DataBean.PartitionsBean partitionsBean = partitions.get(getLayoutPosition());
            Glide.with(context)
                    .load(partitionsBean.getPartition().getSub_icon().getSrc())
                    .into(liveBroadcastTopImage);
            liveBroadcastTopName.setText(partitionsBean.getPartition().getName());
            liveBroadcastTopNumber.setText("当前" + partitionsBean.getPartition().getCount() + "个直播");
            liveBroadcastTopJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "查看更多", Toast.LENGTH_SHORT).show();
                }
            });
            baseLivebroadcastBottomLookmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "查看更多", Toast.LENGTH_SHORT).show();
                }
            });
            baseLivebroadcastBottomRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "点击了刷新", Toast.LENGTH_SHORT).show();
                }
            });

            adapter = new BilibiliItemAdapter(context, partitionsBean);
            itemrecyclerview.setAdapter(adapter);
            itemrecyclerview.setLayoutManager(new GridLayoutManager(context, 2));
        }
    }
}