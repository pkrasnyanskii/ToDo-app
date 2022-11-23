package ru.nsu.fit.team_project.model;

import ru.nsu.fit.team_project.model.fields.Field;

import java.util.List;

public class MObject {
    String id;
    String type;
    List<Field> fields;

    public void addField(Field field) {
        fields.add(field);
    }

    public String getId() {
        return id;
    }
}
