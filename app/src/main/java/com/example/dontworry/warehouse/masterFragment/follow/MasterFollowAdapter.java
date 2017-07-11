package com.example.dontworry.warehouse.masterFragment.follow;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dontworry.warehouse.R;

import java.lang.reflect.InvocationHandler;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Don't worry on 2017/7/11.
 */

public class MasterFollowAdapter extends BaseAdapter {

    private final Context context;
    private final List<MasterFollowInfo.DataBean.ItemsBean.UsersBean> users;

    public MasterFollowAdapter(Context context, List<MasterFollowInfo.DataBean.ItemsBean.UsersBean> users) {
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
            convertView = View.inflate(context, R.layout.follow_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MasterFollowInfo.DataBean.ItemsBean.UsersBean usersBean = users.get(position);
        Glide.with(context)
                .load(usersBean.getUser_image().getOrig())
                .into(viewHolder.followItemImage);
        viewHolder.followItemText.setText(usersBean.getUser_name());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.follow_item_image)
        ImageView followItemImage;
        @BindView(R.id.follow_item_text)
        TextView followItemText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
