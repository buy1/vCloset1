package com.example.baiwen.vcloset;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;


public class OutfitChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit_choice);

        RelativeLayout rLayout = (RelativeLayout) findViewById (R.id.fittingroom);
        Resources res = getResources(); //resource handle
<<<<<<< HEAD
        Drawable drawable = res.getDrawable(R.drawable.sota1); //new Image that was added to the res folder
=======
        Drawable drawable = res.getDrawable(R.drawable.pk); //new Image that was added to the res folder

>>>>>>> bea0e60775b06f7c3992e7b72391d4ec0e0535fb
        rLayout.setBackground(drawable);

        ViewPager viewPagertop = (ViewPager) findViewById(R.id.pagertop);
        viewPagertop.setAdapter(new CustomPagerAdapter(this));

        ViewPager viewPagerBottom = (ViewPager) findViewById(R.id.pagerbottom);
        viewPagerBottom.setAdapter(new CustomPagerAdapterBottom(this));

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
