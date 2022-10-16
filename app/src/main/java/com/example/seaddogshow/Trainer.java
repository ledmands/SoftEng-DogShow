package com.example.seaddogshow;

public class Trainer {

    String name;
    String city;
    String club;
    //String years_experience;

    public Trainer() {
        //stub
    }

    public Trainer(String name, String city, String club) {
        this.name = name;
        this.city = city;
        this.club = club;
        //this.years_experience = years_experience;
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
