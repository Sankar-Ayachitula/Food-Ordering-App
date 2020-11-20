package com.example.foodorderingapp;

public class Order {
    private String Restaurant;
    private String Order_Name;
    private String date;
    private String Time;

    public Order(){

    }
    public Order(String Restaurant, String Order_Name,String date,String time ){
        this.Restaurant=Restaurant;
        this.Order_Name=Order_Name;
        this.date=date;
        this.Time=time;
    }

    public String getDate() {
        return date;
    }

    public String getOrder_Name() {
        return Order_Name;
    }

    public String getRestaurant() {
        return Restaurant;
    }

    public String getTime() {
        return Time;
    }
}
