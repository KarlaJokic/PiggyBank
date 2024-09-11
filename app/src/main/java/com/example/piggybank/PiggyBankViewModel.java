package com.example.piggybank;

import android.app.Application;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatDelegate;
import android.util.Log;

import java.util.List;

public class PiggyBankViewModel extends AndroidViewModel {

    private final PiggyBankRepository repository;
    private final LiveData<List<Expense>> allExpenses;

    public PiggyBankViewModel(@NonNull Application application) {
        super(application);
        repository = new PiggyBankRepository(application);
        allExpenses = repository.getAllExpenses();

    }

    // Getter metode za LiveData objekti
    public LiveData<List<Expense>> getExpenses() {
        return allExpenses;
    }

    // Metode za umetanje i ažuriranje podataka
    public void addExpense(Expense expense) {
        repository.insertExpense(expense);
    }

    public void clearAllExpenses() {
        repository.deleteAllExpenses();
    }

    public void addReminder(Reminder reminder) {
        repository.insertReminder(reminder);
    }

    public void setBudget(Budget budget) {
        repository.insertOrUpdateBudget(budget);
    }

}
