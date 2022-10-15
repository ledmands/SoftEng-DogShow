package com.example.seaddogshow;

public class Trainer {

    String name;
    String town;
    String organization;
    String about;

    public Trainer(String name, String town, String organization, String about) {
        this.name = name;
        this.town = town;
        this.organization = organization;
        this.about = about;
    }

    public String getName() {
        return name;
    }

    public String getTown() {
        return town;
    }

    public String getOrganization() {
        return organization;
    }

    public String getAbout() {
        return about;
    }
}
