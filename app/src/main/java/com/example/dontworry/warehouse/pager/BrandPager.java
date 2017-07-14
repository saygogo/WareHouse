package com.example.dontworry.warehouse.pager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.adapter.BrandAdapter;
import com.example.dontworry.warehouse.bean.BrandInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by Don't worry on 2017/7/6.
 * 商店-品牌页面
 *
 */

public class BrandPager extends Fragment {
    @BindView(R.id.lv_brand)
    ListView lvBrand;
    Unbinder unbinder;
    private Context context;
    private BrandAdapter adapter;
    private String url = "http://mobile.iliangcang.com/brand/brandList?app_key=Android&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
    private List<BrandInfo.DataBean.ItemsBean> items;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brand, container, false);
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

        //联网请求
        proFromNet(url);
    }

    private void proFromNet(String url) {
        OkHttpUtils.get()
                .url(url)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("onResponse", "onResponse==="+response);
                processData(response);
            }


        });
    }
//使用fastJson解析
    private void processData(String json) {
        BrandInfo brandInfo = JSON.parseObject(json, BrandInfo.class);
        items = brandInfo.getData().getItems();
        Log.e("processData", "processData===="+items.get(0).getBrand_name());

        //设置适配器
        adapter = new BrandAdapter(context,items);
        lvBrand.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
