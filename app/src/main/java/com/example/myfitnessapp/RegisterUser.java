package com.example.myfitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, registerUser;
    private EditText editTextName, editTextSurname, editTextAge, editTextHeight, editTextWeight, editTextGender, editTextEmail,editTextPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth=FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.name);
        editTextSurname = (EditText) findViewById(R.id.surname);
        editTextAge = (EditText) findViewById(R.id.age);
        editTextHeight = (EditText) findViewById(R.id.height);
        editTextWeight = (EditText) findViewById(R.id.weight);
        editTextGender = (EditText) findViewById(R.id.gender);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.registerUser:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String surname = editTextSurname.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String height = editTextHeight.getText().toString().trim();
        String weight = editTextWeight.getText().toString().trim();
        String gender = editTextGender.getText().toString().trim();

        if(name.isEmpty()){
            editTextName.setError("Name is required");
            editTextName.requestFocus();
            return;
        }

        if(surname.isEmpty()){
            editTextSurname.setError("Surname is required");
            editTextSurname.requestFocus();
            return;
        }

        if(age.isEmpty()){
            editTextAge.setError("Age is required");
            editTextAge.requestFocus();
            return;
        }

        if(height.isEmpty()){
            editTextHeight.setError("Height is required");
            editTextHeight.requestFocus();
            return;
        }

        if( weight.isEmpty()){
            editTextWeight.setError("Weight is required");
            editTextWeight.requestFocus();
            return;
        }

        if(gender.isEmpty()){
            editTextGender.setError("Gender is required");
            editTextGender.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length()<6){
            editTextPassword.setError("Password needs to be min 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name,surname,age,height,weight,gender,email);

                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterUser.this,"User has been registered succesfully!", Toast.LENGTH_LONG).show();

                                        progressBar.setVisibility(View.GONE);
                                        //redirect to Login
                                    }else{
                                        Toast.makeText(RegisterUser.this, "Failed to register. Try again!",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegisterUser.this, "Failed to register. Try again!",Toast.LENGTH_LONG).show();
                              progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}