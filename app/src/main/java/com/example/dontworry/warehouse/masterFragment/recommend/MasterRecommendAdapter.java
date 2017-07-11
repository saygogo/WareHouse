package com.example.dontworry.warehouse.masterFragment.recommend;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dontworry.warehouse.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Don't worry on 2017/7/11.
 */

public class MasterRecommendAdapter extends BaseAdapter {

    private final Context context;
    private final List<MasterRecommendInfo.DataBean.ItemsBean.GoodsBean> items;

    public MasterRecommendAdapter(Context context, List<MasterRecommendInfo.DataBean.ItemsBean.GoodsBean> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.recommend, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MasterRecommendInfo.DataBean.ItemsBean.GoodsBean goodsBean = items.get(position);
        Glide.with(context)
                .load(goodsBean.getGoods_image())
                .into(viewHolder.recommendImage);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.recommend_image)
        ImageView recommendImage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
