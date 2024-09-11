package com.example.piggybank;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

@Dao
public interface BudgetDao {
    // Umetanje novog budžeta
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(Budget budget);

    // Dohvaćanje svih budžeta
    @Query("SELECT * FROM budgets")
    LiveData<List<Budget>> getAllBudgets();

    // Brisanje određenog budžeta po ID-u
    @Query("DELETE FROM budgets WHERE id = :id")
    void deleteById(int id);

}
