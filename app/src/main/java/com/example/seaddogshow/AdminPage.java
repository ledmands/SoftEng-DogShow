package com.example.seaddogshow;

import android.content.Intent;
import android.media.metrics.Event;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

    DatabaseReference dogShowDBRef;

    ListView lvAdminPage;
    Button btnAdminBack;
    Button btnAdminEditDogs;
    Button btnAdminEditTrainers;
    Button btnAdminEditEvents;
    Button btnAdminAddTrainer;
    Button btnAdminAddDog;
    Button btnAdminAddEvent;
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
        btnAdminEditEvents = findViewById(R.id.btnAdminEditEvents);
        btnAdminAddTrainer = findViewById(R.id.btnAdminAddTrainer);
        btnAdminAddDog = findViewById(R.id.btnAdminAddDog);
        btnAdminAddEvent = findViewById(R.id.btnAdminAddEvent);
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

                        showUpdateTrainer(trainer.getId(), trainer.getName(), trainer.getCountry(), trainer.getClub());
                        return false;
                    }
                });
            }
        });

        btnAdminAddTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddTrainer();
            }
        });

        btnAdminAddDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDog();
            }
        });

        btnAdminEditDogs.setOnClickListener(new View.OnClickListener() {
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
                            Dogs dog = dogsDataSnap.getValue(Dogs.class);
                            dogsList.add(dog);
                        }

                        DogsListAdapter adapter = new DogsListAdapter(AdminPage.this, dogsList);
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
                        Dogs dog = dogsList.get(i);

                        showUpdateDog(dog.getId(), dog.getName(), dog.getBreed(), dog.getFavoriteToy());
                        return false;
                    }
                });
            }


        });

        btnAdminAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddEvent();
            }
        });

        btnAdminEditEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventsList = new ArrayList<>();

                // Returns the database under the specified path
                dogShowDBRef = FirebaseDatabase.getInstance().getReference("events");

                dogShowDBRef.addValueEventListener((new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        eventsList.clear();

                        // Iterate over the entries in the DB and add the values to a local list
                        for (DataSnapshot eventDataSnap : snapshot.getChildren()) {
                            Events event = eventDataSnap.getValue(Events.class);
                            eventsList.add(event);
                        }

                        EventListAdapter adapter = new EventListAdapter(AdminPage.this, eventsList);
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

                        // i is the position integer
                        Events event = eventsList.get(i);

                        showUpdateEvents(event.getId(), event.getDay(), event.getEvent());
                        return false;
                    }
                });
            }
        });

    } // end onCreate

    private void showUpdateEvents(String id, String day, String event) {
        AlertDialog.Builder eventDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View eventDialogView = inflater.inflate(R.layout.update_event_dialog, null);

        eventDialog.setView(eventDialogView);

        // now need the view references (like we have in the class prior to onCreate) within the dialog view
        EditText etUpdateEventDay = eventDialogView.findViewById(R.id.etUpdateEventDay);
        EditText etUpdateEventEvent = eventDialogView.findViewById(R.id.etUpdateEventEvent);
        Button btnUpdateEvent = eventDialogView.findViewById(R.id.btnUpdateEvent);

        // auto-populates fields to current values
        etUpdateEventDay.setText(day);
        etUpdateEventEvent.setText(event);

        // Now show the dialog box
        eventDialog.setTitle(String.format("Updating %s", event));

        AlertDialog dialog = eventDialog.create();
        dialog.show();

        // Here we set the click listener for the update button
        btnUpdateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the values from the view
                String newDay = etUpdateEventDay.getText().toString();
                String newEvent = etUpdateEventEvent.getText().toString();

                updateEventData(id, newDay, newEvent);
                dialog.dismiss();
            }
        });
    }

    private void updateEventData(String id, String day, String event) {
        dogShowDBRef = FirebaseDatabase.getInstance().getReference("events").child(id);
        Events newEvent = new Events(id, day, event);
        dogShowDBRef.setValue(newEvent);
    }

    private void showAddEvent() {
        AlertDialog.Builder eventDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View eventDialogView = inflater.inflate(R.layout.add_event_dialog, null);

        eventDialog.setView(eventDialogView);

        // now need the view references (like we have in the class prior to onCreate) within the dialog view
        EditText etAddEventDay = eventDialogView.findViewById(R.id.etAddEventDay);
        EditText etAddEventEvent = eventDialogView.findViewById(R.id.etAddEventEvent);
        //EditText etAddDogFavoriteToy = eventDialogView.findViewById(R.id.etAddDogFavoriteToy);
        Button btnAddEvent = eventDialogView.findViewById(R.id.btnAddEvent);

        // Now show the dialog box
        eventDialog.setTitle(String.format("Add New Event"));

        AlertDialog dialog = eventDialog.create();
        dialog.show();

        // Here we set the click listener for the update button
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the values from the view
                String newDay = etAddEventDay.getText().toString();
                String newEvent = etAddEventEvent.getText().toString();
                //String newFavoriteToy = etAddDogFavoriteToy.getText().toString();

                addEventData(newDay, newEvent);
                dialog.dismiss();
            }
        });

    }

    private void addEventData(String day, String event) {
        dogShowDBRef = FirebaseDatabase.getInstance().getReference("events");
        String id = dogShowDBRef.push().getKey();
        Events newEvent = new Events(id, day, event);
        dogShowDBRef.child(id).setValue(newEvent);
    }

    private void showAddDog() {

        AlertDialog.Builder dogDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dogDialogView = inflater.inflate(R.layout.add_dog_dialog, null);

        dogDialog.setView(dogDialogView);

        // now need the view references (like we have in the class prior to onCreate) within the dialog view
        EditText etAddDogName = dogDialogView.findViewById(R.id.etAddDogName);
        EditText etAddDogBreed = dogDialogView.findViewById(R.id.etAddDogBreed);
        EditText etAddDogFavoriteToy = dogDialogView.findViewById(R.id.etAddDogFavoriteToy);
        Button btnAddDog = dogDialogView.findViewById(R.id.btnAddDog);

        // Now show the dialog box
        dogDialog.setTitle(String.format("Add New Dog"));

        AlertDialog dialog = dogDialog.create();
        dialog.show();

        // Here we set the click listener for the update button
        btnAddDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the values from the view
                String newName = etAddDogName.getText().toString();
                String newBreed = etAddDogBreed.getText().toString();
                String newFavoriteToy = etAddDogFavoriteToy.getText().toString();

                addDogData(newName, newBreed, newFavoriteToy);
                dialog.dismiss();
            }
        });
    }

    private void addDogData(String name, String breed, String favoriteToy) {
        dogShowDBRef = FirebaseDatabase.getInstance().getReference("dogs");
        String id = dogShowDBRef.push().getKey();
        Dogs dog = new Dogs(id, name, breed, favoriteToy);
        dogShowDBRef.child(id).setValue(dog);

    }

    private void showUpdateDog(String id, String name, String breed, String favoriteToy) {

        AlertDialog.Builder dogDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dogDialogView = inflater.inflate(R.layout.update_dog_dialog, null);

        dogDialog.setView(dogDialogView);

        // now need the view references (like we have in the class prior to onCreate) within the dialog view
        EditText etUpdateDogName = dogDialogView.findViewById(R.id.etUpdateDogName);
        EditText etUpdateDogBreed = dogDialogView.findViewById(R.id.etUpdateDogBreed);
        EditText etUpdateDogFavoriteToy = dogDialogView.findViewById(R.id.etUpdateDogFavoriteToy);
        Button btnUpdateDog = dogDialogView.findViewById(R.id.btnUpdateDog);

        // auto-populates fields to current values
        etUpdateDogName.setText(name);
        etUpdateDogBreed.setText(breed);
        etUpdateDogFavoriteToy.setText(favoriteToy);

        // Now show the dialog box
        dogDialog.setTitle(String.format("Updating %s", name));

        AlertDialog dialog = dogDialog.create();
        dialog.show();

        // Here we set the click listener for the update button
        btnUpdateDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the values from the view
                String newName = etUpdateDogName.getText().toString();
                String newBreed = etUpdateDogBreed.getText().toString();
                String newFavoriteToy = etUpdateDogFavoriteToy.getText().toString();

                updateDogData(id, newName, newBreed, newFavoriteToy);
                dialog.dismiss();
            }
        });
    }

    private void updateDogData(String id, String name, String breed, String favoriteToy) {
        dogShowDBRef = FirebaseDatabase.getInstance().getReference("dogs").child(id);
        Dogs dog = new Dogs(id, name, breed, favoriteToy);
        dogShowDBRef.setValue(dog);
    }

    private void showAddTrainer() {
        // don't need to pass an id or name here
        // because the name is only used for UX and the id has not yet been created

        AlertDialog.Builder trainerDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View trainerDialogView = inflater.inflate(R.layout.add_trainer_dialog, null);

        trainerDialog.setView(trainerDialogView);

        // now need the view references (like we have in the class prior to onCreate) within the dialog view
        EditText etAddTrainerName = trainerDialogView.findViewById(R.id.etAddTrainerName);
        EditText etAddTrainerCountry = trainerDialogView.findViewById(R.id.etAddTrainerCountry);
        EditText etAddTrainerClub = trainerDialogView.findViewById(R.id.etAddTrainerClub);
        Button btnAddTrainer = trainerDialogView.findViewById(R.id.btnAddTrainer);

        // Now show the dialog box
        trainerDialog.setTitle(String.format("Add New Trainer"));

        AlertDialog dialog = trainerDialog.create();
        dialog.show();

        // Here we set the click listener for the update button
        btnAddTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the values from the view
                String newName = etAddTrainerName.getText().toString();
                String newCountry = etAddTrainerCountry.getText().toString();
                String newClub = etAddTrainerClub.getText().toString();

                addTrainerData(newName, newCountry, newClub);
                dialog.dismiss();
            }
        });
    }

    private void addTrainerData(String name, String country, String club) {
        dogShowDBRef = FirebaseDatabase.getInstance().getReference("trainers");
        String id = dogShowDBRef.push().getKey();
        Trainer trainer = new Trainer(id, name, country, club);
        dogShowDBRef.child(id).setValue(trainer);
    }

    private void showUpdateTrainer(String id, String name, String country, String club) {

        AlertDialog.Builder trainerDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View trainerDialogView = inflater.inflate(R.layout.update_trainer_dialog, null);

        trainerDialog.setView(trainerDialogView);

        // now need the view references (like we have in the class prior to onCreate) within the dialog view
        EditText etUpdateTrainerName = trainerDialogView.findViewById(R.id.etUpdateTrainerName);
        EditText etUpdateTrainerCountry = trainerDialogView.findViewById(R.id.etUpdateTrainerCountry);
        EditText etUpdateTrainerClub = trainerDialogView.findViewById(R.id.etUpdateTrainerClub);
        Button btnUpdateTrainer = trainerDialogView.findViewById(R.id.btnUpdateTrainer);

        etUpdateTrainerName.setText(name);
        etUpdateTrainerCountry.setText(country);
        etUpdateTrainerClub.setText(club);

        // Now show the dialog box
        trainerDialog.setTitle(String.format("Updating %s", name));

        AlertDialog dialog = trainerDialog.create();
        dialog.show();

        // Here we set the click listener for the update button
        btnUpdateTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the values from the view
                String newName = etUpdateTrainerName.getText().toString();
                String newCountry = etUpdateTrainerCountry.getText().toString();
                String newClub = etUpdateTrainerClub.getText().toString();

                updateTrainerData(id, newName, newCountry, newClub);
                dialog.dismiss();
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
