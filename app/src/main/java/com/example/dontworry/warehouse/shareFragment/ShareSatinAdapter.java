package com.example.dontworry.warehouse.shareFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.dontworry.warehouse.R;

import java.util.List;

/**
 * Created by Don't worry on 2017/7/10.
 */

public class ShareSatinAdapter extends RecyclerView.Adapter {


    private final Context context;
    private final List<ShareSatinInfo.ListBean> list;

    public ShareSatinAdapter(Context context, List<ShareSatinInfo.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.share_satin_item,null);
        return null ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }
}
