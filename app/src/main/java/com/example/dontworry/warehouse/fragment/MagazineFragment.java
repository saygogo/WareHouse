package com.example.dontworry.warehouse.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.fragment.magazine.MagazineInfo;
import com.example.dontworry.warehouse.fragment.magazine.MasterMagazineAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by Don't worry on 2017/7/5.
 * 杂志页面
 */

public class MagazineFragment extends Fragment {
    @BindView(R.id.title_Search)
    ImageView titleSearch;
    @BindView(R.id.title_Text)
    TextView titleText;
    @BindView(R.id.title_Image)
    ImageView titleImage;
    @BindView(R.id.lv_magazine)
    ListView lvMagazine;
    Unbinder unbinder;
    private Context context;
    private MasterMagazineAdapter adapter;
    private ArrayList<MagazineInfo> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_magzine, container, false);
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
        String url = "http://mobile.iliangcang.com/topic/magazineList?app_key=Android&author_id=1&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";

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
                        Log.e("MagazineFragment", "MagazineFragment" + response);
                        processData(response);
                    }
                });
    }

    private void processData(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject dataObject = jsonObject.optJSONObject("data");
            JSONObject itemsObject = dataObject.optJSONObject("items");
            JSONArray keysArray = itemsObject.optJSONArray("keys");
            JSONObject infosObject = itemsObject.optJSONObject("infos");

            list = new ArrayList<>();
            for(int i = 0; i < keysArray.length(); i++) {
                JSONArray jsonArray = infosObject.optJSONArray(keysArray.get(i) + "");
                
                for(int j = 0; j < jsonArray.length(); j++) {
                    MagazineInfo magazineInfo = new MagazineInfo();
                    JSONObject object = jsonArray.optJSONObject(j);
                    String taid = object.optString("taid");
                    magazineInfo.setTaid(taid);

                    String topic_name = object.optString("topic_name");
                    magazineInfo.setTopic_name(topic_name);

                    String topic_url = object.optString("topic_url");
                    magazineInfo.setTopic_url(topic_url);

                    String cat_name = object.optString("cat_name");
                    magazineInfo.setCat_name(cat_name);

                    String cover_img = object.optString("cover_img");
                    magazineInfo.setCover_img(cover_img);

                    String addtime = object.optString("addtime");
                    magazineInfo.setAddtime(addtime);

                    list.add(magazineInfo);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        try {
//            JSONObject jsonObject = new JSONObject(json);
//            JSONObject data = jsonObject.getJSONObject("data");
//            JSONObject items = data.getJSONObject("items");
//            JSONArray keys = items.getJSONArray("keys");
//
//            list = new ArrayList();
//            JSONObject infos = items.getJSONObject("infos");
//            for (int i = 0; i < keys.length(); i++) {
//                String key = keys.getString(i);
//                JSONArray key2 = infos.getJSONArray(key);
//                int length = key2.length();
//                for (int i1 = 0; i1 < key2.length(); i1++) {
//                    MagazineInfo magazineInfo = new MagazineInfo();
//                    JSONObject info = key2.getJSONObject(i);
//                    String topic_name = info.getString("topic_name");
//                    magazineInfo.setTopic_name(topic_name);
//                    String cat_name = info.getString("cat_name");
//                    Log.e("TAG", "name" + cat_name);
//                    magazineInfo.setCat_name(cat_name);
//                    String cover_img = info.getString("cover_img");
//                    Log.e("TAG", "cover_img" + cover_img);
//                    magazineInfo.setCover_img(cover_img);
//                    String topic_url = info.getString("topic_url");
//                    Log.e("TAG", "topic_url" + topic_url);
//                    magazineInfo.setTopic_url(topic_url);
//                    magazineInfo.setAddtime(info.getString("addtime").substring(5, 10));
//                    list.add(magazineInfo);
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        adapter = new MasterMagazineAdapter(context, list);
        Log.e("TAG", "list" + list.toString());
        lvMagazine.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
