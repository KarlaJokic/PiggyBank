package com.example.piggybank;

        import android.app.Application;
        import androidx.lifecycle.LiveData;

        import java.util.List;

public class PiggyBankRepository {
    private final ExpenseDao expenseDao;
    private final ReminderDao reminderDao;
    private final BudgetDao budgetDao;  // Dodano BudgetDao
    private final LiveData<List<Expense>> allExpenses;
    private final LiveData<List<Reminder>> allReminders;
    private final LiveData<List<Budget>> allBudgets;  // Dodano za budžete

    public PiggyBankRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        expenseDao = db.expenseDao();
        reminderDao = db.reminderDao();
        budgetDao = db.budgetDao();  // Inicijaliziramo BudgetDao

        allExpenses = expenseDao.getAllExpenses();
        allReminders = reminderDao.getAllReminders();
        allBudgets = budgetDao.getAllBudgets();  // Dohvaćanje budžeta
    }

    public LiveData<List<Expense>> getAllExpenses() {
        return allExpenses;
    }

    public void deleteAllExpenses() {
        AppDatabase.databaseWriteExecutor.execute(() -> expenseDao.deleteAll());
    }

    public LiveData<List<Reminder>> getAllReminders() {
        return allReminders;
    }

    public LiveData<List<Budget>> getAllBudgets() {
        return allBudgets;
    }

    public void insertExpense(Expense expense) {
        AppDatabase.databaseWriteExecutor.execute(() -> expenseDao.insert(expense));
    }

    public void insertReminder(Reminder reminder) {
        AppDatabase.databaseWriteExecutor.execute(() -> reminderDao.insert(reminder));
    }

    public void deleteReminder(Reminder reminder) {
        AppDatabase.databaseWriteExecutor.execute(() -> reminderDao.delete(reminder));
    }

    public void insertOrUpdateBudget(Budget budget) {
        AppDatabase.databaseWriteExecutor.execute(() -> budgetDao.insertOrUpdate(budget));
    }

    public void deleteBudget(int id) {
        AppDatabase.databaseWriteExecutor.execute(() -> budgetDao.deleteById(id));
    }
}