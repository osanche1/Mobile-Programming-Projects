package com.example.program4;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
//referenced http://thetechnocafe.com/how-to-use-room-in-android-all-you-need
// -to-know-to-get-started/ fot this file

@Dao
public interface ExpenseDAO{

    @Query("DELETE FROM expense_table")
    void deleteAllExpenses();

    @Query("SELECT * FROM expense_table")
    LiveData<List<Expense>> getAllExpenses();

    @Update
    void update(Expense expense);

    @Insert
    void insert(Expense expense);

    @Delete
    void delete(Expense expense);
}
