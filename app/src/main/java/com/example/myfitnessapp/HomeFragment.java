package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment implements View.OnClickListener {
    ImageButton abs_img;
    ImageButton back_img;
    ImageButton arms_img;
    ImageButton legs_img;
    ImageButton chest_img;

    View view;

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        ((HomePage)getActivity()).setActionBarTitle("Home");

        abs_img = (ImageButton) view.findViewById(R.id.abs);
        abs_img.setOnClickListener(this);

        back_img = (ImageButton) view.findViewById(R.id.back);
        back_img.setOnClickListener(this);

        arms_img = (ImageButton) view.findViewById(R.id.arms);
        arms_img.setOnClickListener(this);

        legs_img = (ImageButton) view.findViewById(R.id.legs);
        legs_img.setOnClickListener(this);

        chest_img = (ImageButton) view.findViewById(R.id.chest);
        chest_img.setOnClickListener(this);

        return view;
    }

    private void openBackExercises() {
        Intent intent = new Intent(getActivity(), BackExercises.class);
        startActivity(intent);
    }


    private void openAbsExercises() {
        Intent intent = new Intent(getActivity(), AbsExercises.class);
        startActivity(intent);
        // getFragmentManager().beginTransaction().replace(R.id.fragment_container,new AbsExercisesFragment()).commit();

    }

    private void openArmsExercises() {
        Intent intent = new Intent(getActivity(), ArmsExercises.class);
        startActivity(intent);
    }

    private void openLegsExercises() {
        Intent intent = new Intent(getActivity(), LegsExercises.class);
        startActivity(intent);
    }

    private void openChestExercises() {
        Intent intent = new Intent(getActivity(), ChestExercises.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.abs){
            Toast.makeText(getActivity(), "Merge", Toast.LENGTH_LONG).show();
            openAbsExercises();
        }else if(view.getId() == R.id.back){
            Toast.makeText(getActivity(), "Merge", Toast.LENGTH_LONG).show();
            openBackExercises();
        }else if(view.getId()== R.id.arms){
            Toast.makeText(getActivity(), "Merge", Toast.LENGTH_LONG).show();
            openArmsExercises();
        }else if (view.getId() == R.id.legs){
            Toast.makeText(getActivity(), "Merge", Toast.LENGTH_LONG).show();
            openLegsExercises();
        }else if(view.getId() == R.id.chest){
            Toast.makeText(getActivity(), "Merge", Toast.LENGTH_LONG).show();
            openChestExercises();
        }
    }
}
