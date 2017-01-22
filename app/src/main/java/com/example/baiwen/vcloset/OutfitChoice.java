package com.example.baiwen.vcloset;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;


public class OutfitChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_outfit_choice);

        RelativeLayout rLayout = (RelativeLayout) findViewById (R.id.fittingroom);
        Resources res = getResources(); //resource handle
        Drawable drawable = res.getDrawable(R.drawable.pk); //new Image that was added to the res folder

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
