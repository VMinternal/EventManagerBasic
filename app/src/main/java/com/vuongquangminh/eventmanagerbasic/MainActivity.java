package com.vuongquangminh.eventmanagerbasic;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etEventName, etLocation, etDate, etReporter, etDescription;
    private RadioGroup rgRiskAssessment;
    private RadioButton rbRiskYes;
    private MaterialButton btnSaveEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        // Open the DatePicker when clicking the "Select Date" field.
        etDate.setOnClickListener(v -> showDatePicker());

        // Handle the "Create Event" button click event.
        btnSaveEvent.setOnClickListener(v -> processFormSubmission());
    }

    private void initViews() {
        etEventName = findViewById(R.id.etEventName);
        etLocation = findViewById(R.id.etLocation);
        etDate = findViewById(R.id.etDate);
        etReporter = findViewById(R.id.etReporter);
        etDescription = findViewById(R.id.etDescription);
        rgRiskAssessment = findViewById(R.id.rgRiskAssessment);
        rbRiskYes = findViewById(R.id.rbRiskYes);
        btnSaveEvent = findViewById(R.id.btnSaveEvent);
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%d", selectedDay, selectedMonth + 1, selectedYear);
                    etDate.setText(formattedDate);
                }, year, month, day);

        datePickerDialog.show();
    }

    private void processFormSubmission() {
        String name = etEventName.getText().toString().trim();
        String location = etLocation.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String reporter = etReporter.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        boolean requiresRisk = rbRiskYes.isChecked();

        // Validation: Check mandatory fields
        if (name.isEmpty()) {
            etEventName.setError("The event name cannot be left blank!");
            etEventName.requestFocus();
            return;
        }

        if (location.isEmpty()) {
            etLocation.setError("The location field cannot be left blank!");
            etLocation.requestFocus();
            return;
        }

        if (date.isEmpty()) {
            etDate.setError("Please select the date of the event!");
            etDate.requestFocus();
            return;
        }

        if (reporter.isEmpty()) {
            etReporter.setError("The reporter's name cannot be left blank!");
            etReporter.requestFocus();
            return;
        }

        // Initialize an Event object
        Event event = new Event(name, location, date, "", requiresRisk, reporter, description);

        // Display confirmation dialog
        showConfirmationDialog(event);
    }

    private void showConfirmationDialog(Event event) {
        String message = "Please confirm the event details.:\n\n" +
                "• Event name: " + event.getName() + "\n" +
                "• Location: " + event.getLocation() + "\n" +
                "• Date of the event: " + event.getDate() + "\n" +
                "• Annunciator: " + event.getReporter() + "\n" +
                "• A risk assessment is required.: " + (event.isRequiresRiskAssessment() ? "Yes" : "No") + "\n" +
                "• Describe: " + (event.getDescription().isEmpty() ? "(Do not have)" : event.getDescription());

        new MaterialAlertDialogBuilder(this)
                .setTitle("Event Confirmation")
                .setMessage(message)
                .setPositiveButton("Confirm", (dialog, which) -> {
                    Toast.makeText(MainActivity.this, "Event saved successfully!", Toast.LENGTH_LONG).show();
                    clearForm();
                })
                .setNegativeButton("Edit", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void clearForm() {
        etEventName.setText("");
        etLocation.setText("");
        etDate.setText("");
        etReporter.setText("");
        etDescription.setText("");
        rgRiskAssessment.check(R.id.rbRiskNo);
        etEventName.requestFocus();
    }
}