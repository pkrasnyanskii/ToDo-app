package ru.nsu.fit.team_project.model;

import ru.nsu.fit.team_project.model.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class Model {
    List<MObject> objects = new ArrayList<>();
    List<Command> commands = new ArrayList<>();

    public Model() {

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
