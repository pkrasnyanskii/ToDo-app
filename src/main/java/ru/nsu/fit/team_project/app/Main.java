package ru.nsu.fit.team_project.app;

import ru.nsu.fit.team_project.local_database.Database;
import ru.nsu.fit.team_project.utilities.Command;
import ru.nsu.fit.team_project.utilities.CreateTaskCommand;
import ru.nsu.fit.team_project.utilities.Task;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();

        Database db = new Database();
        db.add(new CreateTaskCommand(tasks, "Task 1", "Task 1 description"));
        db.add(new CreateTaskCommand(tasks, "Task 2", "Task 2 description"));
        db.add(new CreateTaskCommand(tasks, "Task 2", "Task 2 description"));

        for (Command c : db.getAll()) {
            c.execute();
        }

        for (Task task : tasks) {
            task.print();
        }
    }
}