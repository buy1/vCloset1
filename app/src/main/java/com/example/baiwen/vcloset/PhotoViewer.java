package com.example.baiwen.vcloset;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class PhotoViewer extends AppCompatActivity {

    public Button button1;

    public void init(){
        button1 = (Button)findViewById(R.id.buttonC);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent categorize = new Intent(PhotoViewer.this, MainActivity.class);
                startActivity(categorize);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_photo_viewer);
        Intent intent = getIntent();
        Bitmap bitmap =  intent.getParcelableExtra("bitmapimage");
        ImageView imageview = (ImageView) this
                .findViewById(R.id.imageView1);
        imageview.setImageBitmap(bitmap);
        init();
    }
}
