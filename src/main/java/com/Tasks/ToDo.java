package com.Tasks;
import com.MicroServices.ToDo.*;


public class ToDo {
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public ToDo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
