package com.example.foodorderingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class RestaurantArrayAdapter extends ArrayAdapter <Restaurant> {

    public RestaurantArrayAdapter(Context context,int resource, List<Restaurant> objects){
        super(context,0,objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Restaurant restaurant=getItem(position);

        View list_view = convertView;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }


        TextView name = (TextView) convertView.findViewById(R.id.text1);
        name.setText(restaurant.getName());

        TextView details = (TextView) convertView.findViewById(R.id.text2);
        details.setText(restaurant.getDetails());







        return  convertView;
    }

}
