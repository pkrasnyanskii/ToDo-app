package ru.nsu.fit.team_project.model.commands;

import ru.nsu.fit.team_project.model.MObject;
import ru.nsu.fit.team_project.model.Model;
import ru.nsu.fit.team_project.model.fields.Field;

import java.util.UUID;

public class AddFieldCommand extends Command {
    UUID objectID;
    UUID fieldID = UUID.randomUUID();
    String fieldName;
    String fieldType;

    public AddFieldCommand(UUID objectId, String fieldName, String fieldType) {
        super("AddFieldCommand");
        this.objectID = objectId;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    @Override
    public void execute(Model model) {
        MObject object = model.getObjects()
                              .stream()
                              .filter(o -> o.getId().equals(objectID))
                              .findAny()
                              .orElseThrow();

        object.addField(new Field(fieldID, fieldName));
    }
}
