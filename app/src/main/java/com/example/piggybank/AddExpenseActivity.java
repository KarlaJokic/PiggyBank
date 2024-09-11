package com.example.piggybank;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class AddExpenseActivity extends AppCompatActivity {

    private PiggyBankViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        // Inicijalizacija ViewModel-a
        viewModel = new ViewModelProvider(this).get(PiggyBankViewModel.class);

        // Pronađi EditText-ove za unos imena i iznosa troška
        EditText expenseNameEditText = findViewById(R.id.edit_text_expense_name);
        EditText expenseAmountEditText = findViewById(R.id.edit_text_expense_amount);

        // Postavi listener za gumb za spremanje troška
        findViewById(R.id.button_save_expense).setOnClickListener(v -> {
            // Prikupljanje unesenih podataka iz EditText-ova
            String expenseName = expenseNameEditText.getText().toString().trim();
            String expenseAmountStr = expenseAmountEditText.getText().toString().trim();

            // Provjera jesu li sva polja popunjena
            if (!expenseName.isEmpty() && !expenseAmountStr.isEmpty()) {
                try {
                    double expenseAmount = Double.parseDouble(expenseAmountStr);

                    // Kreiraj novi Expense objekt
                    Expense newExpense = new Expense(0, expenseName, expenseAmount, "someDate");  // Zamijenite "someDate" stvarnim datumom ako je potrebno

                    // Pozovi metodu za spremanje troška u ViewModel
                    viewModel.addExpense(newExpense);

                    // Povratak na glavnu stranicu
                    Intent intent = new Intent(AddExpenseActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } catch (NumberFormatException e) {
                    Toast.makeText(AddExpenseActivity.this, "Please enter a valid number for the amount", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Prikaz poruke ako su polja prazna
                Toast.makeText(AddExpenseActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            }
        });

        // Postavi listener za gumb za povratak na glavnu stranicu
        findViewById(R.id.button_back_to_main).setOnClickListener(v -> {
            Intent intent = new Intent(AddExpenseActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();  // Zatvara trenutnu aktivnost
        });
    }
}
