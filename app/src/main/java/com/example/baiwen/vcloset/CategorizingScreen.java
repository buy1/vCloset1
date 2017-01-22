package com.example.baiwen.vcloset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class CategorizingScreen extends AppCompatActivity {

    public Button button1;
    public Button button2;

    public void init(){
        button1 = (Button)findViewById(R.id.top);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirect1 = new Intent(CategorizingScreen.this, MainActivity.class);
                redirect1.putExtra("topflag", 1);
                startActivity(redirect1);
            }
        });

        button2 = (Button)findViewById(R.id.bottom);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirect2 = new Intent(CategorizingScreen.this, MainActivity.class);
                redirect2.putExtra("botflag",1);
                startActivity(redirect2);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorizing_screen);
        init();
    }
}
