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
import com.example.dontworry.warehouse.masterFragment.like.MasterLikeAdapter;
import com.example.dontworry.warehouse.masterFragment.like.MasterLikeInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by Don't worry on 2017/7/9.
 * 达人—喜欢Fragment
 */

public class LikeFragment extends Fragment {
    @BindView(R.id.gv_like)
    GridView gvLike;
    Unbinder unbinder;
    private String uid;
    private String url;
    private Context context;
    private MasterLikeAdapter adapter;
    private List<MasterLikeInfo.DataBean.ItemsBean.GoodsBean> items;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_like, container, false);
        uid = getActivity().getIntent().getStringExtra("uid");
        url = "http://mobile.iliangcang.com/user/masterLike?app_key=Android&count=12&owner_id="+uid+"&page=1&sig=BF287AF953103F390674E73DDA18CFD8|639843030233268&v=1.0";
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
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        MasterLikeInfo masterLikeInfo = JSON.parseObject(json, MasterLikeInfo.class);
        items = masterLikeInfo.getData().getItems().getGoods();

        adapter = new  MasterLikeAdapter(context,items);
        gvLike.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
