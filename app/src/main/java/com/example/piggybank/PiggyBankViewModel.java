package com.example.piggybank;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class PiggyBankViewModel extends ViewModel {
    private MutableLiveData<List<Expense>> expenses;
    private MutableLiveData<Map<String, Double>> budget;
    private MutableLiveData<List<String>> reminders;

    public PiggyBankViewModel() {
        expenses = new MutableLiveData<>(new ArrayList<>());
        budget = new MutableLiveData<>(new HashMap<>());
        reminders = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<List<Expense>> getExpenses() {
        return expenses;
    }

    public LiveData<Map<String, Double>> getBudget() {
        return budget;
    }

    public LiveData<List<String>> getReminders() {
        return reminders;
    }

    public void addExpense(Expense expense) {
        List<Expense> currentExpenses = expenses.getValue();
        currentExpenses.add(expense);
        expenses.setValue(currentExpenses);
    }

    public void setBudget(String category, double amount) {
        Map<String, Double> currentBudget = budget.getValue();
        currentBudget.put(category, amount);
        budget.setValue(currentBudget);
    }

    public void addReminder(String reminder) {
        List<String> currentReminders = reminders.getValue();
        currentReminders.add(reminder);
        reminders.setValue(currentReminders);
    }
}
