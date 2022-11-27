package ru.nsu.fit.team_project.model.commands;

import ru.nsu.fit.team_project.model.MObject;
import ru.nsu.fit.team_project.model.Model;
import ru.nsu.fit.team_project.model.fields.DoubleField;
import ru.nsu.fit.team_project.model.fields.IntegerField;
import ru.nsu.fit.team_project.model.fields.StringField;

import java.util.UUID;

public class AddFieldCommand extends Command {
    UUID objectID;
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

        switch (fieldType) {
            case "String" -> object.addField(new StringField(fieldName));
            case "int" -> object.addField(new IntegerField(fieldName));
            case "double" -> object.addField(new DoubleField(fieldName));
            default -> throw new IllegalArgumentException("Unsupported field type");
        }
    }
}
