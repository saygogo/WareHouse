package com.example.dontworry.warehouse.fragment.magazine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dontworry.warehouse.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Don't worry on 2017/7/11.
 */

public class MasterMagazineAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<MagazineInfo> list;

    public MasterMagazineAdapter(Context context, ArrayList<MagazineInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
            convertView = View.inflate(context, R.layout.magazine_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MagazineInfo magazineInfo = list.get(position);
        Glide.with(context)
                .load(magazineInfo.getCover_img())
                .into(viewHolder.magazineImage);
        viewHolder.magazineText.setText(magazineInfo.getTopic_name());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.magazine_image)
        ImageView magazineImage;
        @BindView(R.id.magazine_text)
        TextView magazineText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
