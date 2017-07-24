package com.example.dontworry.warehouse.cart;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dontworry.warehouse.R;
import com.example.dontworry.warehouse.db.cartBeanInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Don't worry on 2017/7/21.
 */

public class shoppingCartAdapter extends RecyclerView.Adapter<shoppingCartAdapter.ViewHodler> {


    private final Context context;
    private final List<cartBeanInfo> datas;
    private final CheckBox cbAll;
    private final TextView cartTotal;
    private final CheckBox cbAllDeleter;
    private boolean ischeck;


    public shoppingCartAdapter(Context context, List<cartBeanInfo> cartBeanInfos, CheckBox cbAll, TextView cartTotal, CheckBox cbAllDeleter) {
        this.context = context;
        this.datas = cartBeanInfos;
        //编辑页面全选
        this.cbAll = cbAll;
        //总价格
        this.cartTotal = cartTotal;
        //删除页面全选
        this.cbAllDeleter = cbAllDeleter;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new ViewHodler(view);

    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        cartBeanInfo cartBeanInfo = datas.get(position);
        Glide.with(context)
                .load(cartBeanInfo.getGoods_image())
                .into(holder.goodsImage);
        holder.goodsName.setText(cartBeanInfo.getGoods_name());
        holder.attrName.setText(cartBeanInfo.getAttr_name());
        holder.typeName.setText(cartBeanInfo.getType_name());
        holder.price.setText(cartBeanInfo.getPrice());
        holder.number.setText(cartBeanInfo.getNumber());

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void showTotalPrice() {
        cartTotal.setText("总计：" + getshowTotalPrice());
    }

    public String getshowTotalPrice() {
        double rusult = 0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                cartBeanInfo cartBeanInfo = datas.get(i);
                if (cartBeanInfo.isCaeked()) {
                    rusult = rusult + Double.parseDouble(cartBeanInfo.getNumber())*Double.parseDouble(cartBeanInfo.getPrice());
                }
            }
        }
        return rusult+"";
    }

    //检验全选
    public void checkAll() {
        if(datas != null && datas.size() >0){
            int number = 0;

            for(int i = 0; i < datas.size(); i++) {
                cartBeanInfo cartBeanInfo = datas.get(i);
                //只要有一个不选中就设置非全选
                if(!cartBeanInfo.isCaeked()){
                    cbAll.setChecked(false);
                    cbAllDeleter.setChecked(false);
                }else{
                    number ++;
                }
            }

            if(number ==datas.size()){
                cbAll.setChecked(true);
                cbAllDeleter.setChecked(true);
            }


        }else {
            //没有数据
            cbAll.setChecked(false);
            cbAllDeleter.setChecked(false);
        }

    }

    //是否全部选中
    public void checkAll_none(boolean isCheck) {
        if(datas != null && datas.size() >0){
            int number = 0;

            for(int i = 0; i < datas.size(); i++) {
                cartBeanInfo cartBeanInfo = datas.get(i);
                //只要有一个不选中就设置非全选
                cartBeanInfo.setCaeked(isCheck);
                notifyItemChanged(i);
            }
        }else{
            cbAllDeleter.setChecked(false);
        }

    }

    public void deleteData() {

        if(datas != null && datas.size() > 0){

            for(int i = 0; i < datas.size(); i++) {

                cartBeanInfo cartBeanInfo = datas.get(i);
                if(cartBeanInfo.isCaeked()){
                    datas.remove(cartBeanInfo);
                    //同步到本地
                   // CartStorage.getInstance(context).deleteData(goodsBean);
                    notifyItemRemoved(i);
                    i--;
                }
            }
        }
    }

    class ViewHodler extends RecyclerView.ViewHolder {

        @BindView(R.id.cb_check)
        CheckBox cbCheck;
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.type_name)
        TextView typeName;
        @BindView(R.id.attr_name)
        TextView attrName;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.ll_text)
        LinearLayout llText;
        @BindView(R.id.number)
        TextView number;

        public ViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartBeanInfo cartBeanInfo = datas.get(getLayoutPosition());
                    //状态取反
                    cartBeanInfo.setCaeked(!cartBeanInfo.isCaeked());
                    notifyItemChanged(getLayoutPosition());
                    //设置价格
                    showTotalPrice();
                    //校验是否全选
                    checkAll();
                }
            });

        }
    }
}
