package com.abhipatil.pocketmanagerfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText e1, e2, e3, e4, e5;
    Button data_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main5 );
        e1 = (EditText) findViewById( R.id.editText10 );
        e2 = (EditText) findViewById( R.id.editText12 );
        e3 = (EditText) findViewById( R.id.editText13 );
        e4 = (EditText) findViewById( R.id.editText14 );
        e5 = (EditText) findViewById( R.id.editText15 );
        data_update = (Button) findViewById( R.id.button11 );
        myDb = new DatabaseHelper( this );
        UpdateData();
    }


    public void UpdateData() {
        data_update.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = myDb.updateData(e5.getText().toString(),
                        e1.getText().toString(),
                        e2.getText().toString(),e3.getText().toString(),e4.getText().toString());
                if(isUpdate == true) {
                    Toast.makeText( Main5Activity.this, "Data Updated", Toast.LENGTH_LONG ).show();
                    Intent back = new Intent( Main5Activity.this, Main3Activity.class );
                    Main5Activity.this.startActivity( back );
                }

                else {
                    Toast.makeText( Main5Activity.this, "Data not Updated", Toast.LENGTH_LONG ).show();
                }

            }
        } );

    }
}
