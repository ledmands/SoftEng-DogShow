package com.example.seaddogshow;

public class Events {

    String day;
    String event;
    String id;

    public Events(){
        // stub
    }

    public Events(String id, String day, String event) {
        this.day = day;
        this.event = event;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getEvent() {
        return event;
    }


}
