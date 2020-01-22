package com.example.program6_battlebots;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class startUp extends AppCompatActivity {
    //public static final String EXTRA_MESSAGE = "com.example.program6_battlebots.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_screen);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        EditText powerText = (EditText) findViewById(R.id.power_left);
        EditText armorText = (EditText) findViewById(R.id.armor_middle);
        EditText scanText = (EditText) findViewById(R.id.scan_right);
        EditText addressText = (EditText) findViewById(R.id.address);
        int power = Integer.parseInt(powerText.getText().toString());
        int armor = Integer.parseInt(armorText.getText().toString());
        int scan = Integer.parseInt(scanText.getText().toString());
        String address = addressText.getText().toString();
        int total = power + armor + scan;
        if(total <= 5 && power >= 0 && armor >= 0 && scan >= 0) {
            intent.putExtra("muh power", power);
            intent.putExtra("muh armor", armor);
            intent.putExtra("muh scan", scan);
            intent.putExtra("muh address" ,address);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Verify all values are greater than zero and total no more than 5.",Toast.LENGTH_SHORT).show();
        }
    }

}
