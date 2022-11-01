package ru.nsu.fit.team_project.utilities;

import java.util.ArrayList;
import java.util.List;

public class CreateTaskCommand implements Command {
    List<Task> tasks;
    Task task;

    public CreateTaskCommand(List<Task> tasks, String taskName, String taskDesc) {
        this.tasks = tasks;
        task = new Task(taskName, taskDesc);
    }

    @Override
    public void execute() {
        tasks.add(task);
    }
}
