package com.example.dontworry.warehouse.masterFragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dontworry.warehouse.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Don't worry on 2017/7/9.
 * 达人页面-item点击-粉丝Fragment适配器
 */

public class FansItemAdapter extends BaseAdapter {
    private final Context context;
    private final List<MasterItemInfo.DataBean.ItemsBean.UsersBean> users;

    public FansItemAdapter(Context context, List<MasterItemInfo.DataBean.ItemsBean.UsersBean> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users == null ? 0 : users.size();
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
            convertView = View.inflate(context, R.layout.fans_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MasterItemInfo.DataBean.ItemsBean.UsersBean usersBean = users.get(position);
        viewHolder.fansItemText.setText(usersBean.getUser_name());
        Glide.with(context)
                .load(usersBean.getUser_image().getOrig())
                .placeholder(R.drawable.good_big_bg)
                .error(R.drawable.good_big_bg)
                .into(viewHolder.fansItemImage);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.fans_item_image)
        ImageView fansItemImage;
        @BindView(R.id.fans_item_text)
        TextView fansItemText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
