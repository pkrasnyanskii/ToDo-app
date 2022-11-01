package ru.nsu.fit.team_project.utilities;

import java.util.List;

public class DeleteTaskCommand implements Command {
    List<Task> tasks;
    Task task;

    public DeleteTaskCommand(List<Task> tasks, Task task) {
        this.tasks = tasks;
        this.task = task;
    }

    @Override
    public void execute() {
        tasks.remove(task);
    }
}
