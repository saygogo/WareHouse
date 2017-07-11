package com.example.dontworry.warehouse.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Don't worry on 2017/7/5.
 * 个人页面
 */

public class PersonalFragment extends Fragment {

    @BindView(R.id.title_Search)
    ImageView titleSearch;
    @BindView(R.id.title_Text)
    TextView titleText;
    @BindView(R.id.title_Image)
    ImageView titleImage;
    @BindView(R.id.personal_self)
    ImageView personalSelf;
    @BindView(R.id.btn_denglu)
    Button btnDenglu;
    @BindView(R.id.btn_dindan)
    Button btnDindan;
    @BindView(R.id.btn_hongbao)
    Button btnHongbao;
    @BindView(R.id.rl_qingdan)
    RelativeLayout rlQingdan;
    @BindView(R.id.lv_xiaoxi)
    RelativeLayout lvXiaoxi;
    @BindView(R.id.rl_dizhi)
    RelativeLayout rlDizhi;
    @BindView(R.id.rl_kefu)
    RelativeLayout rlKefu;
    Unbinder unbinder;

    private Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context =context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.title_Search, R.id.title_Text, R.id.title_Image, R.id.personal_self, R.id.btn_denglu, R.id.btn_dindan, R.id.btn_hongbao, R.id.rl_qingdan, R.id.lv_xiaoxi, R.id.rl_dizhi, R.id.rl_kefu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_Search:

                break;
            case R.id.title_Text:
                break;
            case R.id.title_Image:
                break;
            //头像
            case R.id.personal_self:
       // Toast.makeText(context, "personal_self", Toast.LENGTH_SHORT).show();
                if(isLogin()){

                }else{
                    Intent intent = new Intent(context,LoginActivity.class);
                    startActivity(intent);
                }

                break;
            //登录
            case R.id.btn_denglu:
             //   Toast.makeText(context, "btn_denglu", Toast.LENGTH_SHORT).show();
                break;
            //我的订单
            case R.id.btn_dindan:
               // Toast.makeText(context, "btn_dindan", Toast.LENGTH_SHORT).show();
                if(isLogin()){

                }else{
                    Intent intent = new Intent(context,LoginActivity.class);
                    startActivity(intent);
                }
                break;
            //我的红包
            case R.id.btn_hongbao:
              //  Toast.makeText(context, "btn_hongbao", Toast.LENGTH_SHORT).show();
                if(isLogin()){

                }else{
                    Intent intent = new Intent(context,LoginActivity.class);
                    startActivity(intent);
                }
                break;
            //我的心愿单
            case R.id.rl_qingdan:
              //  Toast.makeText(context, "rl_qingdan", Toast.LENGTH_SHORT).show();
                if(isLogin()){

                }else{
                    Intent intent = new Intent(context,LoginActivity.class);
                    startActivity(intent);
                }
                break;
            //我的消息
            case R.id.lv_xiaoxi:
               // Toast.makeText(context, "lv_xiaoxi", Toast.LENGTH_SHORT).show();
                if(isLogin()){

                }else{
                    Intent intent = new Intent(context,LoginActivity.class);
                    startActivity(intent);
                }
                break;
            //地址管理
            case R.id.rl_dizhi:
             //   Toast.makeText(context, "rl_dizhi", Toast.LENGTH_SHORT).show();
                if(isLogin()){

                }else{
                    Intent intent = new Intent(context,LoginActivity.class);
                    startActivity(intent);
                }
                break;
            //良仓客服
            case R.id.rl_kefu:
            //    Toast.makeText(context, "rl_kefu", Toast.LENGTH_SHORT).show();
                if(isLogin()){

                }else{
                    Intent intent = new Intent(context,LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    private boolean isLogin() {
        return false;
    }

}
