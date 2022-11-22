package ru.nsu.fit.team_project.model.commands;

import ru.nsu.fit.team_project.model.MObject;

public class CreateObjectCommand extends Command {
    MObject object;

    public CreateObjectCommand() {

    }

    @Override
    public void execute() {
        object = new MObject();
    }
}
