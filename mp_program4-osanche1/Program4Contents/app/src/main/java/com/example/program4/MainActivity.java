package com.example.program4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int EXPENSE_ADDITION = 1;
    public static final int EXPENSE_EDIT = 2;
    private ListView myListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton newExpenseBttn = findViewById(R.id.newExpense);
        newExpenseBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ModifyExpenses.class);
                startActivityForResult(intent, EXPENSE_ADDITION);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final Adapter adapter = new Adapter();
        recyclerView.setAdapter(adapter);


        myListView = ViewModelProviders.of(this).get(ListView.class);
        myListView.getAllExpenses().observe(this, new Observer<List<Expense>>() {
            @Override
            public void onChanged(@Nullable List<Expense> expenses) {
                adapter.setExpenses(expenses);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                myListView.deleteExpense(adapter.getExpenseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Expense Deleted", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new Adapter.onItemClickListener() {
            @Override
            public void onItemClick(Expense expense) {
                Intent intent = new Intent(MainActivity.this,ModifyExpenses.class);
                intent.putExtra(ModifyExpenses.ID, expense.getID());
                intent.putExtra(ModifyExpenses.NAME,expense.getName());
                intent.putExtra(ModifyExpenses.AMOUNT,expense.getAmount());
                intent.putExtra(ModifyExpenses.CATEGORY,expense.getCategory());
                intent.putExtra(ModifyExpenses.DATE,expense.getDate());
                intent.putExtra(ModifyExpenses.NOTE,expense.getNote());
                startActivityForResult(intent,EXPENSE_EDIT);
            }
        });
    }

    @Override
    protected void onActivityResult(int asking, int received, @Nullable Intent data) {
        super.onActivityResult(asking, received, data);

        if(asking == EXPENSE_ADDITION && received == RESULT_OK){
            String name = data.getStringExtra(ModifyExpenses.NAME);
            String category = data.getStringExtra(ModifyExpenses.CATEGORY);
            String date = data.getStringExtra(ModifyExpenses.DATE);
            String note = data.getStringExtra(ModifyExpenses.NOTE);
            float amount = data.getFloatExtra(ModifyExpenses.AMOUNT, 1);

            Expense expense = new Expense(name,category,date,amount,note);
            myListView.insertExpense(expense);

            Toast.makeText(this,"Expense Saved",Toast.LENGTH_LONG).show();
        }else if(asking == EXPENSE_EDIT && received == RESULT_OK) {
            long id = data.getLongExtra(ModifyExpenses.ID, -1);

            if(id == -1){
                Toast.makeText(this, "Expense can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = data.getStringExtra(ModifyExpenses.NAME);
            String category = data.getStringExtra(ModifyExpenses.CATEGORY);
            String date = data.getStringExtra(ModifyExpenses.DATE);
            String note = data.getStringExtra(ModifyExpenses.NOTE);
            float amount = data.getFloatExtra(ModifyExpenses.AMOUNT, 1);

            Expense expense = new Expense(name,category,date,amount,note);
            expense.setID(id);
            myListView.updateExpense(expense);
            Toast.makeText(this, "Expense updated", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Expense not Saved",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_expenses:
                myListView.deleteAll();
                Toast.makeText(this, "All expenses deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
