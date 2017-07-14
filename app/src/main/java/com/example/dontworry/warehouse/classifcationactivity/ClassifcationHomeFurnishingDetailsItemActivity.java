package com.example.dontworry.warehouse.classifcationactivity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.classifcationactivity.classIfcationHomeItem.ClassifcationHomeFurnishingDetailsItemInfo;
import com.example.dontworry.warehouse.classifcationactivity.classIfcationHomeItem.GlideImageLoader;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

//商店-分类- - 详情Activity
public class ClassifcationHomeFurnishingDetailsItemActivity extends AppCompatActivity {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_brandname)
    TextView tvBrandname;
    @BindView(R.id.tv_goodsname)
    TextView tvGoodsname;
    @BindView(R.id.tv_like_count)
    TextView tvLikeCount;
    @BindView(R.id.ll_collect)
    LinearLayout llCollect;
    @BindView(R.id.tv_promotion_note)
    TextView tvPromotionNote;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_goodsprice)
    TextView tvGoodsprice;
    @BindView(R.id.tv_oldprice)
    TextView tvOldprice;
    @BindView(R.id.tv_free)
    TextView tvFree;
    @BindView(R.id.tv_realgood)
    TextView tvRealgood;
    @BindView(R.id.tv_preslod)
    TextView tvPreslod;
    @BindView(R.id.tv_selected)
    TextView tvSelected;
    @BindView(R.id.ll_choicegoods)
    LinearLayout llChoicegoods;
    @BindView(R.id.iv_heading)
    ImageView ivHeading;
    @BindView(R.id.tv_brandname2)
    TextView tvBrandname2;
    @BindView(R.id.ll_brandinfo)
    LinearLayout llBrandinfo;
    @BindView(R.id.rb_goods_info)
    RadioButton rbGoodsInfo;
    @BindView(R.id.rb_goods_notice)
    RadioButton rbGoodsNotice;
    @BindView(R.id.rg_select_show)
    RadioGroup rgSelectShow;
    @BindView(R.id.ll_images)
    LinearLayout llImages;
    @BindView(R.id.tv_textInfo)
    TextView tvTextInfo;
    @BindView(R.id.ll_brandInfo)
    LinearLayout llBrandInfo;
    @BindView(R.id.tv_ownername)
    TextView tvOwnername;
    @BindView(R.id.tv_brandinfo)
    TextView tvBrandinfo;
    @BindView(R.id.tv_recommend)
    TextView tvRecommend;
    @BindView(R.id.ll_recommend)
    LinearLayout llRecommend;
    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;
    @BindView(R.id.ll_like)
    LinearLayout llLike;
    @BindView(R.id.rl_goodsInfo)
    RelativeLayout rlGoodsInfo;
    @BindView(R.id.tv_goodsnotice)
    TextView tvGoodsnotice;
    @BindView(R.id.ll_goodsnotice)
    LinearLayout llGoodsnotice;
    @BindView(R.id.sv_goodsinfo)
    ScrollView svGoodsinfo;
    @BindView(R.id.iv_goodsinfo_back)
    ImageView ivGoodsinfoBack;
    @BindView(R.id.iv_goodsinfo_cart)
    ImageView ivGoodsinfoCart;
    @BindView(R.id.id_goodsinfo_service)
    ImageButton idGoodsinfoService;
    @BindView(R.id.bt_add_in_cart)
    Button btAddInCart;
    @BindView(R.id.bt_buy)
    Button btBuy;
    private String good_id;
    private ClassifcationHomeFurnishingDetailsItemInfo.DataBean.ItemsBean items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classidcation_home_furnishing_details_item);
        ButterKnife.bind(this);
        good_id = getIntent().getStringExtra("good_id");
        Log.e("TAG", "gDetailsItemActivitygood_id" + good_id);
        initData();
    }

    private void initData() {
        String url = "http://mobile.iliangcang.com/goods/goodsDetail?app_key=Android&goods_id=" + good_id + "&sig=BF287AF953103F390674E73DDA18CFD8%7C639843030233268&v=1.0";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "ClassifcationHomeFurnishingDetailsItemActivity" + response);
                        proFromNet(response);
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void proFromNet(String json) {
        ClassifcationHomeFurnishingDetailsItemInfo info = JSON.parseObject(json, ClassifcationHomeFurnishingDetailsItemInfo.class);
        items = info.getData().getItems();
        ivGoodsinfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        banner.setImages(items.getImages_item()).setImageLoader(new GlideImageLoader()).start();

        tvBrandname.setText(items.getBrand_info().getBrand_name());
        tvGoodsname.setText(items.getGoods_name());
        tvLikeCount.setText(items.getLike_count());
        tvPromotionNote.setText(items.getPromotion_note());
        tvGoodsprice.setText(items.getPrice());
        Glide.with(this)
                .load(items.getBrand_info().getBrand_logo())
                .into(ivHeading);
        tvBrandname2.setText(items.getBrand_info().getBrand_name());


        List<String> imageUrls = new ArrayList<>();
        List<ClassifcationHomeFurnishingDetailsItemInfo.DataBean.ItemsBean.GoodsInfoBean> goods_info = items.getGoods_info();
        if (goods_info != null) {
            for (int i = 0; i < goods_info.size(); i++) {
                ClassifcationHomeFurnishingDetailsItemInfo.DataBean.ItemsBean.GoodsInfoBean goodsInfoBean = goods_info.get(i);
                int type = goodsInfoBean.getType();
                if (type == 1) {
                    //是图片
                    imageUrls.add(goodsInfoBean.getContent().getImg());
                    Log.e("TAG", "imageUrls==" + goodsInfoBean.getContent().getImg());
                }

            }
        }
        if (imageUrls != null && imageUrls.size() > 0) {
            for (int i = 0; i < imageUrls.size(); i++) {
                ImageView imageView = new ImageView(this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setCropToPadding(true);
                imageView.setAdjustViewBounds(true);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                imageView.setLayoutParams(params);
                llImages.addView(imageView);
                Glide.with(this).load(imageUrls.get(i)).into(imageView);
            }
        }
        tvBrandinfo.setText(items.getGoods_desc());

        tvRecommend.setText(items.getBrand_info().getBrand_desc());


    }
}
