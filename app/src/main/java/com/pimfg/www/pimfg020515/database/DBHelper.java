package com.pimfg.www.pimfg020515.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kevin on 1/22/2015.
 */
    //CREATES TABLE
public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    private static final int DATABASE_VERSION = 7;

    // Database Name
    private static final String DATABASE_NAME = "crap.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

  

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + Product.TABLE_CABLE
                + " (" +
                Product.cabID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Product.cabNO + " VARCHAR(3), " +
                Product.cabNAME + " VARCHAR(255), " +
                Product.cabPRICE + " MONEY )";
        
        db.execSQL(CREATE_TABLE);
        System.out.println("Cable Table Created");

        String CREATE_TABLE2 = "CREATE TABLE " + Product.TABLE_CONNECTOR
                + " (" +
                Product.connID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Product.connPartNo + " VARCHAR(20), " +
                Product.connTYPE + " VARCHAR(20), " +
                Product.connHEADNO + " VARCHAR(3), " +
                Product.connPRICE + " MONEY)";
        
        db.execSQL(CREATE_TABLE2);
        System.out.println("Conn Table Created");

        db.execSQL(Product.INSERT_CABLE);
        System.out.println("Cable Inserted");
        db.execSQL(Product.INSERT_CONN);
        System.out.println("Conn Inserted");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone
        db.execSQL("DROP TABLE IF EXISTS " + Product.TABLE_CABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Product.TABLE_CONNECTOR);
        // Create tables again
        onCreate(db);

    }
}


