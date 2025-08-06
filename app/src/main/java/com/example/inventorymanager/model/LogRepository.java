package com.example.inventorymanager.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.inventorymanager.model.LogDao;
import com.example.inventorymanager.model.LogDatabase;
import com.example.inventorymanager.model.LogEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogRepository {

    private final LogDao logDao;
    private final LiveData<List<LogEntity>> allLogs;
    private final LiveData<List<LogEntity>> finishedLogs;
    private final LiveData<List<LogEntity>> unfinishedLogs;
    private final ExecutorService executorService;

    public LogRepository(Application application){
        LogDatabase db = LogDatabase.getInstance(application);
        logDao = db.logDao();
        this.allLogs = db.logDao().getAllLogs();
        this.finishedLogs = db.logDao().getFinishedLogs();
        this.unfinishedLogs = db.logDao().getUnfinishedLogs();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<LogEntity>> getAllLogs() {
        return allLogs;
    }

    public LiveData<List<LogEntity>> getFinishedLogs() {
        return finishedLogs;
    }

    public LiveData<List<LogEntity>> getUnfinishedLogs() {
        return unfinishedLogs;
    }

    public void insert(LogEntity log){
        executorService.execute(() -> logDao.insert(log));
    }
    public void delete(LogEntity log){
        executorService.execute(() -> logDao.delete(log));
    }
    public void update(LogEntity log){
        executorService.execute(() -> logDao.update(log));
    }
}
