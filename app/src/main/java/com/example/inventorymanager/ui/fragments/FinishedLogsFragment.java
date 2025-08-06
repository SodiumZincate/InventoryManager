package com.example.inventorymanager.ui.fragments;

import com.example.inventorymanager.LogAdapter;
import com.example.inventorymanager.R;
import com.example.inventorymanager.model.LogEntity;
import com.example.inventorymanager.viewmodel.LogViewModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FinishedLogsFragment extends Fragment {
    private LogViewModel logViewModel;
    private RecyclerView recyclerViewLogs;
    private LogAdapter logAdapter;
    public FinishedLogsFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finished_logs, container, false);

        recyclerViewLogs = view.findViewById(R.id.recyclerViewFinishedLogs);
        logAdapter = new LogAdapter(new ArrayList<>());
        logAdapter.setActionListener(new LogAdapter.OnLogActionListener() {
            @Override
            public void onFinishClick(LogEntity log) {
                log.setFinished(true);
                logViewModel.update(log);
            }

            @Override
            public void onDeleteClick(LogEntity log) {
                logViewModel.delete(log);
            }
        });

        recyclerViewLogs.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewLogs.setAdapter(logAdapter);

        logViewModel = new ViewModelProvider(requireActivity()).get(LogViewModel.class);
        logViewModel.getFinishedLogs().observe(getViewLifecycleOwner(), logs -> logAdapter.setLogs((ArrayList<LogEntity>) logs));

        return view;
    }
}
