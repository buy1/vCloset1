package com.example.baiwen.vcloset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;


import static android.R.attr.maxHeight;
import static android.R.attr.maxWidth;

public class ImageCropping extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CropView(ImageCropping.this));

    }
}
