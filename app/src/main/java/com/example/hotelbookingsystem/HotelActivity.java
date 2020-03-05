package com.example.hotelbookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HotelActivity extends AppCompatActivity {
Button b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        b5 = findViewById(R.id.but5); b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HotelActivity.this,SearchActivity.class);
                startActivity(i);
            }
        });
    }
}
