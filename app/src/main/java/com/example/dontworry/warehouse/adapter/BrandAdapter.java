package com.example.dontworry.warehouse.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.bean.BrandInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Don't worry on 2017/7/6.
 */

public class BrandAdapter extends BaseAdapter {
    private final Context context;
    private final List<BrandInfo.DataBean.ItemsBean> items;

    public BrandAdapter(Context context, List<BrandInfo.DataBean.ItemsBean> items) {
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
            convertView = View.inflate(context, R.layout.brand_item, null);
            viewHolder = new ViewHolder(convertView);
            viewHolder.ivBrandImage = (ImageView) convertView.findViewById(R.id.iv_brand_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BrandInfo.DataBean.ItemsBean itemsBean = items.get(position);
        viewHolder.tvBrandTextview.setText(itemsBean.getBrand_name());

        Glide.with(context)
                .load(itemsBean.getBrand_logo())
                .placeholder(R.drawable.good_big_bg)
                .error(R.drawable.good_big_bg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.ivBrandImage);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_brand_image)
        ImageView ivBrandImage;
        @BindView(R.id.tv_brand_textview)
        TextView tvBrandTextview;
        @BindView(R.id.iv_brand_next_image)
        ImageView ivBrandNextImage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
