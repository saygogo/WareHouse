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
import com.example.dontworry.warehouse.bean.SpecialInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Don't worry on 2017/7/7.
 * 礼物适配器-listView
 */

public class SpecialAdapter extends BaseAdapter {
    private final Context context;
    private final List<SpecialInfo.DataBean.ItemsBean> items;

    public SpecialAdapter(Context context, List<SpecialInfo.DataBean.ItemsBean> items) {
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
            convertView = View.inflate(context, R.layout.special_item, null);
            viewHolder = new ViewHolder(convertView);
            viewHolder.specialImage = (ImageView) convertView.findViewById(R.id.special_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SpecialInfo.DataBean.ItemsBean itemsBean = items.get(position);
        viewHolder.specialText.setText(itemsBean.getTopic_name());

        Glide.with(context)
                .load(itemsBean.getCover_img_new())
                .placeholder(R.drawable.good_big_bg)
                .error(R.drawable.good_big_bg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.specialImage);
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.special_image)
        ImageView specialImage;
        @BindView(R.id.special_text)
        TextView specialText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
