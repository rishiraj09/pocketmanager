package com.abhipatil.pocketmanagerfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
    DatabaseHelper2 myDb2;
    EditText et1,et2,et3,et4,et5;
    Button b1,b2,bupdate,bdelete,bcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        myDb2 = new DatabaseHelper2(this);

        et1= (EditText)findViewById(R.id.editText6);
        et2= (EditText)findViewById(R.id.editText7);
        et3= (EditText)findViewById(R.id.editText8);
        et4= (EditText)findViewById(R.id.editText9);
        et5 = (EditText)findViewById( R.id.editText11 );
        b1 = (Button)findViewById(R.id.button5);
        //b2 = (Button)findViewById(R.id.button6);
        bupdate = (Button)findViewById(R.id.button7);
        bdelete = (Button)findViewById(R.id.button8);
        bcon = (Button)findViewById(R.id.button9);
        AddData();
        //viewAll();
        UpdateData();
        DeleteData();

        bcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent disIntent = new Intent(Main4Activity.this, Main7Activity.class);
                Main4Activity.this.startActivity(disIntent);

            }

        });
    }
    public void DeleteData() {
        bdelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb2.deleteData(et4.getText().toString());
                        et4.setText( "" );
                        if(deletedRows > 0)
                            Toast.makeText(Main4Activity.this,"Product Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Main4Activity.this,"Product not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void UpdateData() {
        bupdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb2.updateData(et4.getText().toString(),
                                et1.getText().toString(),
                                et2.getText().toString());
                        et4.setText( "" );
                        et1.setText( "" );
                        et2.setText( "" );
                        if(isUpdate == true)
                            Toast.makeText(Main4Activity.this,"Data Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Main4Activity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddData(){
        b1.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        boolean isInserted = myDb2.insertData(et1.getText().toString(),
                                et2.getText().toString(),
                                et3.getText().toString());
                        et1.setText( "" );
                        et2.setText( "" );
                        et3.setText( "" );
                        if(isInserted == true)
                            Toast.makeText(Main4Activity.this,"Product inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Main4Activity.this,"Product not inserted",Toast.LENGTH_LONG).show();

                    }
                }
        );

    }
   /*public void viewAll(){
        b2.setOnClickListener(
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
    }*/
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
