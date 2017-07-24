package com.example.dontworry.warehouse.cart;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.db.cartBeanDAO;
import com.example.dontworry.warehouse.db.cartBeanInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
    @BindView(R.id.cb_all)
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

        adapter = new shoppingCartAdapter(context, cartBeanInfos, cbAll, cartTotal, cbAllDeleter);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    }

    @OnClick({R.id.cart_total, R.id.btn_Settlement, R.id.cart_total_deleter, R.id.back_cart, R.id.cart_buy, R.id.cb_all, R.id.cb_all_deleter, R.id.btn_Settlement_deleter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_cart:
                //返回
                finish();
                break;
            case R.id.cart_buy:
                //编辑
                Toast.makeText(cartBuyActivity.this, "编辑", Toast.LENGTH_SHORT).show();
                int tag = (int) cartBuy.getTag();
                if (tag == ACTION_EDIT) {
                    showDeleter();
                } else {
                    hideDeleter();
                }
                break;
            case R.id.cb_all:
                //编辑页面的全选
                Toast.makeText(cartBuyActivity.this, "编辑页面的全选", Toast.LENGTH_SHORT).show();
                boolean checked = cbAll.isChecked();
                adapter.checkAll_none(checked);
                adapter.showTotalPrice();
                break;
            case R.id.cb_all_deleter:
                //删除页面的全选
                Toast.makeText(cartBuyActivity.this, "删除页面的全选", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_Settlement_deleter:
                //删除页面的结算
                Toast.makeText(cartBuyActivity.this, "删除页面的结算", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cart_total:
                //编辑页面的总价
                Toast.makeText(cartBuyActivity.this, "编辑页面的总价", Toast.LENGTH_SHORT).show();
                adapter.showTotalPrice();
                break;
            case R.id.btn_Settlement:
                //编辑页面的结算
                Toast.makeText(cartBuyActivity.this, "编辑页面的结算", Toast.LENGTH_SHORT).show();
                pay();
                break;
            case R.id.cart_total_deleter:
                //删除页面的总价
                Toast.makeText(cartBuyActivity.this, "删除页面的总价", Toast.LENGTH_SHORT).show();
                adapter.showTotalPrice();
                break;
        }
    }

    private void hideDeleter() {
        llCheckAll.setVisibility(View.VISIBLE);
        llDeleter.setVisibility(View.GONE);
        cartBuy.setText("编辑");
        cartBuy.setTag(ACTION_EDIT);
    }

    private void showDeleter() {
        llDeleter.setVisibility(View.VISIBLE);
        llCheckAll.setVisibility(View.GONE);
        cartBuy.setText("完成");
        cartBuy.setTag(ACTION_COMPLETE);
    }

    // 商户PID
    public static final String PARTNER = "2088911876712776";
    // 商户收款账号
    public static final String SELLER = "chenlei@atguigu.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKrgBHYgH5jrbwP4\n" +
            "Y4SNu7elCjhzoyioK/eQN7sAA3oiiI42PGUd/3M2c/jiKcvaQlk6h6s0/vXZcV0t\n" +
            "RwgRWvLiDzNtxTHoXRlaKSCvUpBZFu7jnlEQmO9/WNr4gnDJjPzHffgiWveAqQZr\n" +
            "IIOpCq1V223OTd7KzBOLDMUO1GRFAgMBAAECgYEAovWTq6gFGkxlim7HUfHtdT1j\n" +
            "WuUFVE/V5npWe/OOXVszyhR1UqXiKdljjpb571yObBRCsIeRI5uk3ozAsjQC/BHG\n" +
            "OCiL11NsQj/fX4QlPWhK32yWwRZZHdHLsPpFGisgrSUeo7hhSHOVGXXQqtEeIo5Y\n" +
            "FOge9Vf5844jgZhZq2ECQQDff5JDeP6HMsqLj1XU1oW4rYrxj11tpKtCqUZmSqaE\n" +
            "rJUGNjXp8bKsPSjGG+UqSvTfrhqXLJNrFyeJIbMf0codAkEAw7lhMzGr/txGAA+K\n" +
            "bScbH3M1o5zCJRsSHIKcrVt1ti3QMJvTBIg23xKXM/xx9PD++J8rp+7Bw1S38In4\n" +
            "BO0qSQJAcpP2Kf0Xj7wsNkvvVsP7nefqOVikLxeibPRXEQ3oPFA1vg+AqESusrpP\n" +
            "8vBOFdaDn0CkhSfnE5m1PQo3yHJGyQJAKFvvqSjS+KjfXl/WAZAQzblqFCTc+93L\n" +
            "LEnamPzFFx33Ui6vor2b4v/oYfXqYcHYMdhQnj5jgi3UCCvLEuj80QJBAIFJLHE+\n" +
            "KLc3FbqVQj5P3eUwNX0SW3YgjDBMYYgUPbkxFTVQifaS7Nuwhk/Ut6195sYEU25h\n" +
            "hmR5dSvGhf9NToQ=";
    //	// 支付宝公钥
//	public static final String RSA_PUBLIC = "";
    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(context, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(context, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    /**
     * call alipay sdk pay. 调用SDK支付
     */
    public void pay() {
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(context).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                        }
                    }).show();
            return;
        }
        //订单信息
        String orderInfo = getOrderInfo("尚硅谷商品", "尚硅谷商品详细描述", adapter.getshowTotalPrice());

        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask((Activity) context);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * create the order info. 创建订单信息
     */
    private String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }
}
