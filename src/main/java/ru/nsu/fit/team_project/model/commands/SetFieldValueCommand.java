package ru.nsu.fit.team_project.model.commands;

import ru.nsu.fit.team_project.model.Model;
import ru.nsu.fit.team_project.model.fields.Field;

import java.util.UUID;

public class SetFieldValueCommand extends Command {
    private final UUID fieldID;
    private final Object value;

    public SetFieldValueCommand(UUID fieldID, Object value) {
        super("SetFieldValueCommand");
        this.fieldID = fieldID;
        this.value = value;
    }

    @Override
    public void execute(Model model) {
        Field field = model.getObjects()
                           .stream()
                           .filter(o -> o.getFields()
                                         .stream()
                                         .anyMatch(f -> f.getID().equals(fieldID))
                           )
                           .findAny()
                           .orElseThrow()
                           .getField(fieldID);
        field.setValue(value);
    }
}
