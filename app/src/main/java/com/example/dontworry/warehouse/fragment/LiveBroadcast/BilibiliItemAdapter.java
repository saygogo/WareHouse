package com.example.dontworry.warehouse.fragment.LiveBroadcast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
 * Created by Don't worry on 2017/7/26.
 */

public class BilibiliItemAdapter extends RecyclerView.Adapter<BilibiliItemAdapter.MyViewHolder> {
    private final Context context;
    private final BilibiliInfo.DataBean.PartitionsBean partitionsBean;


    public BilibiliItemAdapter(Context context, BilibiliInfo.DataBean.PartitionsBean partitionsBean) {
        this.context = context;
        this.partitionsBean = partitionsBean;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_content, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BilibiliInfo.DataBean.PartitionsBean.PartitionBean partition = partitionsBean.getPartition();
        Glide.with(context)
                .load(partitionsBean.getLives().get(position).getCover().getSrc())
                .into(holder.liveItemImage);
        holder.liveTextContext.setText(partitionsBean.getLives().get(position).getTitle());
        holder.liveTextName.setText(partitionsBean.getLives().get(position).getOwner().getName());
        holder.liveTextNumber.setText("当前在线人数"+partitionsBean.getLives().get(position).getOnline());

    }

    @Override
    public int getItemCount() {
        return partitionsBean==null?0:partitionsBean.getLives().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.live_item_image)
        ImageView liveItemImage;
        @BindView(R.id.live_text_context)
        TextView liveTextContext;
        @BindView(R.id.live_text_name)
        TextView liveTextName;
        @BindView(R.id.live_text_number)
        TextView liveTextNumber;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
