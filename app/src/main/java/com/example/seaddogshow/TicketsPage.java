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

    private DatabaseReference dogShowDBRef;

    private Button btnTicketsBack;
    private Button btnTicketsSubmit;

    private EditText etTicketsEmail;
    private EditText etTicketsCC;
    private EditText etTicketsName;
    private EditText etTicketsNum;

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
                Intent intent = new Intent(TicketsPage.this, MainActivity.class);
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

    private void insertTicketInfo () {

        String ticketsName = etTicketsName.getText().toString();
        String creditCard = etTicketsCC.getText().toString();
        String numTickets = etTicketsNum.getText().toString();
        String email = etTicketsEmail.getText().toString();

        String id = dogShowDBRef.push().getKey();

        TicketRequest ticketrequest = new TicketRequest(id, ticketsName, numTickets, creditCard, email);

        dogShowDBRef.push().setValue(ticketrequest);
        Toast.makeText(com.example.seaddogshow.TicketsPage.this, "Ticket request submitted. Thank you!", Toast.LENGTH_SHORT).show();

    }
}

