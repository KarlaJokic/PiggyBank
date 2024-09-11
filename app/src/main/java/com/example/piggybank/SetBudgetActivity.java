package com.example.piggybank;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class SetBudgetActivity extends AppCompatActivity {

    private PiggyBankViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_budget);

        // Inicijalizacija ViewModel-a
        viewModel = new ViewModelProvider(this).get(PiggyBankViewModel.class);

        // Prikupi EditText-ove
        EditText budgetCategoryEditText = findViewById(R.id.edit_text_budget_category);
        EditText budgetAmountEditText = findViewById(R.id.edit_text_budget_amount);

        // Postavi listener za gumb za spremanje budžeta
        Button saveBudgetButton = findViewById(R.id.button_save_budget);
        saveBudgetButton.setOnClickListener(v -> {
            String category = budgetCategoryEditText.getText().toString();
            String amountString = budgetAmountEditText.getText().toString();

            // Provjeri jesu li polja popunjena
            if (!category.isEmpty() && !amountString.isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountString);

                    // Kreiraj instancu Budget objekta
                    Budget budget = new Budget(category, amount); // Kreiraj novi Budget objekt
                    viewModel.setBudget(budget);  // Poziv metode sa Budget objektom

                    // Povratak na glavnu stranicu
                    Intent intent = new Intent(SetBudgetActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } catch (NumberFormatException e) {
                    // Prikaz poruke ako iznos nije valjan
                    Toast.makeText(SetBudgetActivity.this, "Invalid amount", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Prikaz poruke ako su polja prazna
                Toast.makeText(SetBudgetActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            }
        });

        // Postavi listener za gumb za povratak na glavnu stranicu
        Button backButton = findViewById(R.id.button_back_to_main);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(SetBudgetActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();  // Zatvara trenutnu aktivnost
        });
    }
}
