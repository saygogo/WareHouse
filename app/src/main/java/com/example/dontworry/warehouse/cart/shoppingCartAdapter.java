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


    public shoppingCartAdapter(Context context, List<cartBeanInfo> cartBeanInfos) {
        this.context = context;
        this.datas = cartBeanInfos;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.item_cart,parent,false);
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
            ButterKnife.bind(this,itemView);
        }
    }
}
