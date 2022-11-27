package ru.nsu.fit.team_project.model.commands;

import ru.nsu.fit.team_project.model.Model;

import java.util.UUID;

public abstract class Command {
    UUID commandId = UUID.randomUUID();
    String commandName;

    public abstract void execute(Model model);
}
