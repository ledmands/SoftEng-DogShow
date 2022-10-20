package com.example.seaddogshow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private DatabaseReference dogShowDBRef;

    private Button btnAdmin;
    private Button btnTickets;
    private Button btnInformation;

    private List<Admin> adminList;

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
                // populate a dialog.
                // enter a name into the name field
                // loop:
                // if that name matches an id in the db, then continue
                // else print a toast message

                // This gets the admin information from the DB and sets it into an Admin list
                adminList = new ArrayList<>();

                // Returns the database under the specified path
                dogShowDBRef = FirebaseDatabase.getInstance().getReference("admins");

                dogShowDBRef.addValueEventListener((new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        adminList.clear();

                        // Iterate over the entries in the DB and add the values to a local list
                        for (DataSnapshot adminDataSnap : snapshot.getChildren()) {
                            Admin admin = adminDataSnap.getValue(Admin.class);
                            adminList.add(admin);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }));

                // once button is clicked, show the dialog
                showAdminLogin();

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

    private void showAdminLogin() {
        AlertDialog.Builder loginDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View loginDialogView = inflater.inflate(R.layout.check_admin_dialog, null);

        loginDialog.setView(loginDialogView);

        EditText etAdminName = loginDialogView.findViewById(R.id.etAdminName);
        Button btnEnterAdminCredentials = loginDialogView.findViewById(R.id.btnEnterAdminCredentials);

        loginDialog.setTitle("Enter Admin Name");

        AlertDialog dialog = loginDialog.create();
        dialog.show();

        btnEnterAdminCredentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // if the text in the admin name view
                // Get the values from the view
                String newDay = etAddEventDay.getText().toString();
                String newEvent = etAddEventEvent.getText().toString();
                //String newFavoriteToy = etAddDogFavoriteToy.getText().toString();

                addEventData(newDay, newEvent);
                dialog.dismiss();
            }
        });

    }

}