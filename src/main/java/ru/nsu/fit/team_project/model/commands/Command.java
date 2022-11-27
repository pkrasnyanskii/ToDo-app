package ru.nsu.fit.team_project.model.commands;

import ru.nsu.fit.team_project.model.Model;

import java.util.UUID;

public abstract class Command {
    UUID commandId = UUID.randomUUID();
    String commandName;

    public Command(String commandName) {
        this.commandName = commandName;
    }

    public UUID getID() {
        return commandId;
    }

    public abstract void execute(Model model);
}
