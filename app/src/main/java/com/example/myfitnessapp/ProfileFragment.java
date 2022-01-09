package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    View view;
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    private Button updateProfile;

    public ProfileFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_profile, container, false);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");

        updateProfile = (Button) view.findViewById(R.id.updateProfile);
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),UpdateProfile.class);
                startActivity(intent);
            }
        });

        userID = user.getUid();

        final TextView nameProfileTextView = (TextView) view.findViewById(R.id.nameProfile);
        final TextView surnameProfileTextView = (TextView) view.findViewById(R.id.surnameProfile);
        final TextView ageProfileTextView = (TextView) view.findViewById(R.id.ageProfile);
        final TextView heightProfileTextView = (TextView) view.findViewById(R.id.heightProfile);
        final TextView weightProfileTextView = (TextView) view.findViewById(R.id.weightProfile);
        final TextView genderProfileTextView = (TextView) view.findViewById(R.id.genderProfile);
        final TextView emailProfileTextView = (TextView) view.findViewById(R.id.emailProfile);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile= snapshot.getValue(User.class);

                if(userProfile != null){
                    String name = userProfile.name;
                    String surname = userProfile.surname;
                    String age = userProfile.age;
                    String height = userProfile.height;
                    String weight = userProfile.weight;
                    String gender = userProfile.gender;
                    String email = userProfile.email;

                    nameProfileTextView.setText(name);
                    surnameProfileTextView.setText(surname);
                    ageProfileTextView.setText(age);
                    heightProfileTextView.setText(height);
                    weightProfileTextView.setText(weight);
                    genderProfileTextView.setText(gender);
                    emailProfileTextView.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Something wrong happened!", Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }
}
