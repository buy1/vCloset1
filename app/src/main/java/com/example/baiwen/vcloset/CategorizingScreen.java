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
    public Button button3;
    public Button button4;

    public void init(){
        button1 = (Button)findViewById(R.id.top);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirect = new Intent(CategorizingScreen.this, MainActivity.class);
                startActivity(redirect);
            }
        });

        button2 = (Button)findViewById(R.id.accessories);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirect = new Intent(CategorizingScreen.this, MainActivity.class);
                startActivity(redirect);
            }
        });

        button3 = (Button)findViewById(R.id.bottom);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirect = new Intent(CategorizingScreen.this, MainActivity.class);
                startActivity(redirect);
            }
        });

        button4 = (Button)findViewById(R.id.shoes);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirect = new Intent(CategorizingScreen.this, MainActivity.class);
                startActivity(redirect);
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
