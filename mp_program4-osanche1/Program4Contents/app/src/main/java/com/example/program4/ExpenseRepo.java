package com.example.program4;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpenseRepo{
    private ExpenseDAO expenseDao;
    private LiveData<List<Expense>> allExpenses;


    public void insert(Expense expense){
        new Insert(expenseDao).execute(expense);
    }

    public void update(Expense expense){
        new Update(expenseDao).execute(expense);
    }

    public void delete(Expense expense){
        new Delete(expenseDao).execute(expense);
    }

    public void deleteAllExpenses(){
        new DeleteAll(expenseDao).execute();
    }

    public LiveData<List<Expense>> getAllExpenses(){
        return allExpenses;
    }

    public ExpenseRepo(Application application){
        ExpenseDatabase database = ExpenseDatabase.getInstance((application));
        expenseDao = database.expenseDao();
        allExpenses = expenseDao.getAllExpenses();
    }

    private static class Insert extends AsyncTask<Expense, Void,Void>{
        private ExpenseDAO expenseDao;
        private Insert(ExpenseDAO expenseDao){
            this.expenseDao = expenseDao;
        }
        @Override
        protected Void doInBackground(Expense... expenses){
            expenseDao.insert(expenses[0]);
            return null;
        }
    }

    private static class DeleteAll extends AsyncTask<Void, Void,Void>{
        private ExpenseDAO expenseDao;
        private DeleteAll(ExpenseDAO expenseDao){
            this.expenseDao = expenseDao;
        }
        @Override
        protected Void doInBackground(Void... expenses){
            expenseDao.deleteAllExpenses();
            return null;
        }
    }

    private static class Delete extends AsyncTask<Expense, Void,Void>{
        private ExpenseDAO expenseDao;
        private Delete(ExpenseDAO expenseDao){
            this.expenseDao = expenseDao;
        }
        @Override
        protected Void doInBackground(Expense... expenses){
            expenseDao.delete(expenses[0]);
            return null;
        }
    }

    private static class Update extends AsyncTask<Expense, Void,Void>{
        private ExpenseDAO expenseDao;
        private Update(ExpenseDAO expenseDao){
            this.expenseDao = expenseDao;
        }
        @Override
        protected Void doInBackground(Expense... expenses){
            expenseDao.update(expenses[0]);
            return null;
        }
    }

}

