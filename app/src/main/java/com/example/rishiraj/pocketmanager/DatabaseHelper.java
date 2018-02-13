package com.example.rishiraj.pocketmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "shop.db";
    public static final String TABLE_NAME = "data_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "PRODUCT";
    public static final String COL_3 = "CATEGORY";
    public static final String COL_4 = "PRICE";
    public static final String COL_5 = "STOCK";
    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,PRODUCT TEXT,CATEGORY TEXT,PRICE INTEGER,STOCK INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String product, String category, String price, String stock) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Contentvalues  = new ContentValues();
        Contentvalues.put(COL_2,product);
        Contentvalues.put(COL_3,category);
        Contentvalues.put(COL_4,price);
        Contentvalues.put(COL_5,stock);
        long result = db.insert(TABLE_NAME, null, Contentvalues);
        if(result == -1)
            return false;
        else
            return true;


    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public boolean updateData(String id, String product, String category, String price, String stock)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Contentvalues  = new ContentValues();
        Contentvalues.put(COL_1,id);
        Contentvalues.put(COL_2,product);
        Contentvalues.put(COL_3,category);
        Contentvalues.put(COL_4,price);
        Contentvalues.put(COL_5,stock);
        db.update(TABLE_NAME, Contentvalues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

}
