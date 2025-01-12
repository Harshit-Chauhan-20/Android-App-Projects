package com.example.myfirstapphc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DashB extends AppCompatActivity {



    TextView dataTextView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_b);
        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Reference to the Firebase Realtime Database node you want to retrieve data from
        databaseReference = database.getReference("https://console.firebase.google.com/u/0/project/my-first-app-hc/database/my-first-app-hc-default-rtdb/data/~2F");

        dataTextView = findViewById(R.id.idTVRetrieveData);

        // Add a ValueEventListener to listen for data changes
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called when data changes in the database
                // You can access the data from dataSnapshot
                String value = dataSnapshot.getValue(String.class); // Change String.class to your data type
                dataTextView.setText(value); // Update the TextView with the retrieved data
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors, if any
            }
        });
    }
}
