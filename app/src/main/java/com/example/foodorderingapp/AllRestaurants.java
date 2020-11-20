package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AllRestaurants extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.restaurants);


        ArrayList<Restaurant> restaurants=new ArrayList<Restaurant>();

        restaurants.add(new Restaurant("Sweet Magic","BlackForest Cake"));
        restaurants.add(new Restaurant("Domino's ","Peperoni Cheese Pizza"));
        restaurants.add(new Restaurant("G Pullareddy Sweets","Kaju Sweet"));
        restaurants.add(new Restaurant("Subway","Burger"));
        restaurants.add(new Restaurant("GoChaatiz","Chaat and Pani Puri"));
        restaurants.add(new Restaurant("Paradise Biryani","Biryani, Kebabs"));
        restaurants.add(new Restaurant("LunchBox","North Indian, Desserts, Biryani"));
        restaurants.add(new Restaurant("The ThickShake Factory","Beverages"));
        restaurants.add(new Restaurant("Pizza Hut","Fast Food, Pizzas"));
        restaurants.add(new Restaurant("Burger King","American, Fast Food"));
        restaurants.add(new Restaurant("Starbucks Coffee","Beverages"));
        restaurants.add(new Restaurant("KFC","Fast food"));
        restaurants.add(new Restaurant("Ocean of Bakes","Desserts, Fast Food"));
        restaurants.add(new Restaurant("Pista House","Bakery, Pizzas, Fast Food"));
        restaurants.add(new Restaurant("The Food Rangers","Continental"));
        restaurants.add(new Restaurant("Barbeque","Continental"));

        RestaurantArrayAdapter restaurantArrayAdapter= new RestaurantArrayAdapter(this, R.layout.list_item,restaurants);

        final ListView listView= (ListView) findViewById(R.id.list);

        listView.setAdapter(restaurantArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(view.getContext(),OrderActivity.class);
                Restaurant rest=(Restaurant) listView.getItemAtPosition(position);
                intent.putExtra("RestaurantName",rest.getName());
                intent.putExtra("RestaurantDetails",rest.getDetails());
                startActivity(intent);
            }
        });








    }
}
