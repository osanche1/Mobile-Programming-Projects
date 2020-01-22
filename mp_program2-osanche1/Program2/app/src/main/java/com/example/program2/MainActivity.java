package com.example.program2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity implements tipFragment.OnFragmentInteractionListener{

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if this a not new, then place add the fragment to the framelayout
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new tipFragment())
                    .commit();
        }
    }
    @Override
    public void onFragmentInteractionListener(String username)
    {

    }
}
