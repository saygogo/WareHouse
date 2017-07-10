package com.example.dontworry.warehouse.shareFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dontworry.warehouse.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Don't worry on 2017/7/10.
 */

public class ShareRecommendAdapter extends RecyclerView.Adapter {

    private static final int TYPE_VIDEO = 0;
    private static final int TYPE_IMAGE = 1;
    private static final int TYPE_TEXT = 2;
    private static final int TYPE_GIF = 3;
    private final Context context;
    private final List<ShareRecommendInfo.ListBean> list;



    public ShareRecommendAdapter(Context context, List<ShareRecommendInfo.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = null;
        switch (viewType) {
            case TYPE_VIDEO:
                view = View.inflate(context, R.layout.video_item, null);
                viewHolder = new VideoViewHolder(view);
                break;

            case TYPE_IMAGE:
                view = View.inflate(context, R.layout.image_item, null);
                viewHolder = new ImageViewHolder(view);
                break;
            case TYPE_GIF:
                view = View.inflate(context, R.layout.gif_item, null);
                viewHolder = new GifViewHolder(view);
                break;
            case TYPE_TEXT:
                view = View.inflate(context, R.layout.text_item, null);
                viewHolder = new TextViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_VIDEO) {
            VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
            videoViewHolder.setData(list.get(position));
        } else if (getItemViewType(position) == TYPE_IMAGE) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            imageViewHolder.setData(list.get(position));
        } else if (getItemViewType(position) == TYPE_GIF) {
            GifViewHolder gifViewHolder = (GifViewHolder) holder;
            gifViewHolder.setData(list.get(position));
        } else if (getItemViewType(position) == TYPE_TEXT) {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
            textViewHolder.setData(list.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = -1;
        ShareRecommendInfo.ListBean listBean = list.get(position);
        String type = listBean.getType();
        if ("video".equals(type)) {
            itemViewType = TYPE_VIDEO;
        } else if ("image".equals(type)) {
            itemViewType = TYPE_IMAGE;
        } else if ("gif".equals(type)) {
            itemViewType = TYPE_GIF;
        } else if ("text".equals(type)) {
            itemViewType = TYPE_TEXT;
        }
        return itemViewType;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class VideoViewHolder extends RecyclerView.ViewHolder {
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
        @BindView(R.id.jcv_videoplayer)
        JCVideoPlayerStandard jcvVideoplayer;
        @BindView(R.id.tv_play_nums)
        TextView tvPlayNums;
        @BindView(R.id.tv_video_duration)
        TextView tvVideoDuration;
        @BindView(R.id.rl_holder)
        RelativeLayout rlHolder;
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

        VideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(ShareRecommendInfo.ListBean listBean) {
            //头像
            Glide.with(context)
                    .load(listBean.getU().getHeader().get(0))
                    .into(ivHeadpic);
            //用户名
            tvName.setText(listBean.getU().getName());
            //发表时间
            tvTimeRefresh.setText(listBean.getPasstime());
            //默认文字
            tvContext.setText(listBean.getText());
            //设置节操播放器的地址
            jcvVideoplayer.setUp(listBean.getVideo().getVideo().get(0), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,listBean.getText());
            //点赞人数
            tvShenheDingNumber.setText(listBean.getUp());
            //踩人数
            tvShenheCaiNumber.setText(listBean.getDown() + "");
            //转发人数
            tvPostsNumber.setText(listBean.getForward() + "");
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
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
        @BindView(R.id.iv_image_image)
        ImageView ivImageImage;

        ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void setData(ShareRecommendInfo.ListBean listBean) {
            //头像
            Glide.with(context)
                    .load(listBean.getU().getHeader().get(0))
                    .into(ivHeadpic);
            //用户名
            tvName.setText(listBean.getU().getName());
            //发表时间
            tvTimeRefresh.setText(listBean.getPasstime());
            //默认文字
            tvContext.setText(listBean.getText());
            //GIF的图片
            Glide.with(context)
                    .load(listBean.getImage().getBig().get(0))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivImageImage);
            //点赞人数
            tvShenheDingNumber.setText(listBean.getUp());
            //踩人数
            tvShenheCaiNumber.setText(listBean.getDown() + "");
            //转发人数
            tvPostsNumber.setText(listBean.getForward() + "");
        }
    }

    class GifViewHolder extends RecyclerView.ViewHolder {
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
        @BindView(R.id.iv_image_gif)
        ImageView ivImageGif;
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


        public GifViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(ShareRecommendInfo.ListBean listBean) {
            //头像
            Glide.with(context)
                    .load(listBean.getU().getHeader().get(0))
                    .into(ivHeadpic);
            //用户名
            tvName.setText(listBean.getU().getName());
            //发表时间
            tvTimeRefresh.setText(listBean.getPasstime());
            //默认文字
            tvContext.setText(listBean.getText());
            //GIF的图片
            Glide.with(context)
                    .load(listBean.getGif().getImages().get(0))
                    .asGif()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(ivImageGif);
            //点赞人数
            tvShenheDingNumber.setText(listBean.getUp());
            //踩人数
            tvShenheCaiNumber.setText(listBean.getDown() + "");
            //转发人数
            tvPostsNumber.setText(listBean.getForward() + "");
        }
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
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

        public TextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(ShareRecommendInfo.ListBean listBean) {
            //头像
            Glide.with(context)
                    .load(listBean.getU().getHeader().get(0))
                    .into(ivHeadpic);
            //用户名
            tvName.setText(listBean.getU().getName());
            //发表时间
            tvTimeRefresh.setText(listBean.getPasstime());
            //默认文字
            tvContext.setText(listBean.getText());
            //点赞人数
            tvShenheDingNumber.setText(listBean.getUp());
            //踩人数
            tvShenheCaiNumber.setText(listBean.getDown() + "");
            //转发人数
            tvPostsNumber.setText(listBean.getForward() + "");
        }
    }
}
