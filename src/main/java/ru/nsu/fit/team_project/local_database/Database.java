package ru.nsu.fit.team_project.local_database;

import ru.nsu.fit.team_project.utilities.Command;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Command> commands = new ArrayList<>();

    public Database() {

    }

    public void add(Command command) {
        commands.add(command);
    }

    public List<Command> getAll() {
        return commands;
    }
}
