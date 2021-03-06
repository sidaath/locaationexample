package com.example.studentlocations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.ChainHead;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Child> childList = new ArrayList<>();
    public static ArrayList<LatLng> locations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetPointsBackend bg = new GetPointsBackend(this);
        bg.execute();
    }

    public void viewLocations(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

    }
}