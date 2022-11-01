package ru.nsu.fit.team_project.utilities;

public class Task {
    private String taskName;
    private String taskDesc;

    public Task(String taskName, String taskDesc) {
        this.taskName = taskName;
        this.taskDesc = taskDesc;
    }

    public void print() {
        System.out.println(taskName);
        System.out.println("=".repeat(taskDesc.length()));
        System.out.println(taskDesc);
        System.out.println();
    }
}
