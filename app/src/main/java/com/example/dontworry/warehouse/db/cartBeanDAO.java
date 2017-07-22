package com.example.dontworry.warehouse.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Don't worry on 2017/7/22.
 */

public class cartBeanDAO {
    private DBHelper dbHelper;

    public cartBeanDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    // 添加
    public void addcart(cartBeanInfo cartBeanInfo){
        if (cartBeanInfo == null){
            return;
        }
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactTable.COL_GOODID,cartBeanInfo.getGoods_id());
        contentValues.put(ContactTable.GOODS_IMAGE,cartBeanInfo.getGoods_image());
        contentValues.put(ContactTable.GOODS_NAME,cartBeanInfo.getGoods_name());
        contentValues.put(ContactTable.ATTR_NAME,cartBeanInfo.getAttr_name());
        contentValues.put(ContactTable.TYPE_NAME,cartBeanInfo.getType_name());
        contentValues.put(ContactTable.PRICE,cartBeanInfo.getPrice());
        contentValues.put(ContactTable.NUMBER,cartBeanInfo.getNumber());

        database.replace(ContactTable.TABLE_NAME,null,contentValues);
    }

    // 获取所有
    public List<cartBeanInfo> getcart(){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String sql = "select * from "+ContactTable.TABLE_NAME;
        Cursor cursor = database.rawQuery(sql, null);
        List<cartBeanInfo> info = new ArrayList<>();
        while (cursor.moveToNext()){
            cartBeanInfo infocart = new cartBeanInfo();
            infocart.setPrice(cursor.getString(cursor.getColumnIndex(ContactTable.PRICE)));
            infocart.setGoods_name(cursor.getString(cursor.getColumnIndex(ContactTable.GOODS_NAME)));
            infocart.setGoods_id(cursor.getString(cursor.getColumnIndex(ContactTable.COL_GOODID)));
            infocart.setGoods_image(cursor.getString(cursor.getColumnIndex(ContactTable.GOODS_IMAGE)));
            infocart.setAttr_name(cursor.getString(cursor.getColumnIndex(ContactTable.ATTR_NAME)));
            infocart.setType_name(cursor.getString(cursor.getColumnIndex(ContactTable.TYPE_NAME)));
            infocart.setNumber(cursor.getString(cursor.getColumnIndex(ContactTable.NUMBER)));
            info.add(infocart);
        }
        cursor.close();
        return info;
    }


//
}
