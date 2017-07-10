package com.example.dontworry.warehouse.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.masterFragment.RecommendFragment;
import com.example.dontworry.warehouse.shareFragment.ShareRecommendFragment;
import com.example.dontworry.warehouse.shareFragment.ShareSatinFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Don't worry on 2017/7/5.
 * 分享页面
 */

public class ShareFragment extends Fragment {
    @BindView(R.id.share_tablayout)
    TabLayout shareTablayout;
    @BindView(R.id.share_viewpager)
    ViewPager shareViewpager;
    Unbinder unbinder;
    private String[]items = {"推荐","段子"};
    private ArrayList<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        unbinder = ButterKnife.bind(this, view);
        initFragment();
        MyAdapter myAdapter = new MyAdapter(getActivity().getSupportFragmentManager(), fragments);
        shareViewpager.setAdapter(myAdapter);
        shareTablayout.setupWithViewPager(shareViewpager);

        return view;
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ShareRecommendFragment());
        fragments.add(new ShareSatinFragment());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    class MyAdapter extends FragmentPagerAdapter{

        private final ArrayList<Fragment> fragments;

        public MyAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return items[position];
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
