package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {


    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth mAuth;

    EditText editText1,editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);




        Button button= (Button) findViewById(R.id.register);

        editText1=(EditText) findViewById(R.id.email1);

        editText2=(EditText) findViewById(R.id.pass1);


        mAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail= editText1.getText().toString().trim();
                String pass= editText2.getText().toString().trim();

                registerUser(mail,pass);


            }
        });



    }

    public void registerUser(String email, String password){

        if (email.isEmpty()) {
            editText1.setError("Email is required");
            editText1.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editText1.setError("Please enter a valid email");
            editText1.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editText2.setError("Password is required");
            editText2.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editText2.setError("Minimum lenght of password should be 6");
            editText2.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    mAuth=FirebaseAuth.getInstance();

                    FirebaseUser firebaseUser= mAuth.getCurrentUser();

                    rootNode = FirebaseDatabase.getInstance();

                    reference = rootNode.getReference("Users");

                    reference.child(firebaseUser.getUid().toString());



                    reference = rootNode.getReference("Users/"+firebaseUser.getUid().toString());


                    reference.child("Email").setValue(firebaseUser.getEmail());

                    reference.child("Total orders").setValue("0");






                    Toast.makeText(getApplicationContext(),"User Registered Succesful",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();

                }

                else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "This Email is already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }





    }
