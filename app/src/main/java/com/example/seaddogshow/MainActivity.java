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
    EditText etTrainerName;
    EditText etTrainerTown;
    EditText etTrainerOrg;
    EditText etTrainerAbout;
    //Button btn_add_name;
    Button btnSubmitData;
    //ListView schedule;
    Button btnInformation;

    DatabaseReference dogShowDBRef;

    // Write a message to the database
    //FirebaseDatabase database = FirebaseDatabase.getInstance("https://softengprojone-7e4e6-default-rtdb.firebaseio.com/");
    //DatabaseReference myRef = database.getReference("message");

    //push().setValue("Hello, World!");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTrainerName = findViewById(R.id.etTrainerName);
        etTrainerTown = findViewById(R.id.etTrainerTown);
        etTrainerOrg = findViewById(R.id.etTrainerOrg);
        etTrainerAbout = findViewById(R.id.etTrainerAbout);
        //btn_get_schedule = findViewById(R.id.btn_get_schedule);
        btnSubmitData = findViewById(R.id.btnSubmitData);
        //schedule = findViewById(R.id.schedule);
        btnInformation = findViewById(R.id.btnInformation);

        //myRef.push().setValue("Hello World!");

        //Toast.makeText(MainActivity.this, "firebase cxn success!", Toast.LENGTH_LONG).show();

        dogShowDBRef = FirebaseDatabase.getInstance().getReference().child("Trainer Bios");

        btnSubmitData.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertTrainerName();
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

    // Insert Methods to DB
    private void insertTrainerName () {

        String name = etTrainerName.getText().toString();
        String town = etTrainerTown.getText().toString();
        String org = etTrainerOrg.getText().toString();
        String yearsExp = etTrainerAbout.getText().toString();
        // Any other information to add, like dog names, breed, hometown, etc, could go here.

        // Create new Trainers object
        Trainer trainer = new Trainer(name, town, org);

        // Push object to the DB, .push() generates a unique ID so that records will not be overwritten
        dogShowDBRef.push().setValue(trainer);
        Toast.makeText(MainActivity.this, "data pushed successfully", Toast.LENGTH_SHORT).show();
    }
}