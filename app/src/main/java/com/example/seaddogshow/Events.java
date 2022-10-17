package com.example.seaddogshow;

public class Events {

    String day;
    String event;
    //String day;

    public Events(){
        // stub
    }

    public Events(String day, String event) {
        this.day = day;
        this.event = event;
        //this.day = day;
    }

    public String getEventDay() {
        return day;
    }

    public String getEventEvent() {
        return event;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setEvent(String event) {
        this.event = event;
    }



}
