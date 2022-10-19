package com.example.seaddogshow;

public class Dogs {

    String name;
    String breed;
    String favoriteToy;
    //String events;
    String id;

    public Dogs(){
        // stub
    }

    public Dogs(String id, String name, String breed, String favoriteToy) {
        this.name = name;
        this.breed = breed;
        this.favoriteToy = favoriteToy;
        //this.events = events;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getFavoriteToy() {
        return favoriteToy;
    }

    /*public String getEvents() {
        return events;
    }*/
}
