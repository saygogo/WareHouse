package com.example.dontworry.warehouse.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.fragment.MagazineFragment;
import com.example.dontworry.warehouse.fragment.MasterFragment;
import com.example.dontworry.warehouse.fragment.PersonalFragment;
import com.example.dontworry.warehouse.fragment.ShareFragment;
import com.example.dontworry.warehouse.fragment.ShopFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
//主页面
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.FrameLayout_main)
    FrameLayout FrameLayoutMain;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private ArrayList<Fragment> fragments;

    //设置对应的下标位置
    private int position = 0;
    //缓存的Fragment
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initFragment();

        //切换点击RadioButton
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_shop:
                        position = 0;
                        break;
                    case R.id.rb_magazine:
                        position = 1;
                        break;
                    case R.id.rb_master:
                        position = 2;
                        break;
                    case R.id.rb_share:
                        position = 3;
                        break;
                    case R.id.rb_personal:
                        position = 4;
                        break;
                }
                Fragment currentFragment = fragments.get(position);
                switchAddFragment(currentFragment);
            }
        });
        rgMain.check(R.id.rb_shop);
    }
/*    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_shop:
                    position = 0;
                    break;
                case R.id.rb_magazine:
                    position = 1;
                    break;
                case R.id.rb_master:
                    position = 2;
                    break;
                case R.id.rb_share:
                    position = 3;
                    break;
                case R.id.rb_personal:
                    position = 4;
                    break;
            }
            Fragment currentFragment = fragments.get(position);
            switchAddFragment(currentFragment);
        }*/

    private void switchAddFragment(Fragment currentFragment) {
        if (currentFragment != tempFragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if (!currentFragment.isAdded()) {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.add(R.id.FrameLayout_main, currentFragment);
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
        fragments.add(new ShopFragment());//购物
        fragments.add(new MagazineFragment());//杂志
        fragments.add(new MasterFragment());//达人
        fragments.add(new ShareFragment());//分享
        fragments.add(new PersonalFragment());//个人
    }
}
