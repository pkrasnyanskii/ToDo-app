package ru.nsu.fit.team_project;

public class Task {
    private String name;
    private String description;
    private TaskStatus status;

    public Task(String name, String description) {
        this(name, description, TaskStatus.ACTIVE);
    }

    public Task(String name, String description, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return name + " | " + description;
    }
}

