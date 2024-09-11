package com.example.piggybank;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class AddReminderActivity extends AppCompatActivity {

    private PiggyBankViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        // Inicijalizacija ViewModel-a
        viewModel = new ViewModelProvider(this).get(PiggyBankViewModel.class);

        // Prikupi EditText-ove
        EditText reminderTitleEditText = findViewById(R.id.edit_text_reminder_title);
        EditText reminderDescriptionEditText = findViewById(R.id.edit_text_reminder_description);

        // Postavi listener za gumb za spremanje podsjetnika
        Button saveReminderButton = findViewById(R.id.button_save_reminder);
        saveReminderButton.setOnClickListener(v -> {
            String reminderTitle = reminderTitleEditText.getText().toString().trim();
            String reminderDescription = reminderDescriptionEditText.getText().toString().trim();

            // Provjeri jesu li polja popunjena
            if (!reminderTitle.isEmpty() && !reminderDescription.isEmpty()) {
                // Kreiraj novi Reminder objekt
                Reminder newReminder = new Reminder(reminderTitle + ": " + reminderDescription);

                // Spremi podsjetnik u ViewModel
                viewModel.addReminder(newReminder);

                // Prikaz poruke o uspješnom spremanju
                Toast.makeText(AddReminderActivity.this, "Reminder saved successfully", Toast.LENGTH_SHORT).show();

                // Povratak na glavnu stranicu
                Intent intent = new Intent(AddReminderActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            } else {
                // Prikaz poruke ako su polja prazna
                Toast.makeText(AddReminderActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            }
        });

        // Postavi listener za gumb za povratak na glavnu stranicu
        Button backButton = findViewById(R.id.button_back_to_main);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(AddReminderActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();  // Zatvara trenutnu aktivnost
        });
    }
}
