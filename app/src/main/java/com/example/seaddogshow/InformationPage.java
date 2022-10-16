package com.example.seaddogshow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    DatabaseReference dogShowDBRef;
    ListView lvInformation;
    Button btnShowTrainers;
    Button btnShowDogs;
    Button btnShowEvents;
    List<Trainer> trainerList;
    //TextView listLabelName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_page);

        btnShowTrainers = findViewById(R.id.btnShowTrainers);
        lvInformation = findViewById(R.id.lvInformation);
        //listLabelName = findViewById(R.id.listLabelName);


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


    }
}