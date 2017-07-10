package com.example.dontworry.warehouse.masterFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alibaba.fastjson.JSON;
import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.fragment.MasterItemActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;


/**
 * Created by Don't worry on 2017/7/9.
 * 达人-粉丝Fragment
 */

public class FansFragment extends Fragment {
    @BindView(R.id.gr_fans)
    GridView grFans;
    Unbinder unbinder;
    private Context context;
    private String uid;
    private List<MasterItemInfo.DataBean.ItemsBean.UsersBean> users;
    private FansItemAdapter adapter;
    private String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master_fans, container, false);
        uid = getActivity().getIntent().getStringExtra("uid");
        url = "http://mobile.iliangcang.com/user/masterFollowed?app_key=Android&count=12&owner_id=" + uid + "&page=1&sig=BF287AF953103F390674E73DDA18CFD8%7C639843030233268&v=1.0";
        Log.e("TAG", ""+uid);
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
        proFromNet();
    }


    private void proFromNet() {
        OkHttpUtils.get()
                .url(url)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("FansFragment", "FansFragment" + response);
                processData(response);
            }
        });
    }

    //解析
    private void processData(String json) {
        MasterItemInfo masterItemInfo = JSON.parseObject(json, MasterItemInfo.class);
        users = masterItemInfo.getData().getItems().getUsers();
        //设置适配器

        adapter = new FansItemAdapter(context, users);
        grFans.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
