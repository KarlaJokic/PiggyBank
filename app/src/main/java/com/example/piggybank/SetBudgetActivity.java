package com.example.piggybank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
public class SetBudgetActivity extends AppCompatActivity {
    private PiggyBankViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_budget);

        viewModel = new ViewModelProvider(this).get(PiggyBankViewModel.class);

        findViewById(R.id.button_save_budget).setOnClickListener(v -> startActivity(new Intent(SetBudgetActivity.this, SetBudgetActivity.class)));
    }


}
