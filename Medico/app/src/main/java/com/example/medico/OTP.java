package com.example.myfirstapphc;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import com.google.firebase.FirebaseException;

import androidx.appcompat.app.AppCompatActivity;

public class OTP extends AppCompatActivity {
    String otpget;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        TextView t= findViewById(R.id.t);
        Intent i = getIntent();
        String number = i.getStringExtra(MainActivity.NaMe);
        t.setText("An OTP is sent to your number: +91"+number+".");

    }

}