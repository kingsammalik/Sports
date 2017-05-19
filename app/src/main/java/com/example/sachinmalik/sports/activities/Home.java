package com.example.sachinmalik.sports.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.sachinmalik.sports.R;
import com.example.sachinmalik.sports.fragments.ItemDetail;
import com.example.sachinmalik.sports.fragments.ItemFragment;

import mehdi.sakout.fancybuttons.FancyButton;

public class Home extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {
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
                Fragment newFragment = new ItemFragment();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.your_placehodler, newFragment,"items");
                ft.commit();
            }
        });
    }

    @Override
    public void onListFragmentInteraction(int position) {
        Log.e("itemfrgament","item clicked with position "+position);
        FragmentManager fm = getSupportFragmentManager();
        Fragment newFragment = new ItemDetail();
        FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.your_placehodler, newFragment,"detail");
            transaction.setCustomAnimations(R.anim.slide_up,R.anim.slide_down);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.addToBackStack("detail");
            transaction.commit();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        //transaction.detach(newFragment);


// Commit the transaction

        fm.executePendingTransactions();


    }

}
