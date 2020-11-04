package com.omaressam.mydreamland.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.omaressam.mydreamland.R;

public class SplashScreen extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        imageView = findViewById(R.id.imageView4);


        int splash_time = 2000;
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this,MainActivity.class);
            startActivity(intent);
            finish();
        }, splash_time);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.animy);
        imageView.startAnimation(animation);


    }
}