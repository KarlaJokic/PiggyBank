package com.example.piggybank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class AddExpenseActivity extends AppCompatActivity {

    private PiggyBankViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        viewModel = new ViewModelProvider(this).get(PiggyBankViewModel.class);

        findViewById(R.id.button_save_expense).setOnClickListener(v -> startActivity(new Intent(AddExpenseActivity.this, AddExpenseActivity.class)));
    }
}
