package com.example.dontworry.warehouse.pager;

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
import com.example.dontworry.warehouse.adapter.HomeAdapter;
import com.example.dontworry.warehouse.bean.HomeInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by Don't worry on 2017/7/6.
 * 商店-首页页面
 */

public class HomePage extends Fragment {
    @BindView(R.id.recycleview_home)
    RecyclerView recycleviewHome;
    Unbinder unbinder;
    private HomeAdapter adapter;
    private Context context;
    private boolean isLoadMore = false;

    private String url = "http://mobile.iliangcang.com/goods/newShopHome?app_key=Android&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0";
    private List<HomeInfo.DataBean.ItemsBean.ListBeanX> items;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
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
        //联网请求数据
        proFromNet(url);
    }

    private void proFromNet(String url) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "HomePage===" + response);
                        processData(response);
                    }
                });
    }

    //解析数据
    private void processData(String json) {
        HomeInfo homeInfo = JSON.parseObject(json, HomeInfo.class);
        items = homeInfo.getData().getItems().getList();
        //设置适配器（分类型的recycleView的适配器 ）

        adapter = new HomeAdapter(context, items);
        recycleviewHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleviewHome.setAdapter(adapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}