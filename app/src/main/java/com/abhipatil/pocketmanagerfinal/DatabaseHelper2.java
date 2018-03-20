package com.abhipatil.pocketmanagerfinal;

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
    public static final String COL_4 = "PRICE";

    public DatabaseHelper2(Context context) {
        super(context,DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,PRODUCT TEXT,QUANTITY TEXT,PRICE INTEGER)");
      /*  public static final String DATABASE_CREATE_TEAM = "create table "
                + TABLE_TEAM + "(" + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_NAME + " string, "
                + COLUMN_MASCOT + " string, "
                + COLUMN_CITY + " string);";*/

        //db.execSQL( "create table " + TABLE_NAME + "(" + COL_1 + " integer primary key autoincrement, " + COL_2 + "TEXT, " + COL_3 + "TEXT, " + COL_4 + "PRICE);" );
        //db.execSQL( "ALTER TABLE sales_table ADD COLUMN date TEXT " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate( db );

       /* db.execSQL("create table " + NEW_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,PRODUCT TEXT,QUANTITY TEXT,PRICE INTEGER)");
        // Create an temporaty table that can store data of older version

        db.execSQL("INSERT INTO " + NEW_TABLE + " SELECT " +  COL_2 + ", "
                +  COL_3 + ", " +  COL_4 +  "FROM " + TABLE_NAME);

        db.execSQL("DROP TABLE "+ TABLE_NAME);

        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,PRODUCT TEXT,QUANTITY TEXT,PRICE INTEGER,DATE TEXT)");

        db.execSQL("INSERT INTO " + NEW_TABLE + " SELECT " +  COL_2 + ", "
                +  COL_3 + ", " +  COL_4 +  "FROM " + TABLE_NAME);

        db.execSQL("DROP TABLE " + NEW_TABLE);*/
        // if (i > i1) {
        //db.execSQL( "ALTER TABLE sales_table ADD COLUMN date TEXT " );
        // db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COL_5 + " text");

        //}
    }

    public boolean insertData(String product, String quantity, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Contentvalues  = new ContentValues();
        Contentvalues.put(COL_2,product);
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
    public boolean updateData(String id, String product, String price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Contentvalues  = new ContentValues();
        Contentvalues.put(COL_1,id);
        Contentvalues.put(COL_2,product);
        Contentvalues.put(COL_4,price);
        db.update(TABLE_NAME, Contentvalues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

   /* public Cursor show_date_results(String order_date){
        if(order_date.isEmpty()){
            return null;
        }
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE date = \""+order_date +"\"";
        Cursor re     = db.rawQuery(query,null);
        String[] data      = null;
        return re;
    }*/

}