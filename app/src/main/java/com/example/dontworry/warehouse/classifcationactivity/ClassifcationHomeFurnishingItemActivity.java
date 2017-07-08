package com.example.dontworry.warehouse.classifcationactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.Utils.Contants;
import com.example.dontworry.warehouse.pager.ClassificationPager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
//itemActivity

public class ClassifcationHomeFurnishingItemActivity extends AppCompatActivity {

    @BindView(R.id.clssifcation_recycleview)
    RecyclerView clssifcationRecycleview;
    @BindView(R.id.activity_classidcationitem)
    RelativeLayout activityClassidcationitem;
    private Context context;
    private ClassifcationItemAdapter adapter;

    private String[] urls = {
            Contants.HOMEFURNISHING1,
            Contants.FURNITURE2,
            Contants.STATIONERY3,
            Contants.DIGITAL4,
            Contants.FUN5,
            Contants.KITCHENANDTOILET6,
            Contants.DELICIOUSFOOD7,
            Contants.MENSWEAR8,
            Contants.LADIES9,
            Contants.CHILDRENSCLOTHING10,
            Contants.SHOESANDBAGS11,
            Contants.ACCESSORIES12,
            Contants.BEAUTYCARE13,
            Contants.OUTDOORS14,
            Contants.BOTANY15,
            Contants.BOOKS16,
            Contants.GIFT17,
            Contants.RECOMMEND18,
            Contants.ART19,
    };
    private List<CalssIfcationHomeFurnishingItemInfo.DataBean.ItemsBean> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classidcationitem);
        context = this;
        ButterKnife.bind(this);

        int position = getIntent().getIntExtra("position", -1);
        Log.e("TAG", ""+position);
        initData(urls[position]);
    }

    private void initData(String position) {
        proFromNet(position);
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
                        Log.e("TAG", "ClassifcationHomeFurnishingItemActivity" + response);
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        CalssIfcationHomeFurnishingItemInfo classifcationInfo = JSON.parseObject(json, CalssIfcationHomeFurnishingItemInfo.class);
        items = classifcationInfo.getData().getItems();

        adapter = new ClassifcationItemAdapter(context, items);
        clssifcationRecycleview.setLayoutManager(new GridLayoutManager(context, 2));
        clssifcationRecycleview.setAdapter(adapter);

        adapter.setOnItemClickListener(new ClassifcationItemAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Toast.makeText(context, "position"+position, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ClassifcationHomeFurnishingItemActivity.this, ClassifcationHomeFurnishingDetailsItemActivity.class));
            }
        });
    }

}
