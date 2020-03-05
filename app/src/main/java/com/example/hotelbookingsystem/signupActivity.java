package com.example.hotelbookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class signupActivity extends AppCompatActivity {
Button b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
      
        b3 = findViewById(R.id.but3); b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(signupActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
    }

