package com.example.myfirstapphc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class main2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

//        Button b1= findViewById(R.id.imageButton3);
//        Button b2=findViewById(R.id.imageButton4);
//        Button b3=findViewById(R.id.imageButton6);
//        Button b4=findViewById(R.id.imageButton2);
//        requestQueue = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://127.0.0.1:5000/predict", null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject Response) {
//                try {
//                    Log.d("myapp","The Response is");
//                    JSONObject n= new JSONObject();
//                }
//                catch (JSONException e)
//                {
//
//                    Toast.makeText(MainActivity2.this,"Error throw", Toast.LENGTH_SHORT).show();
//                }
//                }
//            }, new Response.ErrorListener(){
//                @Override
//                public void onErrorResponse(VolleyError error){
//                    Log.d("myapp","Something Went Wrong");
//                }
//            });
//        requestQueue.add(jsonObjectRequest);
    }
}