package com.example.dontworry.warehouse.db;

/**
 * Created by Don't worry on 2017/7/22.
 */

public class ContactTable {


    public static final String TABLE_NAME = "cartBean";
    public static final String COL_GOODID = "goods_id";
    public static final String GOODS_IMAGE = "goods_image";
    public static final String ATTR_NAME = "attr_name";
    public static final String TYPE_NAME = "type_name";
    public static final String GOODS_NAME = "goods_name";
    public static final String PRICE = "price";
    public static final String NUMBER = "number";

    public static final String CREATE_TABLE = "create table "+TABLE_NAME+"("
            + COL_GOODID +" text primary key,"
            +GOODS_IMAGE + " text,"
            +ATTR_NAME + " text,"
            +TYPE_NAME + " text,"
            +PRICE+" text,"
            +GOODS_NAME+" text,"
            +NUMBER + " text)";
}
