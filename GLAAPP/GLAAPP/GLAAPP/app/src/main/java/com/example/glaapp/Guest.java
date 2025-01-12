package com.example.glaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.glaapp.databinding.ActivityMapsBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Guest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        Button b3= findViewById(R.id.button3);
        EditText username= findViewById(R.id.editTextTextPersonName2);
        EditText password = findViewById(R.id.editTextPhone);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Guest.this, MainActivity2.class);
                startActivity(intent);
                HashMap<String, Object> m = new HashMap<>();
                m.put("username",username.getText().toString());
                m.put("Phone Number",password.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("Name").push().setValue(m);
                finish();
            }
        });
    }
}