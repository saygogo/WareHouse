package com.example.dontworry.warehouse.masterFragment.like;

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

public class MasterLikeAdapter extends BaseAdapter {
    private final Context context;
    private final List<MasterLikeInfo.DataBean.ItemsBean.GoodsBean> items;

    public MasterLikeAdapter(Context context, List<MasterLikeInfo.DataBean.ItemsBean.GoodsBean> items) {
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
            convertView = View.inflate(context, R.layout.like_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MasterLikeInfo.DataBean.ItemsBean.GoodsBean goodsBean = items.get(position);
        Glide.with(context)
                .load(goodsBean.getGoods_image())
                .into(viewHolder.likeImage);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.like_image)
        ImageView likeImage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
