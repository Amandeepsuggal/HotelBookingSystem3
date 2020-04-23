package com.example.hotelbookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class signupActivity extends AppCompatActivity {
    Button b2, b3;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.2.23:3033";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        b2 = findViewById(R.id.but2);
        final EditText ed1 = findViewById(R.id.e1);
        final EditText ed2 = findViewById(R.id.e2);
        final EditText ed3 = findViewById(R.id.e3);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final HashMap<String, String> map = new HashMap<>();
                map.put("name", ed1.getText().toString());
                map.put("email", ed2.getText().toString());
                map.put("password", ed3.getText().toString());


                final Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {

                            try {

                                System.out.println("Call");
                                URL url = new URL("http://192.168.2.23:3033/register");
                                HttpURLConnection client = null;
                                client = (HttpURLConnection) url.openConnection();
                                client.setRequestMethod("POST");
                                client.setDoInput(true);
                                client.setDoOutput(true);

                                OutputStream os = client.getOutputStream();
                                BufferedWriter writer = new BufferedWriter(
                                        new OutputStreamWriter(os, "UTF-8"));


                                writer.flush();
                                writer.close();
                                os.close();
                                BufferedReader br;

                                if (100 <= client.getResponseCode() && client.getResponseCode() <= 200) {
                                    br = new BufferedReader(new InputStreamReader(client.getInputStream()));

                                } else {
                                    br = new BufferedReader(new InputStreamReader(client.getErrorStream()));

                                }
                                String content = br.readLine();


                                int responseCode = client.getResponseCode();

                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("ERROR ******** " + e.getMessage());
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });


                thread.start();
            }

        });


        b3 = findViewById(R.id.but3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(signupActivity.this, LoginActivity.class);
                startActivity(i);

            }

            private String getPostDataString(HashMap<String, String> map) throws UnsupportedEncodingException {
                StringBuilder sb = new StringBuilder();
                boolean first = true;
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (first)
                        first = false;
                    else
                        sb.append("&");

                    sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    sb.append("=");
                    sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                }
                return sb.toString();
            }

        });
    }
}













