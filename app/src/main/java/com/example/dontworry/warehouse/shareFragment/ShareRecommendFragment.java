package com.example.dontworry.warehouse.shareFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.example.dontworry.warehouse.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by Don't worry on 2017/7/10.
 * 分享-推荐页面
 */

public class ShareRecommendFragment extends Fragment {
    @BindView(R.id.share_recycleView)
    RecyclerView shareRecycleView;
    Unbinder unbinder;
    private Context context;
    String url = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.6.3/0-20.json";
    private List<ShareRecommendInfo.ListBean> list;
    private ShareRecommendAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share_recommend, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        proFromNet(url);
    }

    private void proFromNet(String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("ShareRecommendFragment", "ShareRecommendFragment"+response);
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        ShareRecommendInfo shareRecommendInfo = JSON.parseObject(json, ShareRecommendInfo.class);
        list = shareRecommendInfo.getList();
        //设置RecycleView 的适配器

        adapter = new ShareRecommendAdapter(context,list);
        shareRecycleView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        shareRecycleView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
