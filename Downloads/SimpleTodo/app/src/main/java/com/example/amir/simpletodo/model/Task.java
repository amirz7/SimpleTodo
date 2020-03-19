package com.example.amir.simpletodo.model;

import java.util.Date;



/**
 * Created by amir on 3/14/20.
 */

public class Task {

    private String description;
    private boolean priority;
    private Date date;
    private boolean repeat;

    public Task() {}

    public Task(String description, boolean priority, Date date, boolean repeat) {
        this.description = description;
        this.priority = priority;
        this.date = date;
        this.repeat = repeat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
}
