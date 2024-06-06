package com.example.piggybank;

import junit.framework.TestCase;

public class PiggyBankViewModelTest extends TestCase {
    public void testAddExpense(){
        PiggyBankViewModel viewModel = new PiggyBankViewModel();
        Expense expense = new Expense(1, "Namirnice", 50.0, "2024-05-25");

        assertEquals(0, viewModel.getExpenses().getValue().size());

        viewModel.addExpense(expense);

        assertEquals(1, viewModel.getExpenses().getValue().size());
        assertEquals(expense, viewModel.getExpenses().getValue().get(0));
    }

    public void testSetBudget(){
        PiggyBankViewModel viewModel = new PiggyBankViewModel();

        assertEquals(0, viewModel.getBudget().getValue().size());

        viewModel.setBudget("Namirnice", 100.0);

        assertTrue(viewModel.getBudget().getValue().containsKey("Namirnice"));
        assertEquals(100.0, viewModel.getBudget().getValue().get("Namirnice"));
    }

    public void testAddReminder() {
        PiggyBankViewModel viewModel = new PiggyBankViewModel();

        assertEquals(0, viewModel.getReminders().getValue().size());

        viewModel.addReminder("Pay rent");

        assertTrue(viewModel.getReminders().getValue().contains("Pay rent"));
    }

}
