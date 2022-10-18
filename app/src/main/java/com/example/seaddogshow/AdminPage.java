package com.example.seaddogshow;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminPage extends AppCompatActivity {

    // need edit dogs
    // edit schedule
    // edit trainers
    // back button
    //push().setValue("Hello, World!");

    DatabaseReference dogShowDBRef;

    ListView lvAdminPage;
    Button btnAdminBack;
    Button btnAdminEditDogs;
    Button btnAdminEditTrainers;
    Button btnAdminEditSchedule;
    List<Trainer> trainerList;
    List<Dogs> dogsList;
    List<Events> eventsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);

        btnAdminBack = findViewById(R.id.btnAdminBack);
        btnAdminEditDogs = findViewById(R.id.btnAdminEditDogs);
        btnAdminEditTrainers = findViewById(R.id.btnAdminEditTrainers);
        btnAdminEditSchedule = findViewById(R.id.btnAdminEditSchedule);
        lvAdminPage = findViewById(R.id.lvAdminPage);

        btnAdminBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPage.this, MainActivity.class) ;
                startActivity(intent);
            }
        });

        btnAdminEditTrainers.setOnClickListener(new View.OnClickListener() {
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

                        ListAdapter adapter = new ListAdapter(AdminPage.this, trainerList);
                        lvAdminPage.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }));

                // Here we set the long press listener to the list view
                lvAdminPage.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //String id = dogShowDBRef.getKey();
                        // i is the position integer
                        Trainer trainer = trainerList.get(i);

                        //String id = dogShowDBRef.getKey();
                        showUpdateTrainer(trainer.getId(), trainer.getName());
                        return false;
                    }
                });
            }
        });

    } // end onCreate

    private void showUpdateTrainer(String id, String name) {

        AlertDialog.Builder trainerDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View trainerDialogView = inflater.inflate(R.layout.update_trainer_dialog, null);

        trainerDialog.setView(trainerDialogView);

        // now need the view references (like we have in the class prior to onCreate) within the dialog view
        EditText etUpdateTrainerName = trainerDialogView.findViewById(R.id.etUpdateTrainerName);
        EditText etUpdateTrainerCountry = trainerDialogView.findViewById(R.id.etUpdateTrainerCountry);
        EditText etUpdateTrainerClub = trainerDialogView.findViewById(R.id.etUpdateTrainerClub);
        Button btnUpdateTrainer = trainerDialogView.findViewById(R.id.btnUpdateTrainer);

        // Now show the dialog box
        trainerDialog.setTitle(String.format("Updating %s", name));
        trainerDialog.show();

        // Here we set the click listener for the update button
        btnUpdateTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the values from the view
                String newName = etUpdateTrainerName.getText().toString();
                String newCountry = etUpdateTrainerCountry.getText().toString();
                String newClub = etUpdateTrainerClub.getText().toString();

                updateTrainerData(id, newName, newCountry, newClub);
            }
        });
    }

    private void updateTrainerData(String id, String name, String country, String club) {

        // use the db reference and id to identify which record to update
        dogShowDBRef = FirebaseDatabase.getInstance().getReference("trainers").child(id);
        Trainer trainer = new Trainer(id, name, country, club);
        dogShowDBRef.setValue(trainer);
    }
}
