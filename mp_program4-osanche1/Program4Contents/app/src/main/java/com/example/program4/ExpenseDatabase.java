package com.example.program4;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Expense.class},version = 1)
public abstract class ExpenseDatabase extends RoomDatabase{

    public abstract ExpenseDAO expenseDao();
    private static ExpenseDatabase myDatabase;

    public static synchronized ExpenseDatabase getInstance (Context context){
        if(myDatabase == null){
            myDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    ExpenseDatabase.class,"expense_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return myDatabase;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new Populate(myDatabase).execute();
        }
    };

    private static class Populate extends AsyncTask<Void,Void,Void>{
        private ExpenseDAO expenseDao;
        private Populate(ExpenseDatabase db){
            expenseDao = db.expenseDao();
        }
        @Override
        protected Void doInBackground(Void... voids){
            return null;
        }
    }
}