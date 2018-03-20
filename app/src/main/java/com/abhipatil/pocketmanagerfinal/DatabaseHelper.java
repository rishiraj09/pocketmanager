package com.abhipatil.pocketmanagerfinal;

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

    /*public Cursor showsearchdata(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //SQLiteDatabase db = this.getReadableDatabase();
        //String selectquery = "SELECT * FROM" + TABLE_NAME + "WHERE" + COL_2 + "=?"  ;
        //Cursor resd = db.rawQuery("SELECT * FROM " + TABLE_NAME + "WHERE PRODUCT='"  + "='" + String.valueOf( "product" )+"'",null);
        // Cursor resd = db.rawQuery( " select product,price,stock from " +TABLE_NAME+ "where"  +COL_2 + "= ?",null );
        //Cursor resd = db.rawQuery( TABLE_NAME,new String[]{COL_2,COL_4,COL_5},COL_2 + "=?" ,new String[] {String.valueOf( COL_2 )},null,null,null,null);
        //Cursor resd = db.rawQuery( selectquery,null );
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE id=" + id;
        //  SQLiteDatabase db  = this.getReadableDatabase();
        Cursor resd      = db.rawQuery(selectQuery, null);
        String[] data      = null;
        return resd;
    }*/

    public Cursor showsearch(String cat) {
        if(cat.isEmpty()){
            return null;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        //SQLiteDatabase db = this.getReadableDatabase();
        //String selectquery = "SELECT * FROM" + TABLE_NAME + "WHERE" + COL_2 + "=?"  ;
        //Cursor resd = db.rawQuery("SELECT * FROM " + TABLE_NAME + "WHERE PRODUCT='"  + "='" + String.valueOf( "product" )+"'",null);
        // Cursor resd = db.rawQuery( " select product,price,stock from " +TABLE_NAME+ "where"  +COL_2 + "= ?",null );
        //Cursor resd = db.rawQuery( TABLE_NAME,new String[]{COL_2,COL_4,COL_5},COL_2 + "=?" ,new String[] {String.valueOf( COL_2 )},null,null,null,null);
        //Cursor resd = db.rawQuery( selectquery,null )
        // Cursor resd =db.query(TABLE_NAME,new String[] { COL_4,COL_5},COL_1 + "=?",new String[] {String.valueOf(COL_2)},null,null,null,null);
        //  Cursor resd=db.rawQuery( "SELECT * FROM data_table WHERE id="+product,new String[] { COL_4,COL_5} );
        //for the above command  the result is coming no data found (code line 83) ,if part is executing
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE category= \""+cat+"\"";
        //  SQLiteDatabase db  = this.getReadableDatabase();
        Cursor res     = db.rawQuery(selectQuery, null);
        String[] data      = null;
        return res;
    }




}
