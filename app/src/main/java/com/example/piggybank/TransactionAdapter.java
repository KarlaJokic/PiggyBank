package com.example.piggybank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private List<Expense> expenses;

    public TransactionAdapter(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.categoryTextView.setText(expense.getCategory());
        holder.amountTextView.setText(String.format("$%.2f", expense.getAmount()));

        // Formatiranje datuma
        holder.dateTextView.setText(formatDate(expense.getDate()));
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
        notifyDataSetChanged();
    }

    private String formatDate(String date) {
        try {
            // Pretpostavljamo da je originalni format datuma "yyyy-MM-dd"
            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat desiredFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            return desiredFormat.format(originalFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid date"; // Vraćamo ovu poruku ako je datum neispravan
        }
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView categoryTextView;
        TextView amountTextView;
        TextView dateTextView;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTextView = itemView.findViewById(R.id.text_view_category);
            amountTextView = itemView.findViewById(R.id.text_view_amount);
            dateTextView = itemView.findViewById(R.id.text_view_date);
        }
    }
}
