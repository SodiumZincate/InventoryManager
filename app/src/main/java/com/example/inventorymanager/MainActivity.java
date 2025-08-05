package com.example.inventorymanager;

import com.example.inventorymanager.model.LogDatabase;
import com.example.inventorymanager.model.LogEntity;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private EditText etComponentName, etTakenBy, etBurrowedDate, etReturnDate;
    private Button btnAddLog;

    private RecyclerView recyclerViewLogs;
    private LogAdapter logAdapter;
    private ArrayList<LogEntity> logList;
    private LogDatabase logDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etComponentName = findViewById(R.id.etComponentName);
        etTakenBy = findViewById(R.id.etTakenBy);
        etBurrowedDate = findViewById(R.id.etBurrowedDate);
        etReturnDate = findViewById(R.id.etReturnDate);
        btnAddLog = findViewById(R.id.btnAddLog);
        recyclerViewLogs = findViewById(R.id.recyclerViewLogs);

        logDatabase = LogDatabase.getInstance(this);
        logList = new ArrayList<>();

        LogAdapter.OnDeleteClickListener deleteClickListener = log -> {
            try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
                executor.execute(() -> {
                    logDatabase.logDao().delete(log);
                    runOnUiThread(this::loadLogsFromDatabase);
                });
            }
        };
        logAdapter = new LogAdapter(logList, deleteClickListener);

        recyclerViewLogs.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewLogs.setAdapter(logAdapter);

        loadLogsFromDatabase();

        btnAddLog.setOnClickListener(v -> {
            String componentName = etComponentName.getText().toString().trim();
            String takenBy = etTakenBy.getText().toString().trim();
            String burrowedDate = etBurrowedDate.getText().toString().trim();
            String returnDate = etReturnDate.getText().toString().trim();

            if(componentName.isEmpty()){
                etComponentName.setError("Component Name is Required");
                etComponentName.requestFocus();
                return;
            }
            if(takenBy.isEmpty()){
                etTakenBy.setError("Name is Required");
                etTakenBy.requestFocus();
                return;
            }
            if(burrowedDate.isEmpty()){
                etBurrowedDate.setError("Burrowed Date is Required");
                etBurrowedDate.requestFocus();
                return;
            }
            if(!isValidDate(burrowedDate)){
                etBurrowedDate.setError("Enter Valid Date YYYY/MM/DD");
                etBurrowedDate.requestFocus();
                return;
            }
            if(returnDate.isEmpty()){
                etReturnDate.setError("Return Date is Required");
                etReturnDate.requestFocus();
                return;
            }
            if(!isValidDate(returnDate)){
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

            try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
                executor.execute(() -> {
                    logDatabase.logDao().insert(log);
                    runOnUiThread(() -> {
                        clearForm();
                        loadLogsFromDatabase();
                    });
                });
            }
        });
    }

    private void clearForm() {
        etComponentName.setText("");
        etTakenBy.setText("");
        etBurrowedDate.setText("");
        etReturnDate.setText("");
    }

    private boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd", Locale.US);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private void loadLogsFromDatabase() {
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            executor.execute(() -> {
                final List<LogEntity> logs = logDatabase.logDao().getAllLogs();
                runOnUiThread(() -> {
                    logList.clear();
                    logList.addAll(logs);
                    logAdapter.notifyDataSetChanged();
                });
            });
        }
    }
}
