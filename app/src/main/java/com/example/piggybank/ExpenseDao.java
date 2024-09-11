package com.example.piggybank;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExpenseDao {
    @Insert
    void insert(Expense expense);

    @Query("SELECT * FROM expenses")
    LiveData<List<Expense>> getAllExpenses();

    @Query("DELETE FROM expenses")
    void deleteAll();
}


