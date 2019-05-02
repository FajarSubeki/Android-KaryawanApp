package com.example.karyawanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int time = 3000;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playsound();
        initUi();

        if(Build.VERSION.SDK_INT >= 16){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, Slider.class));
                finish();
            }
        }, time);
    }

    public void initUi(){
        TextView logofont = findViewById(R.id.logofont);
        Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
        logofont.setTypeface(custom_fonts);
    }

    private void playsound(){
        try{
            if(player.isPlaying()){
                player.stop();
                player.release();
            }
        }catch (Exception e){

        }
        player = MediaPlayer.create(this, R.raw.openmusic);
        player.setLooping(false);
        player.start();
    }
}
