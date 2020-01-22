package com.example.program4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

//referenced https://zocada.com/using-intents-extras-pass-data-activities-
// android-beginners-guide/ for this part

public class ModifyExpenses extends AppCompatActivity {
    private EditText note;
    private EditText category;
    private EditText name;
    private EditText amount;
    private EditText date;

    public static final String ID = "com.example.program4.ID";
    public static final String NAME = "com.example.program4.NAME";
    public static final String CATEGORY = "com.example.program4.CATEGORY";
    public static final String DATE = "com.example.program4.DATE";
    public static final String AMOUNT = "com.example.program4.AMOUNT";
    public static final String NOTE = "com.example.program4.NOTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_addition_activity);

        note = findViewById(R.id.noteField);
        category = findViewById(R.id.categoryField);
        name = findViewById(R.id.nameField);
        amount = findViewById(R.id.amountField);
        date = findViewById(R.id.dateField);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();

        if(intent.hasExtra(ID)){
            setTitle("Edit an Expense");
            note.setText(intent.getStringExtra(NOTE));
            category.setText(intent.getStringExtra(CATEGORY));
            name.setText(intent.getStringExtra(NAME));
            amount.setText(String.valueOf(intent.getFloatExtra(AMOUNT,1.0F)));                                                //Might cause issue late on :(
            date.setText(intent.getStringExtra(DATE));
        }else {
            setTitle("Add an Expense");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_expense:
                saveExpense();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.expense_addition,menu);
        return true;
    }


    private void saveExpense(){
        String tempName = name.getText().toString();
        String tempCategory = category.getText().toString();
        String tempDate = date.getText().toString();
        float tempAmount;
        try{tempAmount = Float.valueOf(amount.getText().toString());}catch (Exception a){tempAmount = (float)-9.99;}
        String tempNote = note.getText().toString();

        if(tempName.trim().isEmpty() || tempCategory.trim().isEmpty() || tempDate.trim().isEmpty() || tempAmount < 0.00){
            Toast.makeText(this,"Make sure you have a name, category, date and amount!",Toast.LENGTH_LONG).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(NAME, tempName);
        data.putExtra(CATEGORY, tempCategory);
        data.putExtra(DATE, tempDate);
        data.putExtra(AMOUNT, tempAmount);
        data.putExtra(NOTE, tempNote);

        Long id = getIntent().getLongExtra(ID,-1);
        if(id != -1){
            data.putExtra(ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

}

