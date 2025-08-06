package com.example.inventorymanager;

import com.example.inventorymanager.model.LogDatabase;
import com.example.inventorymanager.model.LogEntity;
import com.example.inventorymanager.ui.fragments.AllLogsFragment;
import com.example.inventorymanager.ui.fragments.FinishedLogsFragment;
import com.example.inventorymanager.ui.fragments.FormFragment;
import com.example.inventorymanager.ui.fragments.UnfinishedLogsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.inventorymanager.R;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private RecyclerView recyclerViewLogs;
    private LogAdapter logAdapter;
    private LiveData<List<LogEntity>> allLogList;
    private LiveData<List<LogEntity>> finishedLogList;
    private LiveData<List<LogEntity>> unfinishedLogList;
    private LogDatabase logDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new FormFragment())
                    .commit();
        }

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int itemId = item.getItemId();
            if (itemId == R.id.navForm) {
                selectedFragment = new FormFragment();
            } else if (itemId == R.id.navFinished) {
                selectedFragment = new FinishedLogsFragment();
            } else if (itemId == R.id.navUnfinished) {
                selectedFragment = new UnfinishedLogsFragment();
            } else if (itemId == R.id.navAll) {
                selectedFragment = new AllLogsFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, selectedFragment)
                        .commit();
                return true;
            }

            return false;
        });
    }
}
