package com.example.dontworry.warehouse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dontworry.warehouse.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplshActivity extends AppCompatActivity {


    @BindView(R.id.splsh_image)
    ImageView splshImage;
    @BindView(R.id.activity_splsh)
    RelativeLayout activitySplsh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splsh);
        ButterKnife.bind(this);

        Glide.with(this)
                .load(R.drawable.loading_start)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(splshImage);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplshActivity.this, MainActivity.class);
                startActivity(intent);
            }
        },6000);


    }
}
