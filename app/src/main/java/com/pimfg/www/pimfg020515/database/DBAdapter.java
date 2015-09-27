package com.pimfg.www.pimfg020515.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Kevin on 1/22/2015.
 */
public class DBAdapter {
    private DBHelper helper;

    public DBAdapter(Context context) {
        helper = new DBHelper(context);
    }

    public String getConnectorNo(String conn){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Product.TABLE_CONNECTOR + " WHERE " + Product.connTYPE + "= '" + conn + "' LIMIT 1", null);
        int index = cursor.getColumnIndex(Product.connHEADNO);
        cursor.moveToFirst();
        String connNo = cursor.getString(index);
        db.close();
        cursor.close();

        return connNo;
    }


    public String getCableNo(String cab){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Product.TABLE_CABLE + " WHERE " + Product.cabNAME + "= '" + cab + "' LIMIT 1", null);
        int index = cursor.getColumnIndex(Product.cabNO);
        cursor.moveToFirst();
        String cabNo = cursor.getString(index);
        db.close();
        cursor.close();

        return cabNo;
    }

    public Double getConnectorPrice(String conn){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Product.TABLE_CONNECTOR + " WHERE " + Product.connTYPE + "= '" + conn + "' LIMIT 1", null);
        int index = cursor.getColumnIndex(Product.connPRICE);
        cursor.moveToFirst();
        double connPrice = cursor.getDouble(index);
        db.close();
        cursor.close();

        return connPrice;
    }

    public Double getCablePrice(String cab){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Product.TABLE_CABLE + " WHERE " + Product.cabNAME + "= '" + cab + "' LIMIT 1", null);
        int index = cursor.getColumnIndex(Product.cabPRICE);
        cursor.moveToFirst();
        double cabPrice = cursor.getDouble(index);
        db.close();
        cursor.close();

        return cabPrice;
    }



    public ArrayList getConnectorType() {
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList alType = new ArrayList();
        Cursor cursor = db.rawQuery("SELECT DISTINCT "+Product.connTYPE +" FROM " + Product.TABLE_CONNECTOR + " ORDER BY " + Product.connTYPE, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int index = cursor.getColumnIndex(Product.connTYPE);
            String type = cursor.getString(index);
            alType.add(type);
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return alType;
    }

    public ArrayList getCableType() {
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList alType2 = new ArrayList();
        Cursor cursor2 = db.rawQuery("SELECT DISTINCT "+Product.cabNAME +" FROM " + Product.TABLE_CABLE + " ORDER BY " + Product.cabNAME, null);
        cursor2.moveToFirst();
        while (!cursor2.isAfterLast()){
            int index = cursor2.getColumnIndex(Product.cabNAME);
            String type = cursor2.getString(index);
            alType2.add(type);
            cursor2.moveToNext();
        }
        db.close();
        cursor2.close();
        return alType2;
    }

}
