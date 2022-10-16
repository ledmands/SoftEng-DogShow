package com.example.seaddogshow;

public class Dogs {

    String name;
    String breed;
    String favorite_toy;
    String events;

    public Dogs(){
        // stub
    }

    public Dogs(String name, String breed, String favorite_toy, String events) {
        this.name = name;
        this.breed = breed;
        this.favorite_toy = favorite_toy;
        this.events = events;
    }

    public String getDogName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getFavoriteToy() {
        return favorite_toy;
    }

    public String getEvents() {
        return events;
    }
}
