package com.example.rishiraj.pocketmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper2 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "sales.db";
    public static final String TABLE_NAME = "sales_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "PRODUCT";
    public static final String COL_3 = "QUANTITY";
    public static final String COL_4 = "PRICE";

    public DatabaseHelper2(Context context) {
        super(context,DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,PRODUCT TEXT,QUANTITY TEXT,PRICE INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String product, String quantity, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Contentvalues  = new ContentValues();
        Contentvalues.put(COL_2,product);
        Contentvalues.put(COL_3,quantity);
        Contentvalues.put(COL_4,price);

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
    public boolean updateData(String id, String product, String quantity, String price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Contentvalues  = new ContentValues();
        Contentvalues.put(COL_1,id);
        Contentvalues.put(COL_2,product);
        Contentvalues.put(COL_3,quantity);
        Contentvalues.put(COL_4,price);
        db.update(TABLE_NAME, Contentvalues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

}
