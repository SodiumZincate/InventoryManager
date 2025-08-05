package com.example.inventorymanager.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "logs")
public class LogEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String componentName;
    private String takenBy;
    private String burrowedDate;
    private String returnDate;

    public LogEntity(String componentName, String takenBy, String burrowedDate, String returnDate){
        this.componentName = componentName;
        this.takenBy = takenBy;
        this.burrowedDate = burrowedDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComponentName() {
        return componentName;
    }

    public String getTakenBy() {
        return takenBy;
    }

    public String getBurrowedDate() {
        return burrowedDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setComponentName(String takenBy){
        this.componentName = componentName;
    }

    public void setTakenBy(String takenBy) {
        this.takenBy = takenBy;
    }

    public void setBurrowedDate(String burrowedDate) {
        this.burrowedDate = burrowedDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
