package com.abhipatil.pocketmanagerfinal;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main7Activity extends AppCompatActivity {
    DatabaseHelper2 myDb2;
    Button b1;
    EditText e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main7 );
        myDb2 = new DatabaseHelper2(this);
        b1 = (Button)findViewById( R.id.button12 );
        //e1 = (EditText)findViewById( R.id.editText17 );
       //viewdatesearch();
        viewAll();
    }
    public void viewAll(){
        b1.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Cursor res= myDb2.getAllData();
                        if(res.getCount()==0){
                            //show message
                            showMessage("Error","No data found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Id: "+res.getString(0)+"\n");
                            buffer.append("Product: "+res.getString(1)+"\n");
                            buffer.append("Price: "+res.getString(2)+"\n");
                            buffer.append("Quantity: "+res.getString(3)+"\n\n");
                            //buffer.append("date: " +res.getString( 5 ));
                        }
                        //show all the data
                        showMessage("product list",buffer.toString());

                    }
                }
        );
    }
    /*public void viewdatesearch(){

        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor re= myDb2.show_date_results( e1.getText().toString());
                e1.setText( "" );
                if(re == null){
                    showMessage( "Warning","please enter the product name to search" );
                    return;
                }

                if(re.getCount()==0){
                    //show message
                    showMessage("Error","No such product exists !!!!");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (re.moveToNext()){
                    //buffer.append("Id:" +resd.getString(0)+"\n");
                    buffer.append("Product: " +re.getString(1)+"\n");
                    buffer.append("Category:" +re.getString(2)+"\n");
                    buffer.append("Price: " +re.getString(3)+"\n");
                    buffer.append("Stock: " +re.getString(4)+"\n\n");
                    //buffer.append("date: " +re.getString( 5 )+"\n\n");
                }
                //show all the data
                showMessage("Data",buffer.toString());
            }
        } );
    }*/

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
