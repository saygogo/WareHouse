package com.example.dontworry.warehouse.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.adapter.MasterAdapter;
import com.example.dontworry.warehouse.bean.MasterInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by Don't worry on 2017/7/5.
 */

public class MasterFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.rv_master)
    RecyclerView rvMaster;
    @BindView(R.id.title_Search)
    ImageView titleSearch;
    @BindView(R.id.title_Text)
    TextView titleText;
    @BindView(R.id.title_Image)
    ImageView titleImage;
    private MasterAdapter adapter;
    private Context context;
    private ArrayList<MasterInfo> masterInfo;
    private String url = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&page=1&sig=BF287AF953103F390674E73DDA18CFD8|639843030233268&v=1.0";
    private List<MasterInfo.DataBean.ItemsBean> datas;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // View view = View.inflate(getActivity(), R.layout.fragment_master, null);
        View view = inflater.inflate(R.layout.fragment_master, container, false);
        unbinder = ButterKnife.bind(this, view);

        titleText.setText("达人");
        return view;
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
                        Log.e("TAG", "onResponse=====" + response);
                        proFromData(response);
                    }
                });
    }

    private void proFromData(String json) {
        MasterInfo masterInfo = JSON.parseObject(json, MasterInfo.class);
        datas = masterInfo.getData().getItems();
        Log.e("TAG", "proFromData" + datas.get(0).getNickname());
        adapter = new MasterAdapter(context, datas);
        rvMaster.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        rvMaster.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
