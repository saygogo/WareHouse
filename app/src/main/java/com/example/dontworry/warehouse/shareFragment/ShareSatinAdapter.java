package com.example.dontworry.warehouse.shareFragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dontworry.warehouse.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Don't worry on 2017/7/10.
 */

public class ShareSatinAdapter extends BaseAdapter {


    private final Context context;
    private final List<ShareSatinInfo.ListBean> list;

    public ShareSatinAdapter(Context context, List<ShareSatinInfo.ListBean> list) {
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
            convertView = View.inflate(context, R.layout.share_satin_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShareSatinInfo.ListBean listBean = list.get(position);
        Glide.with(context)
                .load(listBean.getU().getHeader().get(0))
                .into(viewHolder.ivHeadpic);
        //用户名称
        viewHolder.tvName.setText(listBean.getU().getName());
        //时间
        viewHolder.tvTimeRefresh.setText(listBean.getPasstime());
        //内容
        viewHolder.tvContext.setText(listBean.getText());
        //点赞数
        viewHolder.tvShenheDingNumber.setText(listBean.getUp());
        //踩数
        viewHolder.tvShenheCaiNumber.setText(listBean.getDown()+"");
        //转发数
        viewHolder.tvPostsNumber.setText(listBean.getForward()+"");


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_headpic)
        ImageView ivHeadpic;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time_refresh)
        TextView tvTimeRefresh;
        @BindView(R.id.ll_video_user_info)
        LinearLayout llVideoUserInfo;
        @BindView(R.id.tv_context)
        TextView tvContext;
        @BindView(R.id.tv_ding)
        TextView tvDing;
        @BindView(R.id.tv_shenhe_ding_number)
        TextView tvShenheDingNumber;
        @BindView(R.id.ll_ding)
        LinearLayout llDing;
        @BindView(R.id.iv_cai)
        TextView ivCai;
        @BindView(R.id.tv_shenhe_cai_number)
        TextView tvShenheCaiNumber;
        @BindView(R.id.ll_cai)
        LinearLayout llCai;
        @BindView(R.id.tv_posts_number)
        TextView tvPostsNumber;
        @BindView(R.id.ll_share)
        LinearLayout llShare;
        @BindView(R.id.ll_download)
        LinearLayout llDownload;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
