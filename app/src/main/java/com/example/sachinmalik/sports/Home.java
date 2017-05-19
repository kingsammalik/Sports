package com.example.sachinmalik.sports;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sachinmalik.sports.fragments.ItemFragment;

import mehdi.sakout.fancybuttons.FancyButton;

public class Home extends AppCompatActivity {
    FancyButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home=(FancyButton) findViewById(R.id.home_button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home.setVisibility(View.GONE);
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.your_placehodler, new ItemFragment());
                ft.commit();
            }
        });
    }
}
