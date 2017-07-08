package com.example.dontworry.warehouse.adapter;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dontworry.warehouse.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页H5页面
 */
public class HomeItemActivity extends AppCompatActivity {

    @BindView(R.id.title_Search)
    ImageView titleSearch;
    @BindView(R.id.title_Text)
    TextView titleText;
    @BindView(R.id.title_Image)
    ImageView titleImage;
    @BindView(R.id.HomeWebView)
    WebView HomeWebView;
    @BindView(R.id.activity_home_item)
    LinearLayout activityHomeItem;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_item);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
         url = getIntent().getStringExtra("topic_url");
        titleSearch.setImageResource(R.drawable.ease_mm_title_back);
        titleImage.setVisibility(View.GONE);
        titleSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setWebViewData();
    }

    private void setWebViewData() {
        WebSettings webSettings = HomeWebView.getSettings();
        //设置支持js
        webSettings.setJavaScriptEnabled(true);
        //设置支持双击变大变小
        webSettings.setUseWideViewPort(true);

        //设置检索缓存的
        webSettings.setCacheMode(webSettings.LOAD_CACHE_ELSE_NETWORK);

        //设置不跳转到系统的浏览器
        HomeWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        HomeWebView.loadUrl(url);
    }
}
