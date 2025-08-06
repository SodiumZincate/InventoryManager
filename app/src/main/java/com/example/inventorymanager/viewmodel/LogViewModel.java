package com.example.inventorymanager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.inventorymanager.model.LogEntity;
import com.example.inventorymanager.model.LogRepository;

import java.util.ArrayList;
import java.util.List;

public class LogViewModel extends AndroidViewModel {

    private final LogRepository repository;
    private final LiveData<List<LogEntity>> allLogs;

    public LogViewModel(@NonNull Application application) {
        super(application);
        repository = new LogRepository(application);
        allLogs = repository.getAllLogs();
    }

    public LiveData<List<LogEntity>> getAllLogs() {
        return allLogs;
    }

    public LiveData<List<LogEntity>> getFinishedLogs() {
        return repository.getFinishedLogs();
    }

    public LiveData<List<LogEntity>> getUnfinishedLogs() {
        return repository.getUnfinishedLogs();
    }

    public void insert(LogEntity log) {
        repository.insert(log);
    }

    public void delete(LogEntity log) {
        repository.delete(log);
    }

    public void update(LogEntity log) {
        repository.update(log);
    }
}
