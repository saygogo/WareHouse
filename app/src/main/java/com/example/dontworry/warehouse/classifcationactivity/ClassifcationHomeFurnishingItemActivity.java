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
    private int position;
    private String url1;
    private CalssIfcationHomeFurnishingItemInfo.DataBean.ItemsBean itemsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classidcationitem);
        context = this;
        ButterKnife.bind(this);

        position = getIntent().getIntExtra("position", -1);
        url1 = urls[position];
        Log.e("TAG", ""+position);
        initData(url1);
    }

    private void initData(String url1) {
        proFromNet(url1);
    }

    private void proFromNet(String url1) {
        OkHttpUtils.get()
                .url(url1)
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


       // CalssIfcationHomeFurnishingItemInfo.DataBean.ItemsBean itemsBean = items.get(position);


        adapter = new ClassifcationItemAdapter(context, items);
        clssifcationRecycleview.setLayoutManager(new GridLayoutManager(context, 2));
        clssifcationRecycleview.setAdapter(adapter);
//        String goods_id = itemsBean.getGoods_id();
//        Intent intent = new Intent(context,ClassifcationHomeFurnishingDetailsItemActivity.class);
//        Log.e("TAG", "ClassifcationHomegoods_id"+goods_id);
//        intent.putExtra("good_id",goods_id);
//        context.startActivity(intent);
        adapter.setOnItemClickListener(new ClassifcationItemAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                itemsBean = items.get(position);
                String goods_id = itemsBean.getGoods_id();
                Intent intent = new Intent(context,ClassifcationHomeFurnishingDetailsItemActivity.class);
                intent.putExtra("good_id",goods_id);
                startActivity(intent);
            }
        });
    }
}
