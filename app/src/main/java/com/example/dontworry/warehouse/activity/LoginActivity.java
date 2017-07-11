package com.example.dontworry.warehouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dontworry.warehouse.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.edt_number)
    EditText edtNumber;
    @BindView(R.id.edt_yanzhengma)
    EditText edtYanzhengma;
    @BindView(R.id.btn_huoquyanzhengma)
    Button btnHuoquyanzhengma;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;

    private boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.btn_huoquyanzhengma, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                //返回键
                finish();
                break;
            case R.id.btn_huoquyanzhengma:
                //获取验证码
                Toast.makeText(LoginActivity.this, "获取验证码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_login:
                String edtnumber = edtNumber.getText().toString().trim();
                String edtyanzhengma = edtYanzhengma.getText().toString().trim();
                if(TextUtils.isEmpty(edtnumber)||TextUtils.isEmpty(edtyanzhengma)){
                    Toast.makeText(LoginActivity.this, "手机号或者验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(this,MySelfActivity.class);
                startActivity(intent);
                isLogin =true;
                finish();
                break;
        }
    }
    public boolean getIsLogin(){
        return isLogin;
    }
}
