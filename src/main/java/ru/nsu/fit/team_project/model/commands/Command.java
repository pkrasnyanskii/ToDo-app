package ru.nsu.fit.team_project.model.commands;

import ru.nsu.fit.team_project.model.Model;

import java.util.UUID;

public abstract class Command {
    UUID id;
    String name;

    public abstract void execute(Model model);
}
