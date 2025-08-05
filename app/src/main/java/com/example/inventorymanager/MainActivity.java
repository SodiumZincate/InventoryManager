package com.example.inventorymanager;

import com.example.inventorymanager.model.LogDatabase;
import com.example.inventorymanager.model.LogEntity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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
        logAdapter = new LogAdapter(logList);
        recyclerViewLogs.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewLogs.setAdapter(logAdapter);

        btnAddLog.setOnClickListener(v -> {
            String componentName = etComponentName.getText().toString().trim();
            String takenBy = etTakenBy.getText().toString().trim();
            String burrowedDate = etBurrowedDate.getText().toString().trim();
            String returnDate = etReturnDate.getText().toString().trim();

            if(!componentName.isEmpty() && !takenBy.isEmpty() &&
                    !burrowedDate.isEmpty() && !returnDate.isEmpty()){
                final LogEntity log = new LogEntity(componentName, takenBy, burrowedDate, returnDate);

                try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
                    executor.execute(() -> {
                        logDatabase.logDao().insert(log);
                        runOnUiThread(() -> {
                            loadLogsFromDatabase();
                            clearForm();
                        });
                    });
                }
            }
        });
    }

    private void clearForm() {
        etComponentName.setText("");
        etTakenBy.setText("");
        etReturnDate.setText("");
        etReturnDate.setText("");
    }

    private void loadLogsFromDatabase() {
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            executor.execute(() -> {
                final List<LogEntity> logs = logDatabase.logDao().getAllLogs();
                runOnUiThread(() -> {
                    logList.clear();
                    logList.addAll(logs);
                    logAdapter.notifyItemInserted(logList.size() - 1);
                });
            });
        }
    }
}
