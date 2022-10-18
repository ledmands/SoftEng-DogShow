package com.example.seaddogshow;

public class Trainer {

    String name;
    String city;
    String club;
    String id;

    public Trainer() {
        //stub
    }

    public Trainer(String id, String name, String city, String club) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.club = club;
        //this.years_experience = years_experience;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getClub() {
        return club;
    }


}
