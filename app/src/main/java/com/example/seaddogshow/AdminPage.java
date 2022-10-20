package com.example.seaddogshow;

import android.content.Intent;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminPage extends AppCompatActivity {

    private DatabaseReference dogShowDBRef;

    private ListView lvAdminPage;

    private Button btnAdminBack;
    private Button btnAdminEditDogs;
    private Button btnAdminEditTrainers;
    private Button btnAdminEditEvents;
    private Button btnAdminAddTrainer;
    private Button btnAdminAddDog;
    private Button btnAdminAddEvent;

    private List<Trainer> trainerList;
    private List<Dogs> dogsList;
    private List<Events> eventsList;

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
        }); // end btnAdminBack OnClickListener

        btnAdminAddTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddTrainer();
            }
        }); // end btnAdminAddTrainer OnClickListener

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

                        TrainerListAdapter adapter = new TrainerListAdapter(AdminPage.this, trainerList);
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
        }); // end btnAdminEditTrainers OnClickListener

        btnAdminAddDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDog();
            }
        }); // end btnAdminAddDog OnClickListener

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


        }); // end btnAdminEditDogs OnClickListener

        btnAdminAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddEvent();
            }
        }); // end btnAdminAddEvent OnClickListener

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
        }); // end btnAdminEditEvents OnClickListener

    } // end onCreate


    //----- EVENT METHODS -----//

    private void showAddEvent() {
        AlertDialog.Builder eventDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View eventDialogView = inflater.inflate(R.layout.add_event_dialog, null);

        eventDialog.setView(eventDialogView);

        EditText etAddEventDay = eventDialogView.findViewById(R.id.etAddEventDay);
        EditText etAddEventEvent = eventDialogView.findViewById(R.id.etAddEventEvent);
        Button btnAddEvent = eventDialogView.findViewById(R.id.btnAddEvent);

        eventDialog.setTitle("Add New Event");

        AlertDialog dialog = eventDialog.create();
        dialog.show();

        // Set the onclick listener for the update button
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the values from the view
                String newDay = etAddEventDay.getText().toString();
                String newEvent = etAddEventEvent.getText().toString();

                addEventData(newDay, newEvent);
                dialog.dismiss();
                Toast.makeText(AdminPage.this, "Event added", Toast.LENGTH_SHORT).show();
            }
        });

    } // end showAddEvent

    private void addEventData(String day, String event) {
        dogShowDBRef = FirebaseDatabase.getInstance().getReference("events");
        String id = dogShowDBRef.push().getKey();
        Events newEvent = new Events(id, day, event);
        dogShowDBRef.child(id).setValue(newEvent);
    } // end addEventData

    private void showUpdateEvents(String id, String day, String event) {
        AlertDialog.Builder eventDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View eventDialogView = inflater.inflate(R.layout.update_event_dialog, null);

        eventDialog.setView(eventDialogView);

        EditText etUpdateEventDay = eventDialogView.findViewById(R.id.etUpdateEventDay);
        EditText etUpdateEventEvent = eventDialogView.findViewById(R.id.etUpdateEventEvent);
        Button btnUpdateEvent = eventDialogView.findViewById(R.id.btnUpdateEvent);
        Button btnDeleteEvent = eventDialogView.findViewById(R.id.btnDeleteEvent);

        // auto-populates fields to current values
        etUpdateEventDay.setText(day);
        etUpdateEventEvent.setText(event);

        // Show the dialog box
        eventDialog.setTitle(String.format("Updating %s", event));

        AlertDialog dialog = eventDialog.create();
        dialog.show();

        // Set the onclick listener for the update button
        btnUpdateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the values from the view
                String newDay = etUpdateEventDay.getText().toString();
                String newEvent = etUpdateEventEvent.getText().toString();

                updateEventData(id, newDay, newEvent);
                dialog.dismiss();
                Toast.makeText(AdminPage.this, "Event updated", Toast.LENGTH_SHORT).show();

            }
        });

        btnDeleteEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteEvent(id);
                dialog.dismiss();
                Toast.makeText(AdminPage.this, "Event deleted", Toast.LENGTH_SHORT).show();
            }
        });
    } // end showUpdateEvents

    private void updateEventData(String id, String day, String event) {
        dogShowDBRef = FirebaseDatabase.getInstance().getReference("events").child(id);
        Events newEvent = new Events(id, day, event);
        dogShowDBRef.setValue(newEvent);
    } // end updateEventData

    private void deleteEvent(String id){
        dogShowDBRef = FirebaseDatabase.getInstance().getReference("events").child(id);

        Task<Void> mTask = dogShowDBRef.removeValue();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    } // end deleteEvent


    //----- DOG METHODS -----//

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
        dogDialog.setTitle("Add New Dog");

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
                Toast.makeText(AdminPage.this, "Dog added", Toast.LENGTH_SHORT).show();

            }
        });
    } // end showAddDog

    private void addDogData(String name, String breed, String favoriteToy) {
        dogShowDBRef = FirebaseDatabase.getInstance().getReference("dogs");
        String id = dogShowDBRef.push().getKey();
        Dogs dog = new Dogs(id, name, breed, favoriteToy);
        dogShowDBRef.child(id).setValue(dog);

    } // end addDogData

    private void showUpdateDog(String id, String name, String breed, String favoriteToy) {

        AlertDialog.Builder dogDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dogDialogView = inflater.inflate(R.layout.update_dog_dialog, null);

        dogDialog.setView(dogDialogView);

        EditText etUpdateDogName = dogDialogView.findViewById(R.id.etUpdateDogName);
        EditText etUpdateDogBreed = dogDialogView.findViewById(R.id.etUpdateDogBreed);
        EditText etUpdateDogFavoriteToy = dogDialogView.findViewById(R.id.etUpdateDogFavoriteToy);
        Button btnUpdateDog = dogDialogView.findViewById(R.id.btnUpdateDog);
        Button btnDeleteDog = dogDialogView.findViewById(R.id.btnDeleteDog);

        // auto-populates fields to current values
        etUpdateDogName.setText(name);
        etUpdateDogBreed.setText(breed);
        etUpdateDogFavoriteToy.setText(favoriteToy);

        dogDialog.setTitle(String.format("Updating %s", name));

        AlertDialog dialog = dogDialog.create();
        dialog.show();

        btnUpdateDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the values from the view
                String newName = etUpdateDogName.getText().toString();
                String newBreed = etUpdateDogBreed.getText().toString();
                String newFavoriteToy = etUpdateDogFavoriteToy.getText().toString();

                updateDogData(id, newName, newBreed, newFavoriteToy);
                dialog.dismiss();
                Toast.makeText(AdminPage.this, "Dog updated", Toast.LENGTH_SHORT).show();

            }
        });

        btnDeleteDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDog(id);
                dialog.dismiss();
                Toast.makeText(AdminPage.this, "Dog deleted", Toast.LENGTH_SHORT).show();

            }
        });
    } // end showUpdateDog

    private void updateDogData(String id, String name, String breed, String favoriteToy) {
        dogShowDBRef = FirebaseDatabase.getInstance().getReference("dogs").child(id);
        Dogs dog = new Dogs(id, name, breed, favoriteToy);
        dogShowDBRef.setValue(dog);
    } // end updateDogData

    private void deleteDog(String id){
        dogShowDBRef = FirebaseDatabase.getInstance().getReference("dogs").child(id);

        Task<Void> mTask = dogShowDBRef.removeValue();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    } // end deleteDog


    //----- TRAINER METHODS -----//

    private void showAddTrainer() {
        // don't need to pass an id or name here because the name is only used for UX and the id has not yet been created

        AlertDialog.Builder trainerDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View trainerDialogView = inflater.inflate(R.layout.add_trainer_dialog, null);

        trainerDialog.setView(trainerDialogView);

        EditText etAddTrainerName = trainerDialogView.findViewById(R.id.etAddTrainerName);
        EditText etAddTrainerCountry = trainerDialogView.findViewById(R.id.etAddTrainerCountry);
        EditText etAddTrainerClub = trainerDialogView.findViewById(R.id.etAddTrainerClub);
        Button btnAddTrainer = trainerDialogView.findViewById(R.id.btnAddTrainer);

        // Now show the dialog box
        trainerDialog.setTitle("Add New Trainer");

        AlertDialog dialog = trainerDialog.create();
        dialog.show();

        // Now set the onclick listener for the update button
        btnAddTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the values from the view
                String newName = etAddTrainerName.getText().toString();
                String newCountry = etAddTrainerCountry.getText().toString();
                String newClub = etAddTrainerClub.getText().toString();

                addTrainerData(newName, newCountry, newClub);
                dialog.dismiss();
                Toast.makeText(AdminPage.this, "Trainer Added", Toast.LENGTH_SHORT).show();

            }
        });
    } // end showAddTrainer

    private void addTrainerData(String name, String country, String club) {
        dogShowDBRef = FirebaseDatabase.getInstance().getReference("trainers");
        String id = dogShowDBRef.push().getKey();
        Trainer trainer = new Trainer(id, name, country, club);
        dogShowDBRef.child(id).setValue(trainer);
    } // end addTrainerData

    private void showUpdateTrainer(String id, String name, String country, String club) {

        AlertDialog.Builder trainerDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View trainerDialogView = inflater.inflate(R.layout.update_trainer_dialog, null);

        trainerDialog.setView(trainerDialogView);

        EditText etUpdateTrainerName = trainerDialogView.findViewById(R.id.etUpdateTrainerName);
        EditText etUpdateTrainerCountry = trainerDialogView.findViewById(R.id.etUpdateTrainerCountry);
        EditText etUpdateTrainerClub = trainerDialogView.findViewById(R.id.etUpdateTrainerClub);
        Button btnUpdateTrainer = trainerDialogView.findViewById(R.id.btnUpdateTrainer);
        Button btnDeleteTrainer = trainerDialogView.findViewById(R.id.btnDeleteTrainer);

        etUpdateTrainerName.setText(name);
        etUpdateTrainerCountry.setText(country);
        etUpdateTrainerClub.setText(club);

        // Now show the dialog box
        trainerDialog.setTitle(String.format("Updating %s", name));

        AlertDialog dialog = trainerDialog.create();
        dialog.show();

        // Now set the onclick listener for the update button
        btnUpdateTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the values from the view
                String newName = etUpdateTrainerName.getText().toString();
                String newCountry = etUpdateTrainerCountry.getText().toString();
                String newClub = etUpdateTrainerClub.getText().toString();

                updateTrainerData(id, newName, newCountry, newClub);
                dialog.dismiss();
                Toast.makeText(AdminPage.this, "Trainer updated", Toast.LENGTH_SHORT).show();

            }
        });

        btnDeleteTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTrainer(id);
                dialog.dismiss();
                Toast.makeText(AdminPage.this, "Trainer deleted", Toast.LENGTH_SHORT).show();

            }
        });
    } // end showUpdateTrainer

    private void updateTrainerData(String id, String name, String country, String club) {

        // use the db reference and id to identify which record to update
        dogShowDBRef = FirebaseDatabase.getInstance().getReference("trainers").child(id);
        Trainer trainer = new Trainer(id, name, country, club);
        dogShowDBRef.setValue(trainer);

    } // end updateTrainerData

    private void deleteTrainer(String id){
        dogShowDBRef = FirebaseDatabase.getInstance().getReference("trainers").child(id);

        Task<Void> mTask = dogShowDBRef.removeValue();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    } // end deleteTrainer


} // end AdminPage
