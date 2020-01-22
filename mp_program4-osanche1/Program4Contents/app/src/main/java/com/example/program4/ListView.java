package com.example.program4;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ListView extends AndroidViewModel {
    private ExpenseRepo repo;
    private LiveData<List<Expense>> allExpenses;

    public ListView(@NonNull Application application){
        super(application);
        repo = new ExpenseRepo(application);
        allExpenses = repo.getAllExpenses();
    }

    public void deleteAll(){
        repo.deleteAllExpenses();
    }

    public void updateExpense(Expense expense){
        repo.update(expense);
    }

    public void deleteExpense(Expense expense){
        repo.delete(expense);
    }

    public void insertExpense(Expense expense){
        repo.insert(expense);
    }

    public LiveData<List<Expense>> getAllExpenses(){
        return allExpenses;
    }
}
