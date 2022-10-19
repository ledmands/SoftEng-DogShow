package com.example.seaddogshow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.metrics.Event;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InformationPage extends AppCompatActivity {

    private DatabaseReference dogShowDBRef;

    private ListView lvInformation;

    private Button btnShowTrainers;
    private Button btnShowDogs;
    private Button btnShowEvents;
    private Button btnGoHome;

    private List<Trainer> trainerList;
    private List<Dogs> dogsList;
    private List<Events> eventList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_page);

        btnShowTrainers = findViewById(R.id.btnShowTrainers);
        lvInformation = findViewById(R.id.lvInformation);
        btnShowDogs = findViewById(R.id.btnShowDogs);
        btnShowEvents = findViewById(R.id.btnShowEvents);
        btnGoHome = findViewById(R.id.btnGoHome);

        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnShowTrainers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainerList = new ArrayList<>();

                // Returns the database under the specified path
                dogShowDBRef = FirebaseDatabase.getInstance().getReference("trainers");

                dogShowDBRef.addValueEventListener((new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        trainerList.clear();

                        // Iterate over the entries in the DB and add the values to a local list
                        for (DataSnapshot trainerDataSnap : snapshot.getChildren()) {
                            Trainer trainer = trainerDataSnap.getValue(Trainer.class);
                            trainerList.add(trainer);
                        }

                        ListAdapter adapter = new ListAdapter(InformationPage.this, trainerList);
                        lvInformation.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }));
            }
        });

        btnShowDogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogsList = new ArrayList<>();

                // Returns the database under the specified path
                dogShowDBRef = FirebaseDatabase.getInstance().getReference("dogs");

                dogShowDBRef.addValueEventListener((new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dogsList.clear();

                        // Iterate over the entries in the DB and add the values to a local list
                        for (DataSnapshot dogsDataSnap : snapshot.getChildren()) {
                            Dogs dogs = dogsDataSnap.getValue(Dogs.class);
                            dogsList.add(dogs);
                        }

                        DogsListAdapter adapter = new DogsListAdapter(InformationPage.this, dogsList);
                        lvInformation.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }));
            }
        });

        btnShowEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventList = new ArrayList<>();

                // Returns the database under the specified path
                dogShowDBRef = FirebaseDatabase.getInstance().getReference("events");

                dogShowDBRef.addValueEventListener((new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        eventList.clear();

                        // Iterate over the entries in the DB and add the values to a local list
                        for (DataSnapshot eventDataSnap : snapshot.getChildren()) {
                            Events events = eventDataSnap.getValue(Events.class);
                            eventList.add(events);
                        }

                        EventListAdapter adapter = new EventListAdapter(InformationPage.this, eventList);
                        lvInformation.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }));
            }
        });

    }
}