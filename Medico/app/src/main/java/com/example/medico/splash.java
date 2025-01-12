package com.example.myfirstapphc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

public class splash extends AppCompatActivity {

    androidx.constraintlayout.widget.ConstraintLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        relativeLayout=findViewById(R.id.relativeLayout);
        relativeLayout.setBackgroundColor(Color.WHITE);
        getSupportActionBar().hide();
        Thread thread = new Thread()
        {
            public void run()
            {
               try {
                    sleep(3000);
               }
               catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally{
                        Intent intent  = new Intent(splash.this, MainActivity.class);
                   startActivity(intent);
                   finish();
               }
            }

        }; thread.start();
    }
}