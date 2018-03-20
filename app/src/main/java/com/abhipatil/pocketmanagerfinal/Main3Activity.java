package com.abhipatil.pocketmanagerfinal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText et1,et2,et3,et4,et5;
    Button b1,b2,bupdate,bdelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        myDb = new DatabaseHelper(this);

        et1= (EditText)findViewById(R.id.editText3);
        et2= (EditText)findViewById(R.id.editText2);
        et3= (EditText)findViewById(R.id.editText4);
        et4= (EditText)findViewById(R.id.editText5);
        et5= (EditText)findViewById(R.id.editText);
        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button2);
        bupdate = (Button)findViewById(R.id.button3);
        bdelete = (Button)findViewById(R.id.button4);
        AddData();
        viewAll();
        //UpdateData();
        DeleteData();

        bupdate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent update = new Intent( Main3Activity.this,Main5Activity.class );
                Main3Activity.this.startActivity( update );
            }
        } );
    }
    public void DeleteData() {
        bdelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(et5.getText().toString());
                        et5.setText( "" );
                        if(deletedRows > 0)
                            Toast.makeText(Main3Activity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Main3Activity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    /*public void UpdateData() {
        bupdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(et5.getText().toString(),
                                et1.getText().toString(),
                                et2.getText().toString(),et3.getText().toString(),et4.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(Main3Activity.this,"Data Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Main3Activity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }*/

    public void AddData(){
        b1.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        boolean isInserted = myDb.insertData(et1.getText().toString(),
                                et2.getText().toString(),
                                et3.getText().toString(),
                                et4.getText().toString());

                        et1.setText( "" );
                        et2.setText( "" );
                        et3.setText( "" );
                        et4.setText( "" );
                        if(isInserted == true)
                            Toast.makeText(Main3Activity.this,"Data inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Main3Activity.this,"Data not inserted",Toast.LENGTH_LONG).show();

                    }
                }
        );

    }
    public void viewAll(){
        b2.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Cursor res= myDb.getAllData();
                        if(res.getCount()==0){
                            //show message
                            showMessage("Error","No data found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Id: " +res.getString(0)+"\n");
                            buffer.append("Product: " +res.getString(1)+"\n");
                            buffer.append("Category: " +res.getString(2)+"\n");
                            buffer.append("Price: " +res.getString(3)+"\n");
                            buffer.append("Stock: " +res.getString(4)+"\n\n");
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