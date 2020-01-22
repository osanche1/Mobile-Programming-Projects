package com.example.program5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private Board myBoard;
    private TTTEngine myEngine;

    private void newGame() {
        myEngine.newGame();
        myBoard.invalidate();
    }

    public void gameEnded(char c) {
        String msg = (c == 'T') ? "It's a Tie!" : c + " wins!";

        new AlertDialog.Builder(this).setTitle("Game Over").
                setMessage(msg).
                setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        newGame();
                    }
                }).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myBoard = findViewById(R.id.myBoard);
        myEngine = new TTTEngine();
        myBoard.setEngine(myEngine);
        myBoard.setMainActivity(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_new_game) {
            newGame();
        }
        return super.onOptionsItemSelected(item);
    }




}
