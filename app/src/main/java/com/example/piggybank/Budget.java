package com.example.piggybank;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Definiramo entitet za tablicu "budgets"
@Entity(tableName = "budgets")
public class Budget {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String category;
    private double amount;

    // Konstruktor
    public Budget(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    // Getteri i setteri
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
