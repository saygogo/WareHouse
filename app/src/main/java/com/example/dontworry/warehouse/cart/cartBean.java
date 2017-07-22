package com.example.dontworry.warehouse.cart;

import java.io.Serializable;

/**
 * Created by Don't worry on 2017/7/21.
 */

public class cartBean implements Serializable {
    /**
     * "goods_id": "258055",
     * "goods_image": "http://imgs-qn.iliangcang.com/ware/goods/big/2/258/258055.jpg?t=1497525912",
     * "goods_name": "【独家首发】乐可丽舒漱口水 | 乳酸菌强效抗菌 清爽低刺激【300ml】",
     * "goods_desc": "1",
     * "price": "128.00",
     * "sku_info": [
     * {
     * "type_id": "7",
     * "type_name": "数量",
     * "isColor": "0",
     * "attrList": [
     * {
     * "attr_id": "24",
     * "attr_name": "一瓶",
     * "img_path": ""
     * }
     */
    private String goods_id;
    private String goods_image;
    private String goods_name;
    private String attr_name;
    private String type_name;
    private String number;
    private String price;
    private boolean isCaeked = true;

    public cartBean() {
    }

    public cartBean(String goods_id, String goods_image, String goods_name, String attr_name, String type_name, String number, String price, boolean isCaeked) {
        this.goods_id = goods_id;
        this.goods_image = goods_image;
        this.goods_name = goods_name;
        this.attr_name = attr_name;
        this.type_name = type_name;
        this.number = number;
        this.price = price;
        this.isCaeked = isCaeked;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getAttr_name() {
        return attr_name;
    }

    public void setAttr_name(String attr_name) {
        this.attr_name = attr_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isCaeked() {
        return isCaeked;
    }

    public void setCaeked(boolean caeked) {
        isCaeked = caeked;
    }

    @Override
    public String toString() {
        return "cartBean{" +
                "goods_id='" + goods_id + '\'' +
                ", goods_image='" + goods_image + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", attr_name='" + attr_name + '\'' +
                ", type_name='" + type_name + '\'' +
                ", number='" + number + '\'' +
                ", price='" + price + '\'' +
                ", isCaeked=" + isCaeked +
                '}';
    }
}
