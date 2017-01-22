package com.example.baiwen.vcloset;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class OutfitChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit_choice);

        ViewPager viewPagertop = (ViewPager) findViewById(R.id.pagertop);
        viewPagertop.setAdapter(new CustomPagerAdapter(this));

        ViewPager viewPagerBottom = (ViewPager) findViewById(R.id.pagerbottom);
        viewPagerBottom.setAdapter(new CustomPagerAdapter(this));

        FloatingActionButton confirmOk = (FloatingActionButton) findViewById(R.id.Ok);
        confirmOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Ok = new Intent(OutfitChoice.this, ImageCropping.class);
                startActivity(Ok);
            }
        });
    }


}
