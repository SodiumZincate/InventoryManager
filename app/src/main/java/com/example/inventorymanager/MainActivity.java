package com.example.inventorymanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private EditText etComponentName, etTakenBy, etBurrowedDate, etReturnDate;
    private Button btnAddLog;
    private RecyclerView recyclerViewLogs;
    private LogAdapter logAdapter;
    private ArrayList<LogEntry> logList;

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

        logList = new ArrayList<>();
        logAdapter = new LogAdapter(logList);
        recyclerViewLogs.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewLogs.setAdapter(logAdapter);

        btnAddLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String componentName = etComponentName.getText().toString().trim();
                String takenBy = etTakenBy.getText().toString().trim();
                String burrowedDate = etBurrowedDate.getText().toString().trim();
                String returnDate = etReturnDate.getText().toString().trim();

                if(!componentName.isEmpty() && !takenBy.isEmpty() &&
                        !burrowedDate.isEmpty() && !returnDate.isEmpty()){
                    LogEntry logEntry = new LogEntry(componentName, takenBy, burrowedDate, returnDate);
                    logList.add(logEntry);
                    logAdapter.notifyItemInserted(logList.size() - 1);

                    etComponentName.setText("");
                    etTakenBy.setText("");
                    etBurrowedDate.setText("");
                    etReturnDate.setText("");
                }
            }
        });
    }
}
