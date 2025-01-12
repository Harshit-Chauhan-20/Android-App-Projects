package com.example.myfirstapphc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public static final String NaMe = "com.example.myfirstapphc.NaMe.1234";
    public static final String ot = "otpverification8055";

    EditText number;
    String t,c;
    CountryCodePicker ccp;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openactivity(View v) {
        number = findViewById(R.id.number);
        ccp=(CountryCodePicker) findViewById((R.id.ccp));
        ccp.registerCarrierNumberEditText(number);
        t = number.getText().toString();
//        c=(t+ccp.toString()).toString();
        if (t.trim().length() == 10 ) {

//            PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                    c, 60, TimeUnit.SECONDS, MainActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                        @Override
//                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
//                        {
//
//
//                        }
//
//                        @Override
//                        public void onVerificationFailed(@NonNull FirebaseException e) {
//                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onCodeSent(@NonNull String otpverify, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                            Intent i = new Intent(MainActivity.this, v1.class);
//                            //            number = findViewById(R.id.number);
//                            //            String t = number.getText().toString();
//                            i.putExtra("otpverify12344321",otpverify);
//                            i.putExtra("numberph",c);
//                            startActivity(i);
//                        }
//                    }
//           );
//        }}}
            Intent intent = new Intent(MainActivity.this, DashB.class);

            intent.putExtra("Ve",ccp.getFullNumberWithPlus());

            startActivity(intent);
        }

    }
}