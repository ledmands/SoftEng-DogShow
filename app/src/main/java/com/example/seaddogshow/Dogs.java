package com.example.seaddogshow;

public class Dogs {

    private String name;
    private String breed;
    private String favoriteToy;
    //String events;
    private String id;

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
