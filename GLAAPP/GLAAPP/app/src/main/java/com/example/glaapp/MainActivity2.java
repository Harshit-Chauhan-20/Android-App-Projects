package com.example.glaapp;
//package com.example.glaapp;
//import org.json.JSONException;
//import org.json.*;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.widget.TextView;
//import androidx.appcompat.app.AppCompatActivity;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class MainActivity2 extends AppCompatActivity {
//
////    private TextView textView, tempview;
////    private Handler handler = new Handler(Looper.getMainLooper());
////    private String[] temp = {"29", "30", "31", "32","32","31","31","33","35","34","36","37"};
////    private String[] texts = {"45","47","49","50","53", "55", "56", "57","56","70","65","69"};
////    private int currentIndex = 0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//
////
////        textView = findViewById(R.id.textView3);
////        tempview = findViewById(R.id.textView4);
////        // Start the text change process
////        changeTextOneByOne();
////
////    }
////
////    private void changeTextOneByOne() {
////        if (currentIndex < texts.length) {
////            String newText = texts[currentIndex];
////            textView.setText(newText);
////            currentIndex++;
////
////            // Delay before changing to the next text (adjust the delay as needed)
////            handler.postDelayed(new Runnable() {
////                @Override
////                public void run() {
////                    changeTextOneByOne();
////                }
////            }, 1000); // Change text every 1 second (1000 milliseconds)
////        }
////
////
////      if (currentIndex < temp.length) {
////          String newText = texts[currentIndex];
////        tempview.setText(newText);
////                currentIndex++;
////
////            // Delay before changing to the next text (adjust the delay as needed)
////            handler.postDelayed(new Runnable() {
////                @Override
////                public void run() {
////                    changeTextOneByOne();
////                }
////            }, 5000); // Change text every 1 second (1000 milliseconds)
////        }
////    }
////}
//        Button b1= findViewById(R.id.textView3);
//        Button b2=findViewById(R.id.textView4);
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
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
//                    }
//                }
//            }, new Response.ErrorListener(){
//                @Override
//                public void onErrorResponse(VolleyError error){
//                    Log.d("myapp","Something Went Wrong");
//                }
//            });
//        requestQueue.add(jsonObjectRequest);}}
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class MainActivity2 extends AppCompatActivity {
//
//    private TextView textView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//        firebaseDatabase
//    }
//}
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.textView3);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://gla-app-380323-default-rtdb.firebaseio.com/"; // Replace with your server URL

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parse the JSON response here and update the UI as needed
                            String result = response.getString("key"); // Replace "key" with the actual key in your response
                            textView.setText(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Handle JSON parsing errors here
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("myapp", "Error: " + error.getMessage());
                        if (error.networkResponse != null) {
                            int statusCode = error.networkResponse.statusCode;
                            Log.d("myapp", "Status Code: " + statusCode);
                            byte[] data = error.networkResponse.data;
                            if (data != null) {
                                String responseString = new String(data);
                                Log.d("myapp", "Response: " + responseString);

                                // Check content type
                                String contentType = error.networkResponse.headers.get("Content-Type");
                                Log.d("myapp", "Content-Type: " + contentType);

                                // Handle the response based on content type
                                if (contentType != null && contentType.startsWith("application/json")) {
                                    // It's JSON content but couldn't be parsed
                                    // Handle JSON parsing error here
                                } else {
                                    // It's not JSON content, handle it accordingly (e.g., show an error message)
                                    Toast.makeText(MainActivity2.this, "Error: Server returned non-JSON content", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            // Handle other network errors here
                            Toast.makeText(MainActivity2.this, "Error: Network error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
}
