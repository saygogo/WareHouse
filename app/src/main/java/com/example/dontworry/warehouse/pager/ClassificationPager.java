package com.example.dontworry.warehouse.pager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.Utils.Contants;
import com.example.dontworry.warehouse.adapter.ClassifcationAdapter;
import com.example.dontworry.warehouse.bean.ClassifctionInfo;
import com.example.dontworry.warehouse.classifcationactivity.CalssIfcationHomeFurnishingItemInfo;
import com.example.dontworry.warehouse.classifcationactivity.ClassifcationHomeFurnishingItemActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by Don't worry on 2017/7/6.
 * 商店-分类页面
 */

public class ClassificationPager extends Fragment {
    @BindView(R.id.rv_classifcation)
    RecyclerView rvClassifcation;
    Unbinder unbinder;
    private String url = "http://mobile.iliangcang.com/goods/goodsCategory?app_key=Android&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
    private List<ClassifctionInfo.DataBean.ItemsBean> items;
    private ClassifcationAdapter adapter;
    private Context context;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classifcation, container, false);
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
                        Log.e("TAG", "onResponse====" + response);
                        //解析
                        processData(response);
                    }
                });
    }

    //使用fastJson解析数据
    private void processData(String json) {

        ClassifctionInfo classifctionInfo = JSON.parseObject(json, ClassifctionInfo.class);
        items = classifctionInfo.getData().getItems();

        Log.e("TAG", "items" + items.get(0).getCat_id());
        //解析成功 设置适配器
        adapter = new ClassifcationAdapter(context,items);
        rvClassifcation.setLayoutManager(new GridLayoutManager(context,2));
        rvClassifcation.setAdapter(adapter);
        adapter.setOnItemClickListener(new ClassifcationAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ClassifcationHomeFurnishingItemActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
