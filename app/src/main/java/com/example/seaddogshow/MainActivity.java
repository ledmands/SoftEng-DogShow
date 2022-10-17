package com.example.seaddogshow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Button btnAdmin;
    Button btnTickets;
    Button btnInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInformation = findViewById(R.id.btnInformation);
        btnAdmin = findViewById(R.id.btnAdmin);
        btnTickets = findViewById(R.id.btnTickets);


        btnAdmin.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdminPage.class);
                startActivity(intent);

            }
        }));
        btnTickets.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TicketsPage.class);
                startActivity(intent);

            }
        }));

        btnInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InformationPage.class);
                startActivity(intent);
            }
        });


    } //end OnCreate


}