package com.example.inventorymanager;

import android.app.AlertDialog;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.model.LogEntity;

import java.util.ArrayList;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder>{
    private ArrayList<LogEntity> logs;
    private OnLogActionListener actionListener;

    public interface OnLogActionListener {
        void onFinishClick(LogEntity log);
        void onDeleteClick(LogEntity log);
    }

    public void setActionListener(OnLogActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public LogAdapter(ArrayList<LogEntity> logs){
        this.logs = logs;
    }

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_log, parent, false);
        return new LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position){
        LogEntity log = logs.get(position);

        String componentText = "Component: " + log.getComponentName();
        String takenByText = "Taken By: " + log.getTakenBy();
        String burrowedDateText = "Burrowed Date: " + log.getBurrowedDate();
        String returnDateText = "Return Date: " + log.getReturnDate();
        holder.tvComponentName.setText(componentText);
        holder.tvTakenBy.setText(takenByText);
        holder.tvBurrowedDate.setText(burrowedDateText);
        holder.tvReturnDate.setText(returnDateText);

        if(log.isFinished()){
            holder.itemView.setBackgroundColor(Color.parseColor("#C8E6C9"));
        }
        else{
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.btnDeleteLog.setOnClickListener(v -> {
            if(actionListener != null){
                new AlertDialog.Builder(holder.itemView.getContext())
                        .setTitle("Confirm Delete")
                        .setMessage("Are you sure you want to delete this log?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            actionListener.onDeleteClick(log);
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        holder.btnFinishLog.setOnClickListener(v -> {
            if(actionListener != null){
                new AlertDialog.Builder(holder.itemView.getContext())
                        .setTitle("Confirm Finish")
                        .setMessage("Are you sure you want to finish this log?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            log.setFinished(true);
                            actionListener.onFinishClick(log);
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    public void setLogs(ArrayList<LogEntity> logs) {
        this.logs = logs;
        notifyDataSetChanged();
    }

    public static class LogViewHolder extends RecyclerView.ViewHolder{
        TextView tvComponentName, tvTakenBy, tvBurrowedDate, tvReturnDate;
        ImageButton btnDeleteLog, btnFinishLog;

        public LogViewHolder(@NonNull View itemView){
            super(itemView);
            tvComponentName = itemView.findViewById(R.id.tvComponentName);
            tvTakenBy = itemView.findViewById(R.id.tvTakenBy);
            tvBurrowedDate = itemView.findViewById(R.id.tvBurrowedDate);
            tvReturnDate = itemView.findViewById(R.id.tvReturnDate);
            btnDeleteLog = itemView.findViewById(R.id.btnDeleteLog);
            btnFinishLog = itemView.findViewById(R.id.btnFinishLog);
        }
    }
}
