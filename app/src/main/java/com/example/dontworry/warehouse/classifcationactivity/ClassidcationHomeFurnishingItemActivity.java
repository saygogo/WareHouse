package com.example.dontworry.warehouse.classifcationactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastjsonSockJsMessageCodec;
import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.bean.ClassifctionInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
//家居Activity

public class ClassidcationHomeFurnishingItemActivity extends AppCompatActivity {

    @BindView(R.id.clssifcation_recycleview)
    RecyclerView clssifcationRecycleview;
    @BindView(R.id.activity_classidcationitem)
    RelativeLayout activityClassidcationitem;
    private Context context;
    private ClassifcationItemAdapter adapter;
    private String url = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0045&count=10&coverId=1&page=1&sig=3D3968703BE211CC6D931C9D4F737FB4%7C290216330933368&v=1.0";
    private List<CalssIfcationHomeFurnishingItemInfo.DataBean.ItemsBean> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classidcationitem);
        context = this;
        ButterKnife.bind(this);

        initData();

    }

    private void initData() {
        proFromNet();
    }

    private void proFromNet() {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "ClassidcationHomeFurnishingItemActivity" + response);
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        CalssIfcationHomeFurnishingItemInfo classifctionInfo = JSON.parseObject(json, CalssIfcationHomeFurnishingItemInfo.class);
        items = classifctionInfo.getData().getItems();

        adapter = new ClassifcationItemAdapter(context, items);
        clssifcationRecycleview.setLayoutManager(new GridLayoutManager(context,2));
        clssifcationRecycleview.setAdapter(adapter);

    }

}
