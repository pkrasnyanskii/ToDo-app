package ru.nsu.fit.team_project.model;

import ru.nsu.fit.team_project.model.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<MObject> objects = new ArrayList<>();
    private final List<Command> commands;

    public Model() {
        this(new ArrayList<>());
    }

    public Model(List<Command> commands) {
        this.commands = commands;
    }

    public List<MObject> getObjects() {
        return objects;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void addObject(MObject object) {
        objects.add(object);
    }

    public void addCommand(Command command) {
        commands.add(command);
    }
}
