package com.example.myfitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener{

    TextInputLayout name, surname, age, height, weight, gender, email;
    private FirebaseUser user;
    private DatabaseReference reference;

    private Button updateButton;
    String user_name,user_surname,user_age,user_height,user_weight, user_gender,user_email;
    String userID;
    //private ImageButton buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");

        userID = user.getUid();

        name = findViewById(R.id.updateName);
        surname = findViewById(R.id.updateSurname);
        age = findViewById(R.id.updateAge);
        height = findViewById(R.id.updateHeight);
        weight = findViewById(R.id.updateWeight);
        gender = findViewById(R.id.updateGender);
        email = findViewById(R.id.updateEmail);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    user_name = userProfile.name;
                    user_surname = userProfile.surname;
                    user_age = userProfile.age;
                    user_height = userProfile.height;
                    user_weight = userProfile.weight;
                    user_gender = userProfile.gender;
                    user_email = userProfile.email;

                    name.getEditText().setText(user_name);
                    surname.getEditText().setText(user_surname);
                    age.getEditText().setText(user_age);
                    height.getEditText().setText(user_height);
                    weight.getEditText().setText(user_weight);
                    gender.getEditText().setText(user_gender);
                    email.getEditText().setText(user_email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateProfile.this, "Something wrong happened!", Toast.LENGTH_LONG).show();

            }
        });

        updateButton = (Button) findViewById(R.id.updateButton);
        updateButton.setOnClickListener(this);

     //   buttonBack = (ImageButton) findViewById(R.id.imageButtonBack);
     //   buttonBack.setOnClickListener(this);

    }

    private void updateProfile() {
        if (isNameChanged() || isSurnameChanged() || isAgeChanged() || isHeightChanged() || isWeightChanged() || isGenderChanged() || isEmailChanged()) {
            Toast.makeText(this, "Data has been updated!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Data is the same and cannot be updated!", Toast.LENGTH_LONG).show();

        }
    }

    private boolean isEmailChanged() {
        if (!user_email.equals(email.getEditText().getText().toString())) {
            reference.child(userID).child("email").setValue(email.getEditText().getText().toString());
            user_email = email.getEditText().getText().toString();
            return true;

        } else {
            return false;
        }
    }

    private boolean isGenderChanged() {
        if (!user_gender.equals(gender.getEditText().getText().toString())) {
            reference.child(userID).child("gender").setValue(gender.getEditText().getText().toString());
            user_gender = gender.getEditText().getText().toString();
            return true;

        } else {
            return false;
        }
    }

    private boolean isWeightChanged() {
        if (!user_weight.equals(weight.getEditText().getText().toString())) {
            reference.child(userID).child("weight").setValue(weight.getEditText().getText().toString());
            user_weight = weight.getEditText().getText().toString();
            return true;

        } else {
            return false;
        }
    }

    private boolean isHeightChanged() {
        if (!user_height.equals(height.getEditText().getText().toString())) {
            reference.child(userID).child("height").setValue(height.getEditText().getText().toString());
            user_height = height.getEditText().getText().toString();
            return true;

        } else {
            return false;
        }
    }

    private boolean isAgeChanged() {
        if (!user_age.equals(age.getEditText().getText().toString())) {
            reference.child(userID).child("age").setValue(age.getEditText().getText().toString());
            user_age = age.getEditText().getText().toString();
            return true;

        } else {
            return false;
        }
    }

    private boolean isSurnameChanged() {
        if (!user_surname.equals(surname.getEditText().getText().toString())) {
            reference.child(userID).child("surname").setValue(surname.getEditText().getText().toString());
            user_surname = surname.getEditText().getText().toString();
            return true;

        } else {
            return false;
        }
    }

    private boolean isNameChanged() {

        if (!user_name.equals(name.getEditText().getText().toString())) {
            reference.child(userID).child("name").setValue(name.getEditText().getText().toString());
            user_name = name.getEditText().getText().toString();
            return true;

        } else {
            return false;
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.updateButton){
                updateProfile();
        }
    }
}