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

public class TicketsPage extends AppCompatActivity {
    private static final String TAG = "TicketsPage";



    DatabaseReference dogShowDBRef;
    Button btnTicketsBack;
    Button btnTicketsSubmit;
    EditText etTicketsEmail;
    EditText etTicketsCC;
    EditText etTicketsName;
    EditText etTicketsNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets_page);

        etTicketsNum = findViewById(R.id.etTicketsNum);
        etTicketsName = findViewById(R.id.etTicketsName);
        etTicketsCC = findViewById(R.id.etTicketsCC);
        etTicketsEmail = findViewById(R.id.etTicketsEmail);
        btnTicketsSubmit = findViewById(R.id.btnTicketsSubmit);
        btnTicketsBack = findViewById(R.id.btnTicketsBack);
        dogShowDBRef = FirebaseDatabase.getInstance().getReference().child("tickets");

        btnTicketsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.seaddogshow.TicketsPage.this, InformationPage.class);
                startActivity(intent);
            }
        });

        btnTicketsSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertTicketInfo();
            }
        });

    }

    //InsertMethodstoDB
    private void insertTicketInfo () {

        String ticketsName = etTicketsName.getText().toString();
        String creditCard = etTicketsCC.getText().toString();
        String numTickets = etTicketsNum.getText().toString();
        String email = etTicketsEmail.getText().toString();

        //Anyotherinformationtoadd,likedognames,breed,hometown,etc,couldgohere.

        //CreatenewTrainersobject
        TicketRequest ticketrequest = new TicketRequest(ticketsName, numTickets, creditCard, email);

        dogShowDBRef.push().setValue(ticketrequest);
        Toast.makeText(com.example.seaddogshow.TicketsPage.this, "Ticket request submitted. Thank you!", Toast.LENGTH_SHORT).show();


    }
}

