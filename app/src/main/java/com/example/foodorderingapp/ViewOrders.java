package com.example.foodorderingapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;

public class ViewOrders extends AppCompatActivity{

    CustomArrayAdapter customArrayAdapter;

    RecyclerView recview;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recview);


        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;


        recview=(RecyclerView) findViewById(R.id.RecView);

        recview.setLayoutManager(new LinearLayoutManager(this));




        FirebaseRecyclerOptions<Order> options =
                new FirebaseRecyclerOptions.Builder<Order>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users/"+currentFirebaseUser.getUid()+"/Orders"), Order.class)
                        .build();
                customArrayAdapter=new CustomArrayAdapter(options);

                recview.setAdapter(customArrayAdapter);


    }
    @Override
    protected void onStart() {
        super.onStart();
        customArrayAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        customArrayAdapter.stopListening();
    }


}