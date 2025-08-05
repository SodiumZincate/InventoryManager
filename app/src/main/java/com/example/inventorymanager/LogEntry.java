package com.example.inventorymanager;

public class LogEntry {
    private final String componentName;
    private final String takenBy;
    private final String burrowedDate;
    private final String returnDate;

    public LogEntry(String componentName, String takenBy, String burrowedDate, String returnDate){
        this.componentName = componentName;
        this.takenBy = takenBy;
        this.burrowedDate = burrowedDate;
        this.returnDate = returnDate;
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
}
