package com.example.baiwen.vcloset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PhotoViewer extends AppCompatActivity {

    public Button button;

    public void init(){
        button = (Button)findViewById(R.id.buttonC);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent categorize = new Intent(PhotoViewer.this, CategorizingScreen.class);
                startActivity(categorize);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);
        init();
    }
}
