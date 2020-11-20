package com.example.foodorderingapp;

public class Restaurant{
    private String name;
    private String details;

    private int resource_id;

    public Restaurant(String name, String details ){
        this.name=name;
        this.details=details;
    }

    public Restaurant(String name, String details, int resource_id ){
        this.name=name;
        this.details=details;
        this.resource_id=resource_id;
    }


    public String getName() {
        return name;
    }

    public String getDetails(){
        return details;
    }
}
