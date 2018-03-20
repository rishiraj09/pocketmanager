package com.abhipatil.pocketmanagerfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2activity extends AppCompatActivity {
    Button b1,b2,b3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2activity);
        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        //b4 = (Button)findViewById(R.id.button4);
        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent( Main2activity.this,Main6Activity.class );
                Main2activity.this.startActivity( search );
            }
        } );

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent disIntent = new Intent(Main2activity.this, Main3Activity.class);
                Main2activity.this.startActivity(disIntent);

            }

        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent disIntent = new Intent(Main2activity.this, Main4Activity.class);
                Main2activity.this.startActivity(disIntent);

            }

        });



    }
}
