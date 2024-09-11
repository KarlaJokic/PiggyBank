package com.example.piggybank;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface ReminderDao {
    @Insert
    void insert(Reminder reminder);

    @Delete
    void delete(Reminder reminder);

    @Query("SELECT * FROM reminders")  // Provjerite naziv tablice ovdje
    LiveData<List<Reminder>> getAllReminders();
}
