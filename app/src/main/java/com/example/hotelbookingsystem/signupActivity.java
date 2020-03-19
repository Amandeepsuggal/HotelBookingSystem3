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

import java.util.HashMap;

public class signupActivity extends AppCompatActivity implements View.OnClickListener {

    EditText ed1, ed2, ed3;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ed1 = findViewById(R.id.e1);
        ed2 = findViewById(R.id.e2);
        ed3 = findViewById(R.id.e3);
        b1 = findViewById(R.id.but2);
        b2 = findViewById(R.id.but3);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but2:
                RetreiveData();
                break;
            case R.id.but3:
                adddataFirestore();
                break;
        }
        }
    public  void RetreiveData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Orders").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                System.out.println(documentSnapshot.getData().toString());

                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
        public void adddataFirestore() {
            String name = ed1.getText().toString();
            String email = ed2.getText().toString();
            String password = ed3.getText().toString();

            HashMap<String,String> order = new HashMap<>();
            order.put("name", name);
            order.put("email", email);
            order.put("password", password);
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("Orders")
                    .add(order)



                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(signupActivity.this, "Done", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(signupActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace(); }
                    });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(signupActivity.this,LoginActivity
                            .class);
            startActivity(i);
        }
});

        }

}