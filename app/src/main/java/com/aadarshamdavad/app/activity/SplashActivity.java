package com.aadarshamdavad.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.aadarshamdavad.app.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1,ScaleAnimation.RELATIVE_TO_SELF,.5f);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setInterpolator(new OvershootInterpolator());

//        final Animation animation = AnimationUtils.loadAnimation(this,R.anim.fadein);
        ImageView ivSplash = (ImageView) findViewById(R.id.ivSplash);
//        ivSplash.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent i = new Intent(SplashActivity.this, GalleryViewActivity.class);
                Intent i = new Intent(SplashActivity.this, NavigationMainActivity.class);
                startActivity(i);
                finish();
            }
        }, 2500);
    }

}
