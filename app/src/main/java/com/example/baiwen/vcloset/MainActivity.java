package com.example.baiwen.vcloset;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.provider.MediaStore;
import android.widget.Button;
import android.graphics.Bitmap;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public Button button1;
    public Button button2;

    public void init(){
        button1 = (Button)findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent show = new Intent(MainActivity.this, GalleryOfClothing.class);
                startActivity(show);
            }
        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choice = new Intent(MainActivity.this, OutfitChoice.class);
                startActivity(choice);
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        final Intent pickPhoto= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

       FloatingActionButton takePhotoButton = (FloatingActionButton) findViewById(R.id.floatingActionButton6);
        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent TakenPhoto = new Intent(MainActivity.this, PhotoViewer.class);
                startActivity(TakenPhoto);

                startActivity(takePicture);
            }
        });

        FloatingActionButton importPhotoButton = (FloatingActionButton) findViewById(R.id.floatingActionButton5);
        importPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GalleryPhoto = new Intent(MainActivity.this, PhotoViewer.class);
                startActivity(GalleryPhoto);

                startActivity(pickPhoto);
            }
        });

        init();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
