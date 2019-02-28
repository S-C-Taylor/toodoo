package com.sctaylor.toodoo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodoItemUpdate {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("completed")
    @Expose
    private Boolean completed;

    public TodoItemUpdate() {

    }

    public TodoItemUpdate(TodoItem item) {
        this.title = item.getTitle();
        this.description = item.getDescription();
        this.completed = item.getCompleted();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "TodoItemUpdate{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }
}
