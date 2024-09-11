package com.example.piggybank;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TransactionAdapter transactionAdapter;
    private PiggyBankViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        viewModel = new ViewModelProvider(this).get(PiggyBankViewModel.class);

        recyclerView = findViewById(R.id.recycler_view_transactions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        transactionAdapter = new TransactionAdapter(new ArrayList<>());
        recyclerView.setAdapter(transactionAdapter);

        viewModel.getExpenses().observe(this, expenses -> {
            Log.d("HistoryActivity", "Expenses updated: " + expenses.toString());
            transactionAdapter.setExpenses(expenses);
        });

        Button clearAllButton = findViewById(R.id.button_clear_all);
        clearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.clearAllExpenses();  // Poziva novu metodu u ViewModel-u
            }
        });
    }
}