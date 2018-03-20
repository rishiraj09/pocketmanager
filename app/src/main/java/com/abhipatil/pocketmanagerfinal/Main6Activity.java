package com.abhipatil.pocketmanagerfinal;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class Main6Activity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText e1,e2;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main6 );
        //e1 = (EditText) findViewById( R.id.editText11);
        e2 = (EditText)findViewById( R.id.editText16 );
        myDb = new DatabaseHelper( this );
        //b1 = (Button)findViewById( R.id.button10 );
        b2 = (Button)findViewById( R.id.button6 );
        //viewsearchresult();
        viewsearch();
    }

    /*public void viewsearchresult(){

        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor resd= myDb.showsearchdata(e1.getText().toString());
                if(resd.getCount()==0){
                    //show message
                    showMessage("Error","No product found ");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (resd.moveToNext()){
                    buffer.append("Id:"+resd.getString(0)+"\n");
                    buffer.append("Prodcut:"+resd.getString(1)+"\n");
                    buffer.append("Category:"+resd.getString(2)+"\n");
                    buffer.append("Price:"+resd.getString(3)+"\n");
                    buffer.append("Stock:"+resd.getString(4)+"\n\n");
                }
                //show all the data
                showMessage("Data",buffer.toString());
            }
        } );
    }*/

    public void viewsearch(){

        b2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor resd= myDb.showsearch( e2.getText().toString());
                e2.setText( "" );
                if(resd == null){
                    showMessage( "Warning","please enter the product name to search" );
                    return;
                }

                if(resd.getCount()==0){
                    //show message
                    showMessage("Error","No such product exists !!!!");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (resd.moveToNext()){
                    //buffer.append("Id:" +resd.getString(0)+"\n");
              buffer.append("Product: " +resd.getString(1)+"\n");
                  buffer.append("Category:" +resd.getString(2)+"\n");
                   buffer.append("Price: " +resd.getString(3)+"\n");
                   buffer.append("Stock: " +resd.getString(4)+"\n\n");
                }
                //show all the data
                showMessage("Data",buffer.toString());
            }
        } );
    }



    public void showMessage(String title, String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}