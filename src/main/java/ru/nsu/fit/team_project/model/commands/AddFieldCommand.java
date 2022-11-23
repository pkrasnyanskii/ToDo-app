package ru.nsu.fit.team_project.model.commands;

import ru.nsu.fit.team_project.model.MObject;
import ru.nsu.fit.team_project.model.Model;
import ru.nsu.fit.team_project.model.fields.DoubleField;
import ru.nsu.fit.team_project.model.fields.IntegerField;
import ru.nsu.fit.team_project.model.fields.StringField;

public class AddFieldCommand extends Command {
    String objectId;
    String fieldName;
    String fieldType;

    public AddFieldCommand(String objectId, String fieldName, String fieldType) {
        this.objectId = objectId;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    @Override
    public void execute(Model model) {
        MObject object = model.getObjects()
                .stream()
                .filter(mObj -> mObj.getId().equals(objectId))
                .findAny()
                .orElseThrow();

        switch (fieldType) {
            case "String" -> object.addField(new StringField("id", fieldName));
            case "int" -> object.addField(new IntegerField("id", fieldName));
            case "double" -> object.addField(new DoubleField("id", fieldName));
            default -> throw new IllegalArgumentException("Unsupported field type");
        }
    }
}
