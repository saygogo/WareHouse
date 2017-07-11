package com.example.dontworry.warehouse.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.adapter.MasterAdapter;
import com.example.dontworry.warehouse.masterFragment.FansFragment;
import com.example.dontworry.warehouse.masterFragment.FollowFragment;
import com.example.dontworry.warehouse.masterFragment.LikeFragment;
import com.example.dontworry.warehouse.masterFragment.MasterItemInfo;
import com.example.dontworry.warehouse.masterFragment.RecommendFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MasterItemActivity extends AppCompatActivity {

    @BindView(R.id.title_Search)
    ImageView titleSearch;
    @BindView(R.id.title_Text)
    TextView titleText;
    @BindView(R.id.title_Image)
    ImageView titleImage;
    @BindView(R.id.master_image)
    ImageView masterImage;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.btn_private_letter)
    Button btnPrivateLetter;
    @BindView(R.id.text_duty)
    TextView textDuty;
    @BindView(R.id.rb_master_like)
    RadioButton rbMasterLike;
    @BindView(R.id.rb_master_recommend)
    RadioButton rbMasterRecommend;
    @BindView(R.id.rb_master_follow)
    RadioButton rbMasterFollow;
    @BindView(R.id.rb_master_fans)
    RadioButton rbMasterFans;
    @BindView(R.id.rg_master)
    RadioGroup rgMaster;
    @BindView(R.id.frameLayout_master)
    FrameLayout frameLayoutMaster;
    @BindView(R.id.activity_master_item)
    LinearLayout activityMasterItem;
    @BindView(R.id.like_count)
    TextView likeCount;
    @BindView(R.id.recommendation_count)
    TextView recommendationCount;
    @BindView(R.id.follow_count)
    TextView followCount;
    @BindView(R.id.fans_count)
    TextView fansCount;
    private ArrayList<Fragment> fragments;
    private Fragment tempFragment;
    private int position;
    private String uid;
    private MasterItemInfo.DataBean.ItemsBean items;


    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_item);
        ButterKnife.bind(this);
        uid =getIntent().getStringExtra("uid");
        url = "http://mobile.iliangcang.com/user/masterFollowed?app_key=Android&count=12&owner_id=" + uid + "&page=1&sig=BF287AF953103F390674E73DDA18CFD8%7C639843030233268&v=1.0";
        if (onItemClickListener != null) {
            onItemClickListener.OnItemClick(url);
        }


        initData();
        initFragment();
        rgMaster.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_master_like:
                        position = 0;
                        break;
                    case R.id.rb_master_recommend:
                        position = 1;
                        break;
                    case R.id.rb_master_follow:
                        position = 2;
                        break;
                    case R.id.rb_master_fans:
                        position = 3;
                        break;
                }
                Fragment currentFragment = fragments.get(position);
                switchAddFragment(currentFragment);
            }
        });
        rgMaster.check(R.id.rb_master_recommend);
    }

    private void initData() {
        //联网请求
        OkHttpUtils
                .get().url(url)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("MasterItemActivity", "response" + response);
                proFromNet(response);
            }
        });
    }

    //解析
    private void proFromNet(String json) {
        MasterItemInfo masterItemInfo = JSON.parseObject(json, MasterItemInfo.class);
        items = masterItemInfo.getData().getItems();
        username.setText(items.getUser_name());
        textDuty.setText(items.getUser_desc());
        likeCount.setText(items.getLike_count());
        recommendationCount.setText(items.getRecommendation_count());
        followCount.setText(items.getFollowing_count());
        fansCount.setText(items.getFollowed_count());
        Glide.with(this)
                .load(items.getUser_image().getOrig())
                .placeholder(R.drawable.good_big_bg)
                .error(R.drawable.good_big_bg)
                .into(masterImage);
    }

    private void switchAddFragment(Fragment currentFragment) {
        if (currentFragment != tempFragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (!currentFragment.isAdded()) {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.add(R.id.frameLayout_master, currentFragment);
            } else {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.show(currentFragment);
            }
            ft.commit();
            tempFragment = currentFragment;

        }
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new LikeFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new FollowFragment());
        fragments.add(new FansFragment());
    }

    public interface OnItemClickListener {
        void OnItemClick(String uid);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;
}
