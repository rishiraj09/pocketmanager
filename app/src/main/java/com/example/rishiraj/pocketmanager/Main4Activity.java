package com.example.rishiraj.pocketmanager;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main4Activity extends AppCompatActivity {
    DatabaseHelper2 myDb2;
    Button b12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        b12 = (Button)findViewById(R.id.buttonview);
        viewAll();
    }
    public void viewAll(){

        b12.setOnClickListener(
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
                            buffer.append("Id:"+res.getString(0)+"\n");
                            buffer.append("Product:"+res.getString(1)+"\n");
                            buffer.append("Price:"+res.getString(2)+"\n");
                            buffer.append("Quantity:"+res.getString(3)+"\n\n");
                        }
                        //show all the data
                        showMessage("Data",buffer.toString());

                    }
                }
        );

    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    }

