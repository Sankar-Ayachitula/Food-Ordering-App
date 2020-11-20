package com.example.foodorderingapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderActivity extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    public String s1;
    public String s2;

    public FirebaseAuth mAuth;

    int total_orders=0;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.order);



        TextView textView1=(TextView) findViewById(R.id.Restaurant_Name);
        TextView textView2=(TextView) findViewById(R.id.Restaurant_Details);

        Bundle bundle=getIntent().getExtras();
        if(bundle!= null) {
            s1=bundle.getString("RestaurantName");
            s2=bundle.getString("RestaurantDetails");
            textView1.setText(s1);
            textView2.setText(s2);
        }

        Button button=  (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootNode = FirebaseDatabase.getInstance();

                reference = rootNode.getReference("Users");

                EditText day= (EditText) findViewById(R.id.day);
                EditText month= (EditText) findViewById(R.id.month);
                EditText year= (EditText) findViewById(R.id.year);

                EditText hour= (EditText) findViewById(R.id.hour);
                EditText min= (EditText) findViewById(R.id.min);

                mAuth=FirebaseAuth.getInstance();
                final FirebaseUser firebaseUser= mAuth.getCurrentUser();



                Log.v("OrderActivity.java",firebaseUser.getEmail()+"Hello How r u "+firebaseUser.getUid());

                //Here onwards



                reference.child(firebaseUser.getUid().toString());

                ValueEventListener postListener=new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        total_orders = snapshot.child(firebaseUser.getUid().toString()).child("Total orders").getValue(Integer.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };



                total_orders+=1;


                reference = rootNode.getReference("Users/"+firebaseUser.getUid().toString());




                reference.child("Total orders").setValue(""+total_orders);


                reference = rootNode.getReference("Users/"+firebaseUser.getUid().toString()+"/Orders");

                reference.child("Order "+total_orders);

                reference = rootNode.getReference("Users/"+firebaseUser.getUid().toString()+"/Orders/"+"Order "+total_orders);

                reference.child("Restaurant").setValue(s1.trim());

                reference.child("Order Name").setValue(s2.trim());

                reference.child("Time").setValue(hour.getText()+":"+min.getText());
                reference.child("Date").setValue(day.getText()+"-"+month.getText()+"-"+year.getText());


                TextView textView=(TextView) findViewById(R.id.order_status);
                textView.setText("Status: Order Placed");
            }
        });

    }






}
