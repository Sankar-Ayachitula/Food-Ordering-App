package com.example.foodorderingapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CustomArrayAdapter extends FirebaseRecyclerAdapter<Order,CustomArrayAdapter.myviewholder>{


        public CustomArrayAdapter(@NonNull FirebaseRecyclerOptions<Order> options) {
                super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Order model) {
                holder.res_name.setText(model.getRestaurant());
                holder.order.setText(model.getOrder_Name());
                holder.date.setText(model.getDate());
                holder.time.setText(model.getTime());



        }

        @NonNull
        @Override
        public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
                return new myviewholder(view);
        }

        class myviewholder extends RecyclerView.ViewHolder{

                TextView res_name,order,date,time;
                public myviewholder(@NonNull View itemView) {
                        super(itemView);
                        res_name= (TextView) itemView.findViewById(R.id.Restaurant_Name);

                        order= (TextView) itemView.findViewById(R.id.order_name);

                        date= (TextView) itemView.findViewById(R.id.date);

                        time= (TextView) itemView.findViewById(R.id.time);



                }
        }

}