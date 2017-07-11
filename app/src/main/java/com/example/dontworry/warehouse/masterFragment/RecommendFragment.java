package com.example.dontworry.warehouse.masterFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.alibaba.fastjson.JSON;
import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.masterFragment.recommend.MasterRecommendAdapter;
import com.example.dontworry.warehouse.masterFragment.recommend.MasterRecommendInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by Don't worry on 2017/7/9.
 * <p>
 * 达人—推荐Fragment
 */

public class RecommendFragment extends Fragment {
    @BindView(R.id.gv_recommend)
    GridView gvRecommend;
    Unbinder unbinder;

    private Context context;
    private String uid;
    private String url;
    private List<MasterRecommendInfo.DataBean.ItemsBean.GoodsBean> items;
    private MasterRecommendAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fargment_recommend, container, false);
        uid = getActivity().getIntent().getStringExtra("uid");
        url = "http://mobile.iliangcang.com/user/masterListInfo?app_key=Android&count=10&owner_id="+uid+"&page=1&sig=BF287AF953103F390674E73DDA18CFD8%7C639843030233268&v=1.0";
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
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        MasterRecommendInfo masterRecommendInfo = JSON.parseObject(json, MasterRecommendInfo.class);
        items = masterRecommendInfo.getData().getItems().getGoods();

        adapter  = new MasterRecommendAdapter(context,items);
        gvRecommend.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
