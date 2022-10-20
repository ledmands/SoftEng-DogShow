package com.example.seaddogshow;

public class TicketRequest {

    private String ticketsName;
    private String numTickets;
    private String creditCard;
    private String email;
    private String id;

    public TicketRequest() {
        //stub
    }

    public TicketRequest(String id, String ticketsName, String numTickets, String creditCard, String email) {
        this.ticketsName = ticketsName;
        this.numTickets = numTickets;
        this.creditCard = creditCard;
        this.email = email;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // The following getters/setters are unused at this point, but may be important in the future.

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
