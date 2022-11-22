package ru.nsu.fit.team_project.model;

import ru.nsu.fit.team_project.model.commands.Command;

import java.util.List;

public class Model {
    List<MObject> objects;
    List<Command> commands;

    public List<Command> getCommands() {
        return commands;
    }
}
