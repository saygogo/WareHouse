package com.example.dontworry.warehouse.cart;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.db.cartBeanDAO;
import com.example.dontworry.warehouse.db.cartBeanInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dontworry.warehouse.R.id.cb_all;
import static com.example.dontworry.warehouse.classifcationactivity.ClassifcationHomeFurnishingDetailsItemActivity.CARTBEAN;

/**
 * 购物车页面
 */

public class cartBuyActivity extends AppCompatActivity {

    @BindView(R.id.back_cart)
    ImageView backCart;
    @BindView(R.id.cart_buy)
    TextView cartBuy;
    @BindView(R.id.rl_cart)
    RelativeLayout rlCart;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.activity_cart_buy)
    RelativeLayout activityCartBuy;
    @BindView(R.id.ll_dazhe)
    LinearLayout llDazhe;
    @BindView(cb_all)
    CheckBox cbAll;
    @BindView(R.id.btn_Settlement)
    Button btnSettlement;
    @BindView(R.id.cart_total)
    TextView cartTotal;
    //编辑状态
    private static final int ACTION_EDIT = 1;
    //完成状态
    private static final int ACTION_COMPLETE = 2;
    @BindView(R.id.ll_check_all)
    LinearLayout llCheckAll;
    @BindView(R.id.cb_all_deleter)
    CheckBox cbAllDeleter;
    @BindView(R.id.cart_total_deleter)
    TextView cartTotalDeleter;
    @BindView(R.id.btn_Settlement_deleter)
    Button btnSettlementDeleter;
    @BindView(R.id.ll_deleter)
    LinearLayout llDeleter;
    private shoppingCartAdapter adapter;
    private Context context;
    private cartBeanDAO cartBeanDAO;
    private List<cartBeanInfo> cartBeanInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy);
        ButterKnife.bind(this);
        cartBuy.setTag(ACTION_EDIT);
        cartBeanDAO = new cartBeanDAO(this);
        cartBeanInfos = cartBeanDAO.getcart();
        context = this;
        showData();
    }


    private void showData() {
        //设置recyclerView的适配器

        adapter = new shoppingCartAdapter(context, cartBeanInfos);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    }
}
