package ru.nsu.fit.team_project.model.commands;

import ru.nsu.fit.team_project.model.MObject;
import ru.nsu.fit.team_project.model.Model;

import java.util.UUID;

public class CreateObjectCommand extends Command {
    private final UUID objectID = UUID.randomUUID();
    private final String objectType;

    public CreateObjectCommand(String objectType) {
        this.objectType = objectType;
    }

    @Override
    public void execute(Model model) {
        model.addObject(new MObject(objectID, objectType));
    }
}
