package com.example.inventorymanager.ui.fragments;

import com.example.inventorymanager.R;
import com.example.inventorymanager.model.LogEntity;
import com.example.inventorymanager.viewmodel.LogViewModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormFragment extends Fragment {
    private EditText etComponentName, etTakenBy, etBurrowedDate, etReturnDate;
    private Button btnAddLog;
    private LogViewModel logViewModel;

    public FormFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);
        etComponentName = view.findViewById(R.id.etComponentName);
        etTakenBy = view.findViewById(R.id.etTakenBy);
        etBurrowedDate = view.findViewById(R.id.etBurrowedDate);
        etReturnDate = view.findViewById(R.id.etReturnDate);
        btnAddLog = view.findViewById(R.id.btnAddLog);

        etBurrowedDate.setText(new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date()));
        etReturnDate.setText(new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date()));

        logViewModel = new ViewModelProvider(requireActivity()).get(LogViewModel.class);

        btnAddLog.setOnClickListener(v -> {
            String componentName = etComponentName.getText().toString().trim();
            String takenBy = etTakenBy.getText().toString().trim();
            String burrowedDate = etBurrowedDate.getText().toString().trim();
            String returnDate = etReturnDate.getText().toString().trim();

            if (componentName.isEmpty()) {
                etComponentName.setError("Component Name is Required");
                etComponentName.requestFocus();
                return;
            }
            if (takenBy.isEmpty()) {
                etTakenBy.setError("Name is Required");
                etTakenBy.requestFocus();
                return;
            }
            if (burrowedDate.isEmpty()) {
                etBurrowedDate.setError("Burrowed Date is Required");
                etBurrowedDate.requestFocus();
                return;
            }
            if (!isValidDate(burrowedDate)) {
                etBurrowedDate.setError("Enter Valid Date YYYY/MM/DD");
                etBurrowedDate.requestFocus();
                return;
            }
            if (returnDate.isEmpty()) {
                etReturnDate.setError("Return Date is Required");
                etReturnDate.requestFocus();
                return;
            }
            if (!isValidDate(returnDate)) {
                etReturnDate.setError("Enter Valid Date YYYY/MM/DD");
                etReturnDate.requestFocus();
                return;
            }
            if (returnDate.compareTo(burrowedDate) < 0) {
                etReturnDate.setError("Return date must be after burrowed date");
                etReturnDate.requestFocus();
                return;
            }

            final LogEntity log = new LogEntity(componentName, takenBy, burrowedDate, returnDate);
            logViewModel.insert(log);
            clearForm();
        });

        return view;
    }

    private boolean isValidDate(String dateStr) {
        android.icu.text.SimpleDateFormat sdf = new android.icu.text.SimpleDateFormat("yyyy/mm/dd", Locale.US);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private void clearForm() {
        etComponentName.setText("");
        etTakenBy.setText("");
    }
}
