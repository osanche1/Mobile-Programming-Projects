package com.example.program1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements NameFragment.OnFragmentInteractionListener {

    FragmentManager fragmentManager = getSupportFragmentManager();
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // adding fragment to main activity
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container, new NameFragment()).commit();
    }

    @Override
    public void onFragmentInteraction(String username)
    {

    }

}
