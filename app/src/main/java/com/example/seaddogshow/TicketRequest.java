package com.example.seaddogshow;

public class TicketRequest {

    String ticketsName;
    String numTickets;
    String creditCard;
    String email;

    public TicketRequest() {
        //stub
    }

    public TicketRequest(String ticketsName, String numTickets, String creditCard, String email) {
        this.ticketsName = ticketsName;
        this.numTickets = numTickets;
        this.creditCard = creditCard;
        this.email = email;
    }

    public String getTicketsName() {

        return ticketsName;
    }

    public void setTicketsName(String ticketsName) {
        this.ticketsName = ticketsName;
    }

    public String getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(String numTickets) {
        this.numTickets = numTickets;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
