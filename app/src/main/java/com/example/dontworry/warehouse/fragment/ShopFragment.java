package com.example.dontworry.warehouse.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.pager.BrandPager;
import com.example.dontworry.warehouse.pager.ClassificationPager;
import com.example.dontworry.warehouse.pager.GiftPager;
import com.example.dontworry.warehouse.pager.HomePage;
import com.example.dontworry.warehouse.pager.SpecialPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Don't worry on 2017/7/5.
 * 商店页面
 */

public class ShopFragment extends Fragment {


    @BindView(R.id.title_Search)
    ImageView titleSearch;
    @BindView(R.id.title_Text)
    TextView titleText;
    @BindView(R.id.title_Image)
    ImageView titleImage;
    @BindView(R.id.indicator)
    TabLayout indicator;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;

    private List<Fragment> fragments;
    private String[] items = new String[]{"分类", "品牌", "首页", "专题", "礼物"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_shop, null);
        unbinder = ButterKnife.bind(this, view);
        initFragment();

        MyPagerAdapter adapter = new MyPagerAdapter(getActivity().getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        titleText.setText("商店");
        viewPager.setCurrentItem(2);

        indicator.setupWithViewPager(viewPager);
        indicator.setTabMode(TabLayout.MODE_SCROLLABLE);
        return view;
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ClassificationPager());
        fragments.add(new BrandPager());
        fragments.add(new HomePage());
        fragments.add(new SpecialPager());
        fragments.add(new GiftPager());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragments;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return items[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
