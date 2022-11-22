package ru.nsu.fit.team_project.model;

import ru.nsu.fit.team_project.model.commands.Command;

import java.util.List;

public class CommandExecutor {
    List<Command> commands;

    public CommandExecutor(List<Command> commands) {
        this.commands = commands;
    }

    public void executeAll() {
        commands.forEach(Command::execute);
    }
}
