package com.example.seaddogshow;

public class Trainer {

    private String name;
    private String country;
    private String club;
    private String id;

    public Trainer() {
        //stub
    }

    public Trainer(String id, String name, String country, String club) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.club = club;
        //this.years_experience = years_experience;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getClub() {
        return club;
    }


}
