package com.example.inventorymanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.model.LogEntity;

import java.util.ArrayList;
public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder>{
    private final ArrayList<LogEntity> logs;

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
    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    public static class LogViewHolder extends RecyclerView.ViewHolder{
        TextView tvComponentName, tvTakenBy, tvBurrowedDate, tvReturnDate;

        public LogViewHolder(@NonNull View itemView){
            super(itemView);
            tvComponentName = itemView.findViewById(R.id.tvComponentName);
            tvTakenBy = itemView.findViewById(R.id.tvTakenBy);
            tvBurrowedDate = itemView.findViewById(R.id.tvBurrowedDate);
            tvReturnDate = itemView.findViewById(R.id.tvReturnDate);
        }
    }
}
