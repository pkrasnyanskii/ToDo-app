package ru.nsu.fit.team_project.model;

import ru.nsu.fit.team_project.model.commands.Command;
import ru.nsu.fit.team_project.serialization.Serializer;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<MObject> objects = new ArrayList<>();
    private final List<Command> commands;

    public Model() {
        this(new Serializer().deserialize());
    }

    public Model(List<Command> commands) {
        this.commands = commands;
    }

    public void addObject(MObject object) {
        objects.add(object);
    }

    public List<MObject> getObjects() {
        return objects;
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public List<Command> getCommands() {
        return commands;
    }

}
